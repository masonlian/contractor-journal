package com.masonlian.thejournal.model;

import java.math.BigDecimal;
import java.util.List;

public class LaborEvent {
    private Integer laborEventId;
    private Integer eventId;
    private String name;

    public boolean isAttend() {
        return attend;
    }

    public void setAttend(boolean attend) {
        this.attend = attend;
    }

    private boolean attend;

    public BigDecimal getWage() {
        return wage;
    }

    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }

    private BigDecimal wage;

    public Integer getLaborEventId() {
        return laborEventId;
    }

    public void setLaborEventId(Integer laborEventId) {
        this.laborEventId = laborEventId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
