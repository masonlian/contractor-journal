package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.config.CustomUserDetails;
import com.masonlian.thejournal.dao.CostMgmtDao;
import com.masonlian.thejournal.dao.ProjectsDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.QuotationWithItemDto;
import com.masonlian.thejournal.dto.request.*;
import com.masonlian.thejournal.model.*;
import com.masonlian.thejournal.service.FinancialService;
import com.masonlian.thejournal.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Component
public class ProjectsServiceImpl implements ProjectsService {

    @Autowired
    ProjectsDao projectsDao;

    @Autowired
    CostMgmtDao costMgmtDao;

    @Autowired
    FinancialService financialService;

    @Override
    public Integer createProject(ProjectRequest projectRequest){

        return projectsDao.createProject(projectRequest);
    }

    @Override
    public Project getProjectById(Integer projectId){

        return projectsDao.getProjectById(projectId);

    }

    @Override
    public void deleteProjectById(Integer projectId){
      projectsDao.deleteProjectById(projectId);
    }

    @Override
    public void updateProjectById(Integer projectId,ProjectRequest projectRequest){
        projectsDao.updateProjectById(projectId ,projectRequest);
    }


    @Override
    public List<Project> getProjects(QueryPara queryPara) {

        List<Project> projectList = projectsDao.getProjects(queryPara);

        return projectList;
    }

    @Override
    public BigDecimal updateBalanceByDailyExpense(String projectName , BigDecimal newDailyExpense ){
        Project project = projectsDao.getProjectByName(projectName);
        BigDecimal balance = project.getCostEstimate();
        balance = balance.add(newDailyExpense);
        return balance;

    }

    @Override
    public Integer createQuotation(CustomUserDetails userDetails , CreateQuotationRequest createQuotationRequest){

        return projectsDao.createQuotation(userDetails , createQuotationRequest);

    }

    @Override
    public Quotation getQuotationById(Integer quotationId){

        return projectsDao.getQuotationById(quotationId);



    }



    //排單功能
    @Override
    public void createQuotationItem(Integer quotationId , QuotationItemRequest quotationItemRequest){

        List<QuotationItem> requestList = new ArrayList<>();
        List<GiveAQuote> quotationItemRequestList =  quotationItemRequest.getQuotationItemsRequest();

        BigDecimal totalAmount = BigDecimal.ZERO;

        for(GiveAQuote giveAQuote  : quotationItemRequestList ){

            Material material = costMgmtDao.getMaterialByName(giveAQuote.getMaterialName());
            Construction construction = costMgmtDao.getConstructionItemByName(giveAQuote.getConstructionItem());

            QuotationItem quotationItem = new QuotationItem();

            quotationItem.setQuotationId(quotationId);


            quotationItem.setMaterialName(giveAQuote.getMaterialName());
            quotationItem.setMaterialUnit(giveAQuote.getMaterialUnit());
            quotationItem.setMaterialSpec(material.getSpecification());

            BigDecimal materialUnitPrice = material.getUnitPrice();
            BigDecimal materialAmount =materialUnitPrice.multiply(quotationItem.getMaterialUnit());
            quotationItem.setMaterialAmount(materialAmount);

            totalAmount = totalAmount.add(materialAmount);

            quotationItem.setConstructionItem(construction.getConstructionItem());
            quotationItem.setConstructionUnit(giveAQuote.getConstructionUnit());
            quotationItem.setConstructionSpec(construction.getConstructionSpec());

            BigDecimal constructionAmount = construction.getConstructionEstimate().multiply(quotationItem.getConstructionUnit());
            quotationItem.setConstructionAmount(constructionAmount);

            totalAmount = totalAmount.add(constructionAmount);


            requestList.add(quotationItem);
        }

        projectsDao.updateTotalAmount(quotationId, totalAmount);

        Quotation quotation = projectsDao.getQuotationById(quotationId);

        updateCostEstimate(quotation.getProjectId(), totalAmount);


        projectsDao.createQuotationItem(requestList);

    }


    public void updateCostEstimate(Integer projectId, BigDecimal newAmount){

        Project project =  projectsDao.getProjectById(projectId);
        BigDecimal costEstimate = project.getCostEstimate();

        costEstimate = costEstimate.add(newAmount);

        projectsDao.updateCostEstimate(projectId, costEstimate);

    }
    @Override
    public List<QuotationWithItemDto> getQuotations (Integer projectId){

        return projectsDao.getQuotations(projectId);

    }

    @Override
    public void updateProfitById(Integer projectId){
        Project project = projectsDao.getProjectById(projectId);
        BigDecimal balance =  project.getBalance();

        BigDecimal costEstimate = project.getCostEstimate();

        if (project.getFinish() == true){

        BigDecimal profit = costEstimate.subtract(balance);

        projectsDao.updateProfitById(projectId,profit);
        financialService.updateProfit(profit);

        }

    }

    @Override
    public Integer createReceived(NewReceived newReceived){

        BigDecimal  payment = newReceived.getReceivedPayment();
        createBudget(newReceived.getProjectId(),payment);

        financialService.updateReceived(newReceived.getReceivedPayment());

        return projectsDao.createReceived(newReceived);


     }

     public  void updateBalance(Integer projectId, BigDecimal balance){

        Project project = projectsDao.getProjectById(projectId);
        BigDecimal newBalance =  project.getBalance().add(balance);

        projectsDao.updateBalance(projectId,newBalance);

     }

     @Override
     public Received  getReceivedById(Integer receivedId){

        return projectsDao.getReceivedById(receivedId);
     }

     @Override
     public List<Received> getReceivedByProjectId(Integer projectId){

        return projectsDao.getReceivedByProjectId(projectId);
     }
     @Override
     public void updateBalanceByMaterialCost(Integer projectId , BigDecimal newAmount){

        Project project = projectsDao.getProjectById(projectId);
        BigDecimal balance = project.getBalance();
        balance = balance.subtract(newAmount);

        projectsDao.updateBalance(projectId,balance);

     }

    public  void createBudget(Integer projectId, BigDecimal newReceived){

        Project project = projectsDao.getProjectById(projectId);
        BigDecimal budget =  project.getBudget();
        budget = budget.add(newReceived);

        updateBalance(projectId,budget);
        projectsDao.updateBudgetById(projectId,budget);



    }


}
