package com.masonlian.thejournal.dto.request;

import java.util.List;

//其實可以不用List的Request格式。
public class CreateLaborEventRequest {

    List<LaborAttendance> laborAttendanceList;


    public List<LaborAttendance> getLaborAttendanceList() {
        return laborAttendanceList;
    }

    public void setLaborAttendanceList(List<LaborAttendance> laborAttendanceList) {
        this.laborAttendanceList = laborAttendanceList;
    }
}
