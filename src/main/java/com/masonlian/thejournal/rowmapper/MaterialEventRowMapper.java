package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.model.MaterialEvent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialEventRowMapper implements RowMapper<MaterialEvent> {

    public MaterialEvent mapRow(ResultSet rs, int rowNum ) throws SQLException {

        MaterialEvent materialEvent = new MaterialEvent();
        materialEvent.setMaterialEventId(rs.getInt("material_eventId"));
        materialEvent.setProjectId(rs.getInt("project_id"));
        materialEvent.setCreatedDate(rs.getTimestamp("created_date"));
        materialEvent.setTotalAmount(rs.getBigDecimal("total_amount"));

        return materialEvent;





    }
}
