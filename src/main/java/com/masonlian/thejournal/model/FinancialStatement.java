package com.masonlian.thejournal.model;

import java.math.BigDecimal;

public class FinancialStatement {
    private Integer statementId;
    private Integer quarter;
    private BigDecimal received;
    private BigDecimal materialPayable;
    private BigDecimal laborCost;
    private BigDecimal profit;
    private String summary;

    public Integer getStatementId() {
        return statementId;
    }

    public void setStatementId(Integer statementId) {
        this.statementId = statementId;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public BigDecimal getMaterialPayable() {
        return materialPayable;
    }

    public void setMaterialPayable(BigDecimal materialPayable) {
        this.materialPayable = materialPayable;
    }

    public BigDecimal getReceived() {
        return received;
    }

    public void setReceived(BigDecimal received) {
        this.received = received;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
