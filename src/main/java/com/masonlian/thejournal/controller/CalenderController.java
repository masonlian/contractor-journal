package com.masonlian.thejournal.controller;

import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.CalendarEventRequest;
import com.masonlian.thejournal.model.CalendarEvent;
import com.masonlian.thejournal.service.CalendarService;
import com.masonlian.thejournal.util.Page;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController//日曆功能透過join創建
public class CalenderController {
    @Autowired
    CalendarService calendarService;

    @PostMapping("event")
    public ResponseEntity<CalendarEvent> createCalendarEvent(@RequestBody CalendarEventRequest calendarEventRequest) {

        Integer eventId = calendarService.createCalendarEvent(calendarEventRequest);
        CalendarEvent calendarEvent = calendarService.getCalendarEventById(eventId);
        return ResponseEntity.status(HttpStatus.CREATED).body(calendarEvent);

    }

    @DeleteMapping("/event/{eventId}")
    public ResponseEntity<CalendarEvent> deleteCalendarEventById(@PathVariable Integer eventId) {

        calendarService.deleteCalendarEventById(eventId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/event/{eventId}")
    public ResponseEntity<CalendarEvent> updateCalendarEvent(@PathVariable Integer eventId, @RequestBody CalendarEventRequest calendarEventRequest) {

        calendarService.updateCalendarEvent(eventId, calendarEventRequest);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/calendar")
    public ResponseEntity<Page<Calendar>> getEventsByCalendarDate(@RequestParam (name="limit", defaultValue = "10")@Max(100)Integer limit,
                                                                  @RequestParam (defaultValue = "0") @Min(0) Integer offset,
                                                                  @PathVariable  Date calendarDate) {
        QueryPara calendarQueryPara = new QueryPara();
        calendarQueryPara.setLimit(limit);
        calendarQueryPara.setOffset(offset);
        calendarQueryPara.setCalendarDate(calendarDate);
        List<CalendarEvent> calendarList =calendarService.getCalendarEventsByDate(calendarQueryPara);

        Page calendarPage = new Page();

        calendarPage.setTotal(calendarList.size());
        calendarPage.setLimit(limit);
        calendarPage.setOffset(offset);
        calendarPage.setTotal(calendarList.size());

        return ResponseEntity.status(HttpStatus.OK).body(calendarPage);
    }

}

