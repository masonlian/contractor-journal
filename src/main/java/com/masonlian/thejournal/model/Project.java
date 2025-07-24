package com.masonlian.thejournal.model;

import java.math.BigDecimal;
import java.util.Date;

public class Project {

    private Integer projectId;
    private String owner;
    private String projectName;
    private String address;
    private String projectManager;
    private BigDecimal budget;
    private BigDecimal costEstimate;
    private BigDecimal profit;
    private Date  creationDate;
    private Date lastModifiedDate;
    private String description;
    private Integer ConstructionPeriod;
    private BigDecimal balance;
    private Boolean finish;

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }



    public Integer getConstructionPeriod() {
        return ConstructionPeriod;
    }

    public void setConstructionPeriod(Integer constructionPeriod) {
        ConstructionPeriod = constructionPeriod;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getCostEstimate() {
        return costEstimate;
    }

    public void setCostEstimate(BigDecimal costEstimate) {
        this.costEstimate = costEstimate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }


    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }


}
