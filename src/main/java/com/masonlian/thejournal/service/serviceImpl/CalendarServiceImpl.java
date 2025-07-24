package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.dao.CalendarDao;
import com.masonlian.thejournal.dao.HumanResourceDao;
import com.masonlian.thejournal.dao.ProjectsDao;
import com.masonlian.thejournal.config.CustomUserDetails;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.*;
import com.masonlian.thejournal.model.*;
import com.masonlian.thejournal.service.CalendarService;
import com.masonlian.thejournal.service.HumanResourceService;
import com.masonlian.thejournal.service.ProjectsService;
import com.masonlian.thejournal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    CalendarDao calendarDao;
    @Autowired
    HumanResourceDao humanResourceDao;

    @Autowired
    private UserService userService;
    @Autowired
    private ProjectsService projectsService;
    @Autowired
    private ProjectsDao projectsDao;
    @Autowired
    private HumanResourceService humanResourceService;


    //之後可以的話調用資料盡量都用id不要用名字
    @Override
    public Integer createCalendarEvent(CalendarEventRequest calendarEventRequest){

        Project project = projectsDao.getProjectByName(calendarEventRequest.getProjectName());
        Integer period = project.getConstructionPeriod();
        period =  period + 1;
        projectsDao.updatePeriod(project.getProjectId() ,period);

        return calendarDao.createCalendarEvent(calendarEventRequest);

    }

    @Override
    public void updateCalendarEvent(Integer eventId, CalendarEventRequest calendarEventRequest){

        Project project = projectsDao.getProjectByName(calendarEventRequest.getProjectName());
        Integer projectId = project.getProjectId();

        if(calendarEventRequest.getFinished()==true){
           projectsService.updateProfitById(projectId);
        }

        calendarDao.updateCalendarEvent(eventId, calendarEventRequest);
    }

    @Override
    public void deleteCalendarEventById(Integer eventId){
        CalendarEvent calendarEvent= getCalendarEventById(eventId);
        Project project = projectsDao.getProjectByName(calendarEvent.getProjectName());
        Integer period =  project.getConstructionPeriod();
        period =  period - 1;
        projectsDao.updatePeriod(project.getProjectId(), period);

        calendarDao.deleteCalendarEventById(eventId);
    }

    @Override
    public CalendarEvent getCalendarEventById(Integer eventId){

        return calendarDao.getCalendarEventById(eventId);
    }

    @Override
    public List<CalendarEvent> getCalendarEventsByDate(QueryPara calendarQueryPara){

        return calendarDao.getCalendarEventsByDate(calendarQueryPara);


    }

    @Override
    public void createLaborEvent(Integer eventId ,CreateLaborEventRequest createLaborEventRequest){

        CalendarEvent calendarEvent  = calendarDao.getCalendarEventById(eventId);
        Integer month  = calendarEvent.getEventDate().toLocalDateTime().getMonthValue();
        Timestamp date =  calendarEvent.getEventDate();

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<LaborRole> laborRoleList = new ArrayList<>();

        for(LaborRole laborRole:laborRoleList){

            LaborRole attendance= humanResourceDao.getEmployeeByName(laborRole.getName());




            laborRole.setLevel(attendance.getLevel());
            laborRole.setRole(attendance.getRole());
            laborRole.setEmployeeId(attendance.getEmployeeId());
            laborRole.setName(attendance.getName());
            laborRole.setWage(attendance.getWage());
            laborRole.setPhoneNumber(attendance.getPhoneNumber());
            laborRole.setImageUrl(attendance.getImageUrl());

            totalAmount.add(laborRole.getWage());

            Salary laborMonthSalary  =  humanResourceDao.getSalary(month,laborRole.getEmployeeId());

            if(laborMonthSalary!=null){

                humanResourceDao.createSalary(month,laborRole);

            }

            else humanResourceService.updateExpectedSalary(laborMonthSalary);


        }

        updateDailyExpenses(eventId, totalAmount);

        calendarDao.createLaborEvent(eventId,laborRoleList);
        calendarDao.updateWagePerDay(eventId,totalAmount);




        humanResourceDao.createAttendance(date,laborRoleList);

    }

    //
    // 從create 和update labor/material event的當下 就要更新updateDaily的計算了 incident_expense 為常數
    @Override
    public BigDecimal updateDailyExpenses(Integer eventId,BigDecimal newExpenses){

        CalendarEvent calendarEvent = calendarDao.getCalendarEventById(eventId);
        String projectName = calendarEvent.getProjectName();
        BigDecimal dailyExpense = calendarEvent.getDailyExpenses();

        dailyExpense.add(newExpenses);

        calendarDao.updateDailyExpenses(eventId,dailyExpense);

        projectsService.updateBalanceByDailyExpense(projectName ,dailyExpense);


        return dailyExpense;

    }




    //好像可以增加一個是否出席的功能

    @Override
    public List<LaborRole> getAttendancesList(Integer eventId){

        return  calendarDao.getAttendancesList(eventId);

    }

    @Override
    public void updateLaborEvent(Integer eventId, LaborEvent laborEvent){

        BigDecimal beforeWage = BigDecimal.ZERO;
        BigDecimal afterWage = BigDecimal.ZERO;

        List<LaborRole> beforeLaborRoleList= calendarDao.getAttendancesList(eventId);

        for(LaborRole laborRole: beforeLaborRoleList){

           beforeWage = beforeWage.add(laborRole.getWage());

        }

        calendarDao.updateLaborEvent(eventId, laborEvent);


        List<LaborRole> afterLaborRoleList= calendarDao.getAttendancesList(eventId);

        for(LaborRole laborRole: afterLaborRoleList){

            afterWage = afterWage.add(laborRole.getWage());

        }

        calendarDao.updateWagePerDay(eventId,afterWage);

        BigDecimal difference  = afterWage.subtract(beforeWage);

        updateDailyExpenses(eventId,difference);

    }

    @Override
    public void deleteLaborEvent(Integer eventId){

        BigDecimal budgetCut = BigDecimal.ZERO;
        List<LaborRole>laborRoleList=  getAttendancesList(eventId);

        for(LaborRole laborRole: laborRoleList){

            budgetCut = budgetCut.subtract(laborRole.getWage());
        }

        updateDailyExpenses(eventId,budgetCut);

        calendarDao.deleteLaborEvent(eventId);


    }




    @Override
    public void attendanceCheck(Integer eventId, CustomUserDetails user, AttendanceRequest attendanceRequest){



        CalendarEvent calendarEvent = calendarDao.getCalendarEventById(eventId);
        Timestamp eventDate =  calendarEvent.getEventDate();
        LocalDateTime eventDateTime = eventDate.toLocalDateTime();
        LocalDate evenDate = eventDateTime.toLocalDate();

        Timestamp checkTime = new Timestamp(System.currentTimeMillis());
        LocalDate checkDate = checkTime.toLocalDateTime().toLocalDate();




        //將userId轉換為 laborId
        String email = user.getUsername();
        LaborRole laborRole = humanResourceDao.getEmployeeByEmail(email);
        Integer employeeId =  laborRole.getEmployeeId();


        //scope先放寬到“日”的級距
        if( evenDate ==  checkDate ) {

            calendarDao.laborAttend(eventId, employeeId, attendanceRequest);

            humanResourceDao.isAttendance(employeeId ,attendanceRequest );

            Integer month = checkDate.getMonthValue();
            Salary salary = humanResourceDao.getSalary(month,eventId);

            humanResourceService.updateActualSalary(salary);

        }

        else throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE," 並非上班日期！") ;
    }

    @Override
    public Integer finishProject(Integer eventId, CalendarEventRequest calendarEventRequest){

        String name = calendarEventRequest.getProjectName();
        Project project = projectsDao.getProjectByName(name);

        if(calendarEventRequest.getFinished()==true){


            projectsService.updateProfitById(project.getProjectId());
            projectsDao.finishProject(project.getProjectId(),calendarEventRequest.getFinished());

            return calendarDao.finishProject(eventId,calendarEventRequest);

        } else return null;
    }


}
