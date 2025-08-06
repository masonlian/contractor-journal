package com.masonlian.thejournal.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LaborAttendance {


    private Boolean attend = Boolean.FALSE;
    private String name;


    public Boolean getAttend() {
        return attend;
    }

    public void setAttend(Boolean attend) {
        this.attend = attend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
