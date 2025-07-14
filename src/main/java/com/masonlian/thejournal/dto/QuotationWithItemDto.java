package com.masonlian.thejournal.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class QuotationWithItemDto {

    private Integer projectId;
    private Integer quotationId;
    public Timestamp createdDate;
    public String createBy;
    public String status;
    public String summary;
    public BigDecimal totalAmount;
    private Integer quotationItemId;

    private String materialName;
    private BigDecimal materialUnit;
    private String materialSpec;

    private BigDecimal materialAmount;

    private BigDecimal constructionUnit;
    private String constructionItem;
    private String constructionSpec;

    private BigDecimal constructionAmount;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getQuotationItemId() {
        return quotationItemId;
    }

    public void setQuotationItemId(Integer quotationItemId) {
        this.quotationItemId = quotationItemId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialSpec() {
        return materialSpec;
    }

    public void setMaterialSpec(String materialSpec) {
        this.materialSpec = materialSpec;
    }

    public BigDecimal getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(BigDecimal materialUnit) {
        this.materialUnit = materialUnit;
    }

    public BigDecimal getMaterialAmount() {
        return materialAmount;
    }

    public void setMaterialAmount(BigDecimal materialAmount) {
        this.materialAmount = materialAmount;
    }

    public String getConstructionSpec() {
        return constructionSpec;
    }

    public void setConstructionSpec(String constructionSpec) {
        this.constructionSpec = constructionSpec;
    }

    public BigDecimal getConstructionAmount() {
        return constructionAmount;
    }

    public void setConstructionAmount(BigDecimal constructionAmount) {
        this.constructionAmount = constructionAmount;
    }

    public String getConstructionItem() {
        return constructionItem;
    }

    public void setConstructionItem(String constructionItem) {
        this.constructionItem = constructionItem;
    }

    public BigDecimal getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(BigDecimal constructionUnit) {
        this.constructionUnit = constructionUnit;
    }
}
