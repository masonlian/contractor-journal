package com.masonlian.thejournal.service;

import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.CalendarEventRequest;
import com.masonlian.thejournal.model.CalendarEvent;

import java.util.Calendar;
import java.util.List;

public interface CalendarService {
   Integer createCalendarEvent(CalendarEventRequest calendarRequest) ;
   void updateCalendarEvent(Integer eventId, CalendarEventRequest calendarEventRequest);
   void deleteCalendarEventById(Integer eventId);
   CalendarEvent getCalendarEventById(Integer eventId);
   List<CalendarEvent> getCalendarEventsByDate(QueryPara calendarQueryPara);

}
