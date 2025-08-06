package com.masonlian.thejournal.controller;

import com.masonlian.thejournal.config.CustomUserDetails;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.*;
import com.masonlian.thejournal.model.*;
import com.masonlian.thejournal.service.CalendarService;
import com.masonlian.thejournal.service.ProjectsService;
import com.masonlian.thejournal.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

//在實作的過程當中如果有感覺不對勁地方通常代表something really go wrong.

@RestController
public class CalenderController {
    @Autowired
    CalendarService calendarService;

    @Autowired
    ProjectsService projectsService;


    //日曆功能
    //@PreAuthorize("hasAnyAuthority('L0','L1')")
    @PostMapping("/calendar/event")
    public ResponseEntity<CalendarEvent> createCalendarEvent(@RequestBody CalendarEventRequest calendarEventRequest) {

        Integer eventId = calendarService.createCalendarEvent(calendarEventRequest);
        calendarService.updateDailyExpenses(eventId,calendarEventRequest.getIncidentalExpenses());


        CalendarEvent calendarEvent = calendarService.getCalendarEventById(eventId);
        return ResponseEntity.status(HttpStatus.CREATED).body(calendarEvent);

    }

    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @DeleteMapping("/calendar/event/{eventId}")
    public ResponseEntity<CalendarEvent> deleteCalendarEventById(@PathVariable Integer eventId) {

        calendarService.deleteCalendarEventById(eventId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @PutMapping("/calendar/event/{eventId}")
    public ResponseEntity<CalendarEvent> updateCalendarEvent(@PathVariable Integer eventId, @RequestBody CalendarEventRequest calendarEventRequest) {

        calendarService.updateCalendarEvent(eventId, calendarEventRequest);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    //假設前端使用ISO8601字串格式傳入時間資訊 =>轉換成LacalDate去把範圍內的資料call出來
    //平行取得資料
    //@PreAuthorize("hasAnyAuthority('L0','L1','L2')")
    @GetMapping("/calendar/{localDate}")
    public ResponseEntity<Page<Calendar>> getEventsByCalendarDate(@RequestParam (name="limit", defaultValue = "10")@Max(100) @Min(0) Integer limit,
                                                                  @RequestParam (defaultValue = "0") @Min(0) Integer offset,
                                                                  @PathVariable @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate localDate) {
        QueryPara calendarQueryPara = new QueryPara();

        calendarQueryPara.setLimit(limit);
        calendarQueryPara.setOffset(offset);
        calendarQueryPara.setCalendarDate(localDate);

        List<CalendarEvent> calendarList =calendarService.getCalendarEventsByDate(calendarQueryPara);

        Page calendarPage = new Page();

        calendarPage.setTotal(calendarList.size());
        calendarPage.setLimit(limit);
        calendarPage.setOffset(offset);
        calendarPage.setResult(calendarList);

        return ResponseEntity.status(HttpStatus.OK).body(calendarPage);
    }


    //分段提交
    //@PreAuthorize("hasAnyAuthority('L0','L1')")
    @PostMapping("/calendar/event/{eventId}/labor")
    public ResponseEntity <List<LaborRole>> createLaborEvent(@PathVariable Integer  eventId,  @RequestBody CreateLaborEventRequest createLaborEventRequest) {

       List<LaborRole>  attendanceList =  calendarService.createLaborEvent(eventId, createLaborEventRequest); //回傳人力事件的列表id

        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceList);


    }

    //排班功能

    @GetMapping("/calendar/{eventId}/labor")
    public ResponseEntity<List<LaborRole>>  getAttendancesList( @PathVariable Integer eventId ){

        List<LaborRole> laborRoleList =  calendarService.getAttendancesList(eventId);
        return ResponseEntity.status(HttpStatus.OK).body(laborRoleList);


    }

    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @PostMapping("/calendar/{eventId}")
    public ResponseEntity<LaborRole> updateLaborEvent(@PathVariable Integer eventId , @RequestBody LaborEvent laborEvent) {

        calendarService.updateLaborEvent(eventId,laborEvent);
        return ResponseEntity.status(HttpStatus.OK).build();


    }

    @PreAuthorize("hasAnyAuthority('L0','L1')")
    @DeleteMapping("/calendar/{eventId}")
    public ResponseEntity<LaborRole> deleteLaborEventById(@PathVariable Integer eventId) {

        calendarService.deleteLaborEvent(eventId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }


    //將使用者的資訊外包給前端自動送入後端 ，也就是說
    @PreAuthorize("hasAnyAuthority('L0','L1','L2','L3')")
    @PutMapping("/calendar/{eventId}/attendance")
    public ResponseEntity<Boolean> AttendanceCheck(@PathVariable Integer eventId, @AuthenticationPrincipal CustomUserDetails user, @RequestBody AttendanceRequest attendanceRequest) {

        System.out.println("使用者為:" + user.getUsername()+ "，使用者ID為: "+ user.getUserId()  );

        calendarService.attendanceCheck(eventId,user,attendanceRequest);

        return ResponseEntity.status(HttpStatus.OK).build();

    }



    //@PreAuthorize("hasAnyAuthority('L0','L1')")
    @PutMapping("/calendar/finish/{eventId}")
    public ResponseEntity<Project> finishProject(@PathVariable Integer eventId , @RequestBody  FinishProjectRequest finishProjectRequest) {

        System.out.println("前端傳來的參數為:"+finishProjectRequest.getFinish());

        Integer projectId = calendarService.finishProject(eventId,finishProjectRequest);
        Project project = projectsService.getProjectById(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(project);

    }

}

