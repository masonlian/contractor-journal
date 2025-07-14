package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.constant.Level;
import com.masonlian.thejournal.dao.CostMgmtDao;
import com.masonlian.thejournal.dao.ProjectsDao;
import com.masonlian.thejournal.dto.CustomUserDetails;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.QuotationWithItemDto;
import com.masonlian.thejournal.dto.request.GiveAQuote;
import com.masonlian.thejournal.dto.request.QuotationItemRequest;
import com.masonlian.thejournal.dto.request.QuotationRequest;
import com.masonlian.thejournal.model.*;
import com.masonlian.thejournal.dto.request.ProjectRequest;
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
    private CostMgmtDao costMgmtDao;


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
    public List<Project> getProjects(QueryPara queryPara, CustomUserDetails customUserDetails) {

        List<Project> projectList = projectsDao.getProjects(queryPara);
        if(customUserDetails.getLevel() == Level.L3){
            for(Project project : projectList){
                project.setCostEstimate(null);
                project.setProfit(null);
                project.setBudget(null);
            }
        }
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
    public Integer createQuotation(Integer projectId, QuotationRequest quotationRequest){

        return projectsDao.createQuotation(projectId, quotationRequest);

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


}
