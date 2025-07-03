package com.masonlian.thejournal.dto.request;

import com.masonlian.thejournal.constant.ConstructionCategory;

import java.math.BigDecimal;
import java.security.Timestamp;

public class CalendarEventRequest {

    private String projectName;
    private Boolean  finished;
    private ConstructionCategory constructionCategory;
    private BigDecimal dailyExpenses;
    private String notation;

    public Timestamp getEventDate() {
        return eventDate;
    }

    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
    }

    private Timestamp eventDate;


    public ConstructionCategory getConstructionCategory() {
        return constructionCategory;
    }

    public void setConstructionCategory(ConstructionCategory constructionCategory) {
        this.constructionCategory = constructionCategory;
    }

    public BigDecimal getDailyExpenses() {
        return dailyExpenses;
    }

    public void setDailyExpenses(BigDecimal dailyExpenses) {
        this.dailyExpenses = dailyExpenses;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


}
