package com.masonlian.thejournal.controller;

import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.*;
import com.masonlian.thejournal.model.*;
import com.masonlian.thejournal.service.CalendarService;
import com.masonlian.thejournal.service.ProjectsService;
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

//在實作的過程當中如果有感覺不對勁地方通常代表something really go wrong.

@RestController
public class CalenderController {
    @Autowired
    CalendarService calendarService;
    ProjectsService projectsService;


    //日曆功能
    @PostMapping("event")
    public ResponseEntity<CalendarEvent> createCalendarEvent(@RequestBody CalendarEventRequest calendarEventRequest) {

        Integer eventId = calendarService.createCalendarEvent(calendarEventRequest);

        calendarService.updateDailyExpenses(eventId,calendarEventRequest.getIncidentalExpenses());

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

    @PostMapping("/calendar/labor/{eventId}")
    public ResponseEntity <?> createLaborEvent(@PathVariable Integer eventId, @RequestBody CreateLaborEventRequest createLaborEventRequest) {

        calendarService.createLaborEvent(eventId,createLaborEventRequest);//回傳人力事件的列表id
        return ResponseEntity.status(HttpStatus.CREATED).build();


    }

    //排班功能
    @PostMapping("/calendar/material/{eventId}")
    public ResponseEntity <?> createMaterialEvent(@PathVariable Integer eventId, @RequestBody MaterialEventRequst materialEventRequst) {

        calendarService.createMaterialEvent(eventId, materialEventRequst);//回傳人力事件的列表id
        return ResponseEntity.status(HttpStatus.CREATED).build();


    }

    @GetMapping("/calendar/labor/eventId")
    public ResponseEntity<List<LaborRole>>  getAttendancesList( @PathVariable Integer eventId ){

        List<LaborRole> laborRoleList =  calendarService.getAttendancesList(eventId);
        return ResponseEntity.status(HttpStatus.OK).body(laborRoleList);


    }

    @PostMapping("/calendar/{eventId}")
    public ResponseEntity<LaborRole> updateLaborEvent(@PathVariable Integer eventId , @RequestBody LaborEvent laborEvent) {

        calendarService.updateLaborEvent(eventId,laborEvent);
        return ResponseEntity.status(HttpStatus.OK).build();


    }

    @DeleteMapping("/calendar/{eventId}")
    public ResponseEntity<LaborRole> deleteLaborEventById(@PathVariable Integer eventId) {

        calendarService.deleteLaborEvent(eventId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }


    @PutMapping("/calendar/{userIdI}/attendance")
    public ResponseEntity<Boolean> AttendanceCheck(@PathVariable Integer userId , @RequestBody AttendanceRequest attendanceRequest) {

        calendarService.attendanceCheck(userId,attendanceRequest);

        return ResponseEntity.status(HttpStatus.OK).build();



    }


    @GetMapping("/calendar/material/{eventId}")
    public ResponseEntity<List<MaterialEvent>> getMaterialUsed(@PathVariable Integer eventId) {

        List<MaterialEvent> materialList = calendarService.getMaterialUsedById(eventId);

        return ResponseEntity.status(HttpStatus.OK).body(materialList);


    }


    @PutMapping("/calendar/material/{eventId}")
    public ResponseEntity<MaterialEvent> updateMaterialEvent(@PathVariable Integer eventId, @RequestBody MaterialUsed materialUsed) {

        calendarService.updateMaterialEvent(eventId,materialUsed);
        return ResponseEntity.status(HttpStatus.OK).build();


    }





}

