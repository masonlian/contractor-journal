package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.dao.CalendarDao;
import com.masonlian.thejournal.dao.CostMgmtDao;
import com.masonlian.thejournal.dao.HumanResourceDao;
import com.masonlian.thejournal.dao.ProjectsDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.*;
import com.masonlian.thejournal.model.*;
import com.masonlian.thejournal.service.CalendarService;
import com.masonlian.thejournal.service.ProjectsService;
import com.masonlian.thejournal.service.UserService;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Component
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    CalendarDao calendarDao;
    @Autowired
    HumanResourceDao humanResourceDao;
    @Autowired
    CostMgmtDao costMgmtDao;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectsService projectsService;
    @Autowired
    private ProjectsDao projectsDao;

    @Override
    public Integer createCalendarEvent(CalendarEventRequest calendarEventRequest){

        return calendarDao.createCalendarEvent(calendarEventRequest);

    }

    @Override
    public void updateCalendarEvent(Integer eventId, CalendarEventRequest calendarEventRequest){
        calendarDao.updateCalendarEvent(eventId, calendarEventRequest);
    }

    @Override
    public void deleteCalendarEventById(Integer eventId){
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
        }

        updateDailyExpenses(eventId, totalAmount);

        calendarDao.createLaborEvent(eventId,laborRoleList);
        calendarDao.updateWagePerDay(eventId,totalAmount);

    }

    @Override
    public void createMaterialEvent(Integer eventId, MaterialEventRequst createMaterialEventRequest){



        BigDecimal totalAmount = BigDecimal.ZERO;
        List<MaterialEvent> materialEventList = new ArrayList<>();

        for(MaterialUsed materialUsed:createMaterialEventRequest.getUsedList()){

            Material material = costMgmtDao.getMaterialByName(materialUsed.getMaterialName());

            BigDecimal unit=  BigDecimal.valueOf(materialUsed.getUnit());
            totalAmount.add(material.getUnitPrice().multiply(unit) );

            MaterialEvent materialEvent = new MaterialEvent();

            materialEvent.setEventId(eventId);
            materialEvent.setMaterialName(materialUsed.getMaterialName());
            materialEvent.setAmount( material.getUnitPrice().multiply(unit));
            materialEvent.setUnit(materialUsed.getUnit());

            materialEventList.add(materialEvent);

        }

        updateDailyExpenses(eventId,totalAmount);


        calendarDao.createMaterialEvent(eventId, materialEventList);

        calendarDao.updateMaterialCost(eventId,totalAmount);

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
    public void attendanceCheck(Integer userId, AttendanceRequest attendanceRequest){


        //將userId轉換為 laborId
        User user = userService.getUserById(userId);
        LaborRole laborRole = humanResourceDao.getEmployeeByName(user.getName());


        calendarDao.attendanceCheck(laborRole,attendanceRequest);

    }

    @Override
    public List<MaterialEvent> getMaterialUsedById(Integer eventId){

        return  calendarDao.getMaterialUsedById(eventId);

    }

    @Override
    public void  updateMaterialEvent(Integer eventId, MaterialUsed materialUsed){

        CalendarEvent calendarEvent = calendarDao.getCalendarEventById(eventId);

        BigDecimal beforeCost = calendarEvent.getMaterialCost();
        BigDecimal afterCost = BigDecimal.ZERO;

        MaterialEvent updateEvent = new MaterialEvent();
        Material material = costMgmtDao.getMaterialByName(materialUsed.getMaterialName());

        updateEvent.setEventId(eventId);
        updateEvent.setMaterialName(materialUsed.getMaterialName());
        updateEvent.setUnit(materialUsed.getUnit());

        BigDecimal unitPrice =  material.getUnitPrice();
        BigDecimal unit= BigDecimal.valueOf(materialUsed.getUnit());
        BigDecimal amount = unitPrice.multiply(unit);

        updateEvent.setAmount(amount);

        calendarDao.updateMaterialEvent(eventId, updateEvent);

        List<MaterialEvent> materialEventList = calendarDao.getMaterialUsedById(eventId);

        for(MaterialEvent materialEvent: materialEventList){

            afterCost = afterCost.add(materialEvent.getAmount());


        }


        calendarDao.updateMaterialCost(eventId,afterCost);

        BigDecimal difference  = afterCost.subtract(beforeCost);
        updateDailyExpenses(eventId,difference);


    }







}
