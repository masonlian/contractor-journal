package com.masonlian.thejournal.dto.request;

import com.masonlian.thejournal.constant.ConstructionCategory;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class NewReceived {

    public Integer projectId;
    public String name;
    public BigDecimal receivedPayment;
    public ConstructionCategory constructionCategory;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getReceivedPayment() {
        return receivedPayment;
    }

    public void setReceivedPayment(BigDecimal receivedPayment) {
        this.receivedPayment = receivedPayment;
    }

    public ConstructionCategory getConstructionCategory() {
        return constructionCategory;
    }

    public void setConstructionCategory(ConstructionCategory constructionCategory) {
        this.constructionCategory = constructionCategory;
    }
}
