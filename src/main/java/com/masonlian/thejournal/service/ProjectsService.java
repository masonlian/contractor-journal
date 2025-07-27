package com.masonlian.thejournal.service;

import com.masonlian.thejournal.config.CustomUserDetails;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.QuotationWithItemDto;
import com.masonlian.thejournal.dto.request.NewReceived;
import com.masonlian.thejournal.dto.request.QuotationItemRequest;
import com.masonlian.thejournal.dto.request.CreateQuotationRequest;
import com.masonlian.thejournal.model.Project;
import com.masonlian.thejournal.dto.request.ProjectRequest;
import com.masonlian.thejournal.model.Quotation;
import com.masonlian.thejournal.model.Received;

import java.math.BigDecimal;
import java.util.List;

public interface ProjectsService {
    Integer createProject(ProjectRequest projectRequest);
    Project getProjectById(Integer projectId);
    void deleteProjectById(Integer projectId);
    void updateProjectById(Integer projectId,ProjectRequest projectRequest);
    List<Project> getProjects(QueryPara queryPara);

    BigDecimal updateBalanceByDailyExpense(String projectName ,BigDecimal newDailyExpense );

    Integer createQuotation(CustomUserDetails userDetails  , CreateQuotationRequest createQuotationRequest);
    Quotation getQuotationById(Integer quotationId);
    void createQuotationItem (Integer projectId, QuotationItemRequest quotationItemRequest);
    List<QuotationWithItemDto> getQuotations (Integer projectId);
    void updateProfitById(Integer projectId) ;
    void updateCostEstimate(Integer projectId, BigDecimal newAmount);
    void updateBalanceByMaterialCost(Integer projectId , BigDecimal newAmount);

    Integer createReceived(NewReceived newReceived);
    Received  getReceivedById(Integer receivedId);
    List<Received> getReceivedByProjectId(Integer projectId);

}
