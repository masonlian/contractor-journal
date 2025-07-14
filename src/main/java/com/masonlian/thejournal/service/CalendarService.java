package com.masonlian.thejournal.service;

import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.*;
import com.masonlian.thejournal.model.*;

import java.math.BigDecimal;
import java.util.List;

public interface CalendarService {
   Integer createCalendarEvent(CalendarEventRequest calendarRequest) ;
   void updateCalendarEvent(Integer eventId, CalendarEventRequest calendarEventRequest);
   void deleteCalendarEventById(Integer eventId);
   CalendarEvent getCalendarEventById(Integer eventId);
   List<CalendarEvent> getCalendarEventsByDate(QueryPara calendarQueryPara);

   void createLaborEvent(Integer eventId, CreateLaborEventRequest createLaborEventRequest);
   List<LaborRole> getAttendancesList(Integer eventId);
   void updateLaborEvent(Integer eventId, LaborEvent laborEvent);
   void deleteLaborEvent(Integer eventId);

   void createMaterialEvent(Integer eventId, MaterialEventRequst createMaterialEventRequest);
   List<MaterialEvent> getMaterialUsedById(Integer eventId);
   void  updateMaterialEvent(Integer eventId, MaterialUsed materialUsed);


   void attendanceCheck(Integer userId, AttendanceRequest attendanceRequest);
   BigDecimal updateDailyExpenses(Integer eventId,BigDecimal newExpenses);
}
