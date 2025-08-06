package com.masonlian.thejournal.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.masonlian.thejournal.constant.ConstructionCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class CalendarEventRequest {


    private String projectName;
    private ConstructionCategory constructionCategory;
    private BigDecimal dailyExpenses = BigDecimal.ZERO;
    private String notation;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;
    private BigDecimal incidentalExpenses;
    private Boolean finished = Boolean.FALSE;


    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }



    public BigDecimal getIncidentalExpenses() {
        return incidentalExpenses;
    }

    public void setIncidentalExpenses(BigDecimal incidentalExpenses) {
        this.incidentalExpenses = incidentalExpenses;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    private Integer eventId;

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
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


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


}
