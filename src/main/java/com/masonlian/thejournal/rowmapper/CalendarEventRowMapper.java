package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.constant.ConstructionCategory;
import com.masonlian.thejournal.model.CalendarEvent;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarEventRowMapper implements RowMapper {

    public CalendarEvent  mapRow(ResultSet rs, int rowNum ) throws SQLException {
        CalendarEvent event = new CalendarEvent();

        event.setEventId(rs.getInt("event_id"));
        event.setDailyExpenses(rs.getBigDecimal("daily_expenses"));
        event.setFinished(rs.getBoolean("finished"));
        event.setNotation(rs.getString("notation"));
        event.setProjectName(rs.getString("project_name"));

        String categoryStr = rs.getString("construction_category");
        ConstructionCategory category= ConstructionCategory.valueOf(categoryStr);
        event.setConstructionCategory(category);
        event.setIs_weekend(rs.getBoolean("is_weekend"));
        return event;
    }

}
