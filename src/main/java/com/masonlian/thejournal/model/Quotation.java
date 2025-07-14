package com.masonlian.thejournal.model;


//我可能覺得一個人擁有兩種世界，一種是這個世界直接給你而你就直接接受的，另外一種則是世界給了你之後你經過思考而建構出來的，

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Quotation {

    private Integer projectId;
    private Integer quotationId;
    public Timestamp createdDate;
    public String createBy;
    public String status;
    public String summary;
    public BigDecimal totalAmount;

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

    public Timestamp getCreateDate() {
        return createdDate;
    }

    public void setCreateDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
