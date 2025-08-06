package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.config.CustomUserDetails;
import com.masonlian.thejournal.dao.CostMgmtDao;
import com.masonlian.thejournal.dao.ProjectsDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.QuotationWithItemDto;
import com.masonlian.thejournal.dto.request.*;
import com.masonlian.thejournal.model.*;
import com.masonlian.thejournal.service.CostMgmtService;
import com.masonlian.thejournal.service.FinancialService;
import com.masonlian.thejournal.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class ProjectsServiceImpl implements ProjectsService {

    @Autowired
    ProjectsDao projectsDao;

    @Autowired
    CostMgmtDao costMgmtDao;

    @Autowired
    FinancialService financialService;

    @Override
    public Integer createProject(ProjectRequest projectRequest) {

        return projectsDao.createProject(projectRequest);
    }

    @Override
    public Project getProjectById(Integer projectId) {

        return projectsDao.getProjectById(projectId);

    }

    @Override
    public void deleteProjectById(Integer projectId) {
        projectsDao.deleteProjectById(projectId);
    }

    @Override
    public void updateProjectById(Integer projectId, ProjectRequest projectRequest) {
        projectsDao.updateProjectById(projectId, projectRequest);
    }


    @Override
    public List<Project> getProjects(QueryPara queryPara) {

        List<Project> projectList = projectsDao.getProjects(queryPara);

        return projectList;
    }

    @Transactional
    @Override
    public void updateBalanceByDailyExpense(String projectName, BigDecimal newDailyExpense) {

        Project project = projectsDao.getProjectByName(projectName);

        BigDecimal balance = project.getBalance();
        balance = balance.subtract(newDailyExpense);

        projectsDao.updateBalance(project.getProjectId(), balance);

    }

    @Transactional
    @Override
    public void updateBalanceByWagePerDay(String projectName, BigDecimal newWagePerDay) {

        Project project = projectsDao.getProjectByName(projectName);

        BigDecimal balance = project.getBalance();
        balance = balance.subtract(newWagePerDay);
        projectsDao.updateBalance(project.getProjectId(), balance);

    }

    @Override
    public Integer createQuotation(CustomUserDetails userDetails, CreateQuotationRequest createQuotationRequest) {

        return projectsDao.createQuotation(userDetails, createQuotationRequest);

    }

    @Override
    public Quotation getQuotationById(Integer quotationId) {

        return projectsDao.getQuotationById(quotationId);


    }


    //排單功能
    @Override
    public void createQuotationItem(Integer quotationId, QuotationItemRequest quotationItemRequest) {

        List<QuotationItem> quotationItemList = new ArrayList<>();
        List<GiveAQuote> quotationItemRequestList = quotationItemRequest.getQuotationItemsRequest();

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (GiveAQuote giveAQuote : quotationItemRequestList) {


            QuotationItem quotationItem = new QuotationItem();
            quotationItem.setQuotationId(quotationId);

            quotationItem.setConstructionItem(giveAQuote.getConstructionItem());
            quotationItem.setConstructionUnit(giveAQuote.getConstructionUnit());
            quotationItem.setConstructionSpec(giveAQuote.getConstructionSpec());
            quotationItem.setConstruction_estimate(giveAQuote.getConstructionEstimate());


            BigDecimal amount = quotationItem.getConstruction_estimate().multiply(giveAQuote.getConstructionUnit());
            totalAmount = totalAmount.add(amount);



            if (checkItemExist(quotationItem.getConstructionItem()) == false) {
                Construction construction = new Construction();

                construction.setConstructionItem(quotationItem.getConstructionItem());
                construction.setConstructionSpec(quotationItem.getConstructionSpec());
                construction.setConstructionEstimate(quotationItem.getConstruction_estimate());

                costMgmtDao.createConstruction(List.of(construction));

            }

            quotationItemList.add(quotationItem);
        }


        projectsDao.createQuotationItem(quotationItemList);
        projectsDao.updateTotalAmount(quotationId, totalAmount);

        Quotation quotation = projectsDao.getQuotationById(quotationId);

        System.out.println("專案為:"+quotation.getProjectId()+"報價總額為:"+totalAmount);

        updateCostEstimate(quotation.getProjectId(), totalAmount);



    }

    //我想要在這裡加上transactional的功能
    //project中的cost_estimate欄位有問題。

    @Transactional
    public void updateCostEstimate(Integer projectId, BigDecimal newAmount) {

        Project project = projectsDao.getProjectById(projectId);
        System.out.println( "報價為："+project.getProjectName());

        BigDecimal costEstimate = project.getCostEstimate();

        System.out.println("現階成本估計為"+costEstimate);

        newAmount = newAmount.multiply(BigDecimal.valueOf(0.7));
        costEstimate = costEstimate.add(newAmount);

        projectsDao.updateCostEstimate(projectId, costEstimate);

    }

    @Override
    public List<QuotationWithItemDto> getQuotations(Integer projectId) {

        List< Quotation> quotationList = projectsDao.getQuotations(projectId); //quotation要大改

        List<QuotationWithItemDto> quotationWithItemDtoList = new ArrayList<>(); // 動態陣列會自己調整大小

        for( Quotation quotation :quotationList) {

            Integer quotationId = quotation.getQuotationId();
            List<QuotationItem> quotationItemList = projectsDao.getQuotationItemById(quotationId);

            QuotationWithItemDto quotationWithItemDto = new QuotationWithItemDto();

            quotationWithItemDto.setQuotationId(quotationId);
            quotationWithItemDto.setProjectId(projectId);
            quotationWithItemDto.setStatus(quotation.getStatus());
            quotationWithItemDto.setSummary(quotation.getSummary());
            quotationWithItemDto.setCreatedDate(quotation.getCreateDate());
            quotationWithItemDto.setCreateBy(quotation.getCreateBy());
            quotationWithItemDto.setTotalAmount(quotation.getTotalAmount());

            quotationWithItemDto.setQuotationItemList(quotationItemList);

            quotationWithItemDtoList.add(quotationWithItemDto);

        }

        return quotationWithItemDtoList;


    }

    @Transactional
    @Override
    public void updateProfitById(Integer projectId) {

        System.out.println("要更新利潤的專案為："+projectsDao.getProjectById(projectId).getProjectName());

        Project project = projectsDao.getProjectById(projectId);
        BigDecimal balance = project.getBalance();

        BigDecimal costEstimate = project.getCostEstimate();

        if (project.getFinish() == true) {

            BigDecimal profit = costEstimate.subtract(balance);

            System.out.println("要被更新的專案利潤金額為:"+profit);

            projectsDao.updateProfitById(projectId, profit);

            financialService.updateProfit(profit);

        }

    }

    @Transactional
    @Override
    public Integer createReceived(NewReceived newReceived) {

        BigDecimal payment = newReceived.getReceivedPayment();
        createBudget(newReceived.getProjectId(), payment);

        financialService.updateReceived(newReceived.getReceivedPayment());

        return projectsDao.createReceived(newReceived);


    }

    @Transactional
    public void updateBalance(Integer projectId, BigDecimal balance) {

        Project project = projectsDao.getProjectById(projectId);
        BigDecimal newBalance = project.getBalance().add(balance);

        projectsDao.updateBalance(projectId, newBalance);

    }

    @Override
    public Received getReceivedById(Integer receivedId) {

        return projectsDao.getReceivedById(receivedId);
    }

    @Override
    public List<Received> getReceivedByProjectId(Integer projectId) {

        return projectsDao.getReceivedByProjectId(projectId);
    }

    @Transactional
    @Override
    public void updateBalanceByMaterialCost(Integer projectId, BigDecimal newAmount) {

        Project project = projectsDao.getProjectById(projectId);
        BigDecimal balance = project.getBalance();
        balance = balance.subtract(newAmount);

        projectsDao.updateBalance(projectId, balance);

    }

    @Transactional
    public void createBudget(Integer projectId, BigDecimal newReceived) {

        Project project = projectsDao.getProjectById(projectId);
        BigDecimal budget = project.getBudget();
        budget = budget.add(newReceived);

        updateBalance(projectId, newReceived);
        projectsDao.updateBudgetById(projectId, budget);


    }


    public boolean checkItemExist(String itemName) {
        if (costMgmtDao.getConstructionByName(itemName) == null) {

            return false;
        } else {
            return true;
        }
    }
}

