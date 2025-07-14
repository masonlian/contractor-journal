package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.model.Material;
import com.masonlian.thejournal.model.MaterialEvent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialEventRowMapper implements RowMapper<MaterialEvent> {

    public MaterialEvent mapRow(ResultSet rs, int rowNum ) throws SQLException {

        MaterialEvent materialEvent = new MaterialEvent();
        materialEvent.setEventId(rs.getInt("event_id"));
        materialEvent.setUnit(rs.getInt("unit"));
        materialEvent.setMaterialName(rs.getString("material_name"));
        materialEvent.setMaterialEventId(rs.getInt("material_event_id"));
        materialEvent.setAmount(rs.getBigDecimal("amount"));

        return materialEvent;





    }
}
