package com.masonlian.thejournal.dao.daoImpl;

import com.masonlian.thejournal.dao.CalendarDao;
import com.masonlian.thejournal.dao.ProjectsDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.*;
import com.masonlian.thejournal.model.*;
import com.masonlian.thejournal.rowmapper.CalendarEventRowMapper;
import com.masonlian.thejournal.rowmapper.MaterialEventRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CalendarDaoImpl implements CalendarDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private ProjectsDao projectsDao;

    @Override
    public Integer createCalendarEvent(CalendarEventRequest calendarEventRequest) {

        String sql = " INSERT INTO  calendar_events ( event_id, project_name,finished, construction_category,daily_expenses, notation, event_date)  VALUES  ( :event_id, :project_id,finished, :construction_category, :daily_expenses, :notation, :event_date) ";
        Map<String, Object> map = new HashMap<>();
        map.put("project_neame", calendarEventRequest.getProjectName());
        map.put("finished", calendarEventRequest.getFinished());
        map.put("construction_category", calendarEventRequest.getConstructionCategory());
        map.put("daily_expenses", calendarEventRequest.getDailyExpenses());
        map.put("notation", calendarEventRequest.getNotation());
        map.put("event_date", calendarEventRequest.getEventDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        Integer eventId = keyHolder.getKey().intValue();
        return eventId;
    }

    @Override
    public CalendarEvent getCalendarEventById(Integer eventId) {

        String sql = " SELECT project_name, finished, construction_category, daily_expense, notation, event_date FROM calendar_events WHERE event_id = :event_id";
        Map<String, Object> map = new HashMap<>();
        map.put("event_id", eventId);
        List<CalendarEvent> calendarEventList = namedParameterJdbcTemplate.query(sql, map, new CalendarEventRowMapper());
        if (calendarEventList.size() > 0)
            return calendarEventList.get(0);
        else
            return null;
    }

    @Override
    public void deleteCalendarEventById(Integer eventId) {

        String sql = "  DELETE FROM calendar_events WHERE event_id = :event_id";
        Map<String, Object> map = new HashMap<>();
        map.put("event_id", eventId);
        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void updateCalendarEvent(Integer eventId, CalendarEventRequest calendarEventRequest) {
        String sql = " UPDATE  calendar_events SET  finished= :finished, construction_category= :construction_category, daily_expenses= :daily_expenses, notation= :notation, event_date= :event_date project_name= project_name WHERE event_id = :event_id ";
        Map<String, Object> map = new HashMap<>();
        map.put("event_id", eventId);
        map.put("finished", calendarEventRequest.getFinished());
        map.put("construction_category", calendarEventRequest.getConstructionCategory());
        map.put("daily_expenses", calendarEventRequest.getDailyExpenses());
        map.put("notation", calendarEventRequest.getNotation());
        map.put("event_date", calendarEventRequest.getEventDate());
        map.put("project_name", calendarEventRequest.getProjectName());
        namedParameterJdbcTemplate.update(sql, map);
    }


    @Override
    public List<CalendarEvent> getCalendarEventsByDate(QueryPara calendarQueryPara) {

        //透過date這個欄位調取event table連接到跟calendar table 回傳一個List的格式給controller層

        String sql = " SELECT e.event_id, e.finished, e.construction_category, e.daily_expenses, e.notation, e.project_name, c.is_weekend   FROM calendar_events AS e LEFT JOIN calendar AS c  ON e.event_date = calendar_date WHERE calendar_date = :calendar_date ";
        Map<String, Object> map = new HashMap<>();
        map.put("calendar_date", calendarQueryPara.getCalendarDate());

        sql = sql + "LIMIT :limit OFFSET :offset";
        map.put("limit", calendarQueryPara.getLimit());
        map.put("offset", calendarQueryPara.getOffset());
        List<CalendarEvent> calendarEventList = namedParameterJdbcTemplate.query(sql, map, new CalendarEventRowMapper());
        if (calendarEventList.size() > 0)
            return calendarEventList;
        else return null;

    }

    @Override
    public void createLaborEvent(Integer eventId ,List<LaborRole> laborRoleList){



        for (LaborRole laborRole : laborRoleList) {
            String sql = "INSERT labor_event (event_id, name ) VALUES ( :event_id, :name) ";
            Map<String, Object> map = new HashMap<>();

            map.put("event_id", eventId);
            map.put("name", laborRole.getName());

            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        }


    }

    @Override
    public void updateWagePerDay(Integer eventId, BigDecimal totalAmount) {

        String sql = "UPDATE calendar_events SET wage_per_day = :wage_per_day WHERE event_id = :event_id  ";
        Map<String, Object> map = new HashMap<>();
        map.put("event_id", eventId);
        map.put("wage_per_day", totalAmount);
        namedParameterJdbcTemplate.update(sql, map);
    }




    @Override
    public  List<LaborRole> getAttendancesList(Integer eventId){

        String sql = " SELECT name,attend From calendar_events WHERE event_id = :event_id  ";
        Map<String, Object> map = new HashMap<>();
        map.put("event_id", eventId);
        List<LaborRole> attendanceList =  namedParameterJdbcTemplate.query(sql,map, new CalendarEventRowMapper());
        if (attendanceList.size() > 0){
            return attendanceList;
        }
        else return null;

    }
    @Override
    public void updateLaborEvent(Integer eventID ,LaborEvent laborEvent){

        String sql = "UPDATE calendar_events SET attend=:attend WHERE name = :name and event_id = :event_id  ";
        Map<String, Object> map = new HashMap<>();
        map.put("name", laborEvent.getName());
        map.put("attend",laborEvent.isAttend());
        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void deleteLaborEvent(Integer eventId){

        String sql = "DELETE  FROM calendar_events WHERE event_id = :event_id  ";
        Map<String, Object> map = new HashMap<>();
        map.put("event_id", eventId);
        namedParameterJdbcTemplate.update(sql, map);

    }


    @Override
    public void updateDailyExpenses(Integer eventId,BigDecimal newExpenses){

        String sql = " UPDATE calendar_events SET daily_expenses = :daily_expenses WHERE event_id = :event_id     ";
        Map<String, Object> map = new HashMap<>();
        map.put("daily_expenses", newExpenses);
        namedParameterJdbcTemplate.update(sql, map);


    }

    @Override
    public List<MaterialEvent> getMaterialUsedById(Integer eventId){

        String sql = " SELECT material_name ,unit, amount, material_events_id FROM material_events WHERE event_id = :event_id  ";

        Map<String, Object> map = new HashMap<>();
        map.put("event_id", eventId);
        List <MaterialEvent> userdList = namedParameterJdbcTemplate.query(sql,map,new MaterialEventRowMapper());
        if (userdList.size() > 0){
            return userdList;
        }
        else return null;


    }


    @Override
    public Integer finishProject(Integer eventId, CalendarEventRequest calendarEventRequest){

        String sql = " UPDATE calendar_events SET finished = :finished WHERE event_id = :event_id ";
        Map<String, Object> map = new HashMap<>();
        map.put("event_id", eventId);
        map.put("finished", calendarEventRequest.getFinished());
        namedParameterJdbcTemplate.update(sql, map);

        Project project =  projectsDao.getProjectByName(projectsDao.getProjectByName(calendarEventRequest.getProjectName()).getProjectName());
        if (project != null){
            return project.getProjectId();
        }
        else return null;

    }

    @Override
    public void laborAttend(Integer eventId , Integer employeeId,AttendanceRequest attendanceRequest ){
        String sql =  " UPDATE  labor_events SET attend = :attend WHERE event_id = :event_id  AND employee_id = :employee_id  ";
        Map<String, Object> map = new HashMap<>();
        map.put("event_id", eventId);
        map.put("attend", attendanceRequest.getAttendance());
        map.put("employee_id", employeeId);
        namedParameterJdbcTemplate.update(sql, map);

    }



}
