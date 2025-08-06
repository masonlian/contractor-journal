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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class CalendarServiceImpl implements CalendarService {

    final static Logger logger = LoggerFactory.getLogger(CalendarServiceImpl.class);

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
    @Transactional
    @Override
    public Integer createCalendarEvent(CalendarEventRequest calendarEventRequest){

        Project project = projectsDao.getProjectByName(calendarEventRequest.getProjectName());
        System.out.println("專案ID為:"+project.getProjectId());

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
    public List<LaborRole> createLaborEvent(Integer eventId ,CreateLaborEventRequest createLaborEventRequest){



        CalendarEvent calendarEvent  = calendarDao.getCalendarEventById(eventId);

        Integer month  = calendarEvent.getEventDate().getMonthValue();
        Timestamp date = Timestamp.valueOf(calendarEvent.getEventDate().atStartOfDay());

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<LaborRole> laborRoleList = new ArrayList<>();

        for(LaborAttendance attendance :createLaborEventRequest.getLaborAttendanceList()){

            LaborRole attendLabor = humanResourceDao.getEmployeeByName(attendance.getName());

            if(attendLabor == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"該員工並不存在");
            }



            System.out.println("出席人員為:"+attendLabor.getName());

            BigDecimal wageForOne = attendLabor.getWage();


            totalAmount =  totalAmount.add(wageForOne);
            laborRoleList.add(attendLabor);


            Salary laborMonthSalary  =  humanResourceDao.getSalary(month,attendLabor.getEmployeeId());

            if(laborMonthSalary == null){

                humanResourceDao.createSalary(month,attendLabor);

            }

            else humanResourceService.updateExpectedSalary(laborMonthSalary);

        }

        System.out.println("單日薪資為:"+totalAmount);


        calendarDao.createLaborEvent(eventId,laborRoleList);

        updateWagePerDay(eventId,totalAmount);

        projectsService.updateBalanceByWagePerDay(calendarEvent.getProjectName(),totalAmount);//計算ba

        humanResourceDao.createAttendance(date,laborRoleList);

        return laborRoleList ;
    }

    void updateWagePerDay(Integer eventId,BigDecimal newAmount){

        CalendarEvent calendarEvent = getCalendarEventById(eventId);
        BigDecimal newWagePerDay =  calendarEvent.getWagePerDay().add(newAmount);
        calendarDao.updateWagePerDay(eventId,newWagePerDay);

    }


    //
    // 從create 和update labor/material event的當下 就要更新updateDaily的計算了 incident_expense 為常數
    @Transactional
    @Override
    public void updateDailyExpenses(Integer eventId,BigDecimal newExpenses){

        CalendarEvent calendarEvent = calendarDao.getCalendarEventById(eventId);
        String projectName = calendarEvent.getProjectName();
        BigDecimal dailyExpense = calendarEvent.getDailyExpenses();

        dailyExpense = dailyExpense.add(newExpenses);

        calendarDao.updateDailyExpenses(eventId,dailyExpense);

        projectsService.updateBalanceByDailyExpense(projectName ,dailyExpense);

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

        LocalDate evenDate = calendarEvent.getEventDate();

        //Timestamp checkTime = new Timestamp(System.currentTimeMillis());
        //LocalDate checkDate = checkTime.toLocalDateTime().toLocalDate();

        String strDate = "2025-08-04";
        LocalDate checkDate = LocalDate.parse(strDate);



        //將userId轉換為 laborId
        String userName= user.getUsername();
        LaborRole laborRole = humanResourceDao.getEmployeeByName(userName);
        Integer employeeId =  laborRole.getEmployeeId();

        System.out.println("eventDate為:"+ evenDate + "checkDate為:"+checkDate );


        //scope先放寬到“日”的級距
        if( evenDate.equals(checkDate) ) {

            calendarDao.laborAttend(eventId, employeeId, attendanceRequest);

            humanResourceDao.isAttendance(employeeId ,attendanceRequest );


            Integer month = checkDate.getMonthValue();

            System.out.println("月份為:"+month);

            Salary salary = humanResourceDao.getSalary(month,employeeId);

            System.out.println("取得名細結果為:"+salary.getEmployeeId());



            humanResourceService.updateActualSalary(salary);

        }

        else throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE," 並非上班日期！") ;
    }



    @Transactional
    @Override
    public Integer finishProject(Integer eventId, FinishProjectRequest finishProjectRequest ) {

        Boolean finish = finishProjectRequest.getFinish();
        System.out.println("Service所接收到的參數為:"+finish);

        CalendarEvent calendarEvent = calendarDao.getCalendarEventById(eventId);

        String name = calendarEvent.getProjectName();
        Project project = projectsDao.getProjectByName(name);


        if(finish ==  true){


            projectsService.updateProfitById(project.getProjectId());
            projectsDao.finishProject(project.getProjectId(),finishProjectRequest.getFinish());

            return calendarDao.finishProject(eventId,finishProjectRequest);

        } else return null;
    }


}
