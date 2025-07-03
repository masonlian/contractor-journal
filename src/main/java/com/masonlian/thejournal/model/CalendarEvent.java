package com.masonlian.thejournal.model;

import com.masonlian.thejournal.constant.ConstructionCategory;

import java.math.BigDecimal;
import java.security.Timestamp;

public class CalendarEvent {

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private String projectName;
    private Boolean  finished;
    private ConstructionCategory constructionCategory;
    private BigDecimal dailyExpenses;
    private String notation;
    private Integer eventId;

    public Boolean getIs_weekend() {
        return is_weekend;
    }

    public void setIs_weekend(Boolean is_weekend) {
        this.is_weekend = is_weekend;
    }

    private  Boolean is_weekend;


    public Timestamp getEventDate() {
        return eventDate;
    }

    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
    }

    private Timestamp eventDate;


    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

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


}
