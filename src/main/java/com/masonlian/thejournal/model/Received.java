package com.masonlian.thejournal.model;

import com.masonlian.thejournal.constant.ConstructionCategory;

import java.math.BigDecimal;
import java.sql.Timestamp;


//敏感資訊操作需加密
public class Received {
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer projectId;
    public Integer received_id;
    public String name;
    public BigDecimal receivedPayment;
    public ConstructionCategory constructionCategory;
    public Timestamp receivedTime;

    public Integer getReceived_id() {
        return received_id;
    }

    public void setReceived_id(Integer received_id) {
        this.received_id = received_id;
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

    public Timestamp getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Timestamp receivedTime) {
        this.receivedTime = receivedTime;
    }
}
