package com.masonlian.thejournal.dto.request;

import com.masonlian.thejournal.constant.ConstructionCategory;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class NewReceived {

    public Integer projectId;
    @NotNull
    public String name;
    public BigDecimal receivedPayment;

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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
