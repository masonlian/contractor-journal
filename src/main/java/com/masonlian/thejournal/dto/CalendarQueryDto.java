package com.masonlian.thejournal.dto;

import com.masonlian.thejournal.constant.ConstructionCategory;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CalendarQueryDto {

    private String projectName;
    private Boolean  finished;
    private ConstructionCategory constructionCategory;
    private BigDecimal dailyExpenses;
    private String notation;
    private Integer eventId;
    private Timestamp eventDate;
    private BigDecimal incidentalExpenses;
    private  Boolean is_weekend;


}
