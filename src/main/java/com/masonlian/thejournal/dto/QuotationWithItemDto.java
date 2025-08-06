package com.masonlian.thejournal.dto;

import com.masonlian.thejournal.model.QuotationItem;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class QuotationWithItemDto {

    private Integer projectId;
    private Integer quotationId;
    private Timestamp createdDate;
    private String createBy;
    private String status;
    private String summary;
    private BigDecimal totalAmount;

    private List<QuotationItem> quotationItemList;


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(Integer quotationId) {
        this.quotationId = quotationId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<QuotationItem> getQuotationItemList() {
        return quotationItemList;
    }

    public void setQuotationItemList(List<QuotationItem> quotationItemList) {
        this.quotationItemList = quotationItemList;
    }
}
