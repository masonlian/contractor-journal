package com.masonlian.thejournal.dao.daoImpl;

import com.masonlian.thejournal.dao.CalendarDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.CalendarEventRequest;
import com.masonlian.thejournal.model.CalendarEvent;
import com.masonlian.thejournal.rowmapper.CalendarEventRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CalendarDaoImpl implements CalendarDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
    public List<CalendarEvent> getCalendarEventsByDate(QueryPara calendarQueryPara){

        //透過date這個欄位調取event table連接到跟calendar table 回傳一個List的格式給controller層

        String sql = " SELECT e.event_id, e.finished, e.construction_category, e.daily_expenses, e.notation, e.project_name, c.is_weekend   FROM calendar_events AS e LEFT JOIN calendar AS c  ON e.event_date = calendar_date WHERE calendar_date = :calendar_date ";
        Map<String, Object> map = new HashMap<>();
        map.put("calendar_date", calendarQueryPara.getCalendarDate());

        sql= sql+"LIMIT :limit OFFSET :offset";
        map.put("limit", calendarQueryPara.getLimit());
        map.put("offset", calendarQueryPara.getOffset());
        List<CalendarEvent> calendarEventList= namedParameterJdbcTemplate.query(sql, map, new CalendarEventRowMapper());
        if(calendarEventList.size()>0)
            return calendarEventList;
        else return null;
        }

}
