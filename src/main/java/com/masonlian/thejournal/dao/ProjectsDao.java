package com.masonlian.thejournal.dao;

import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.QuotationWithItemDto;
import com.masonlian.thejournal.dto.request.NewReceived;
import com.masonlian.thejournal.dto.request.QuotationRequest;
import com.masonlian.thejournal.model.Project;
import com.masonlian.thejournal.dto.request.ProjectRequest;
import com.masonlian.thejournal.model.Quotation;
import com.masonlian.thejournal.model.QuotationItem;
import com.masonlian.thejournal.model.Received;

import java.math.BigDecimal;
import java.util.List;

public interface ProjectsDao {
    //專案計畫操作
    Integer createProject(ProjectRequest projectRequest);
    Project getProjectById(Integer projectId);
    void deleteProjectById(Integer projectId);
    void updateProjectById(Integer projectId,ProjectRequest projectRequest);
    List<Project> getProjects(QueryPara queryPara);
    Project getProjectByName(String projectName);
    void updateProfitById(Integer projectId,BigDecimal profit);

    //報價單資料操作
    Integer createQuotation(Integer projectId, QuotationRequest quotationRequest);
    void updateTotalAmount(Integer quotationId, BigDecimal totalAmount);

    void createQuotationItem(List<QuotationItem> quotationItemList);

    Quotation getQuotationById(Integer quotationId);

    void updatePeriod(Integer projectId  , Integer period);
    void updateCostEstimate(Integer projectId, BigDecimal newEstimate);
    List<QuotationWithItemDto> getQuotations (Integer projectId);
    void finishProject(Integer projectId,Boolean finish);

    Integer createReceived(NewReceived newReceived);
    void updateBalance(Integer projectId , BigDecimal balance);
    Received getReceivedById(Integer receivedId);
    List<Received> getReceivedByProjectId(Integer projectId);
    void updateBudgetById( Integer projectId, BigDecimal budget);



}
