package com.masonlian.thejournal.dao;

import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.CalendarEventRequest;
import com.masonlian.thejournal.model.CalendarEvent;

import java.util.Calendar;
import java.util.List;

public interface CalendarDao {
    Integer createCalendarEvent(CalendarEventRequest calendarEventRequest);
    void updateCalendarEvent(Integer eventId, CalendarEventRequest calendarEventRequest);
    void deleteCalendarEventById(Integer eventId);
    CalendarEvent getCalendarEventById(Integer eventId);
    List<CalendarEvent> getCalendarEventsByDate(QueryPara calendarQueryPara);
}
