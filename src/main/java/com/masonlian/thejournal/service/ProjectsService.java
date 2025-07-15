package com.masonlian.thejournal.service;

import com.masonlian.thejournal.dto.CustomUserDetails;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.QuotationWithItemDto;
import com.masonlian.thejournal.dto.request.NewReceived;
import com.masonlian.thejournal.dto.request.QuotationItemRequest;
import com.masonlian.thejournal.dto.request.QuotationRequest;
import com.masonlian.thejournal.model.Project;
import com.masonlian.thejournal.dto.request.ProjectRequest;
import com.masonlian.thejournal.model.Quotation;
import com.masonlian.thejournal.model.QuotationItem;
import com.masonlian.thejournal.model.Received;

import java.math.BigDecimal;
import java.util.List;

public interface ProjectsService {
    Integer createProject(ProjectRequest projectRequest);
    Project getProjectById(Integer projectId);
    void deleteProjectById(Integer projectId);
    void updateProjectById(Integer projectId,ProjectRequest projectRequest);
    List<Project> getProjects(QueryPara queryPara, CustomUserDetails customUserDetails);

    BigDecimal updateBalanceByDailyExpense(String projectName ,BigDecimal newDailyExpense );

    Integer createQuotation(Integer projectId, QuotationRequest quotationRequest);
    Quotation getQuotationById(Integer quotationId);
    void createQuotationItem (Integer projectId, QuotationItemRequest quotationItemRequest);
    List<QuotationWithItemDto> getQuotations (Integer projectId);
    void updateProfitById(Integer projectId) ;
    void updateCostEstimate(Integer projectId, BigDecimal newAmount);

    Integer createReceived(NewReceived newReceived);
    Received  getReceivedById(Integer receivedId);
    List<Received> getReceivedByProjectId(Integer projectId);

}
