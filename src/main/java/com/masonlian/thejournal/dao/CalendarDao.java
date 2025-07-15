package com.masonlian.thejournal.dao;

import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.AttendanceRequest;
import com.masonlian.thejournal.dto.request.CalendarEventRequest;
import com.masonlian.thejournal.dto.request.MaterialEventRequst;
import com.masonlian.thejournal.dto.request.MaterialUsed;
import com.masonlian.thejournal.model.*;

import java.math.BigDecimal;
import java.util.List;

public interface CalendarDao {
    Integer createCalendarEvent(CalendarEventRequest calendarEventRequest);
    void updateCalendarEvent(Integer eventId, CalendarEventRequest calendarEventRequest);
    void deleteCalendarEventById(Integer eventId);
    CalendarEvent getCalendarEventById(Integer eventId);
    List<CalendarEvent> getCalendarEventsByDate(QueryPara calendarQueryPara);
    void createLaborEvent(Integer eventId ,List<LaborRole> laborRoleList);
    void updateWagePerDay(Integer eventId, BigDecimal totalAmount);
    void createMaterialEvent(Integer eventId, List<MaterialEvent>  materialEventList);
    void updateMaterialCost(Integer eventId,BigDecimal totalAmount);
    List<LaborRole> getAttendancesList(Integer eventId);
    void updateLaborEvent(Integer eventId,LaborEvent laborEvent);
    void deleteLaborEvent(Integer eventId);
    void attendanceCheck(LaborRole laborRole, AttendanceRequest attendanceRequest);
    void updateDailyExpenses(Integer eventId,BigDecimal newExpenses);
    List<MaterialEvent> getMaterialUsedById(Integer eventId);
    void  updateMaterialEvent(Integer eventId, MaterialEvent materialEvent);
    Integer finishProject(Integer eventId, CalendarEventRequest calendarEventRequest);
    void laborAttend(Integer eventId ,Integer employeeId,AttendanceRequest attendanceRequest );


}
