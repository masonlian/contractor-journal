package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.model.LaborEvent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LaborEventRowMapper implements RowMapper<LaborEvent> {

    public LaborEvent mapRow(ResultSet rs, int rowNum ) throws SQLException {

        LaborEvent laborEvent = new LaborEvent();

        laborEvent.setEventId(rs.getInt("event_id"));
        laborEvent.setLaborEventId(rs.getInt("labor_event_id"));
        laborEvent.setName(rs.getString("name"));
        laborEvent.setAttend(rs.getBoolean("attend"));
        laborEvent.setEmployeeId(rs.getInt("employee_id"));

        return laborEvent;

    }
}
