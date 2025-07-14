package com.masonlian.thejournal.dto.request;

public class AttendanceRequest {
    public Boolean attendance;
    public Boolean getAttendance() {
        return attendance;
    }

    public Integer eventId;
    public Integer getEventId() {
        return eventId;
    }
    public void setEventId(Integer eventId) {}
}
