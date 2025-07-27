package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.model.Construction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConstructionRowMapper implements RowMapper<Construction> {

    public Construction mapRow (ResultSet rs, int rowNum) throws SQLException {

        Construction construction = new Construction();
        construction.setConstructionItem(rs.getString("construction_item"));
        construction.setConstructionId(rs.getInt("construction_id"));
        construction.setConstructionSpec(rs.getString("construction_spec"));
        construction.setConstructionCategory(rs.getString("construction_category"));
        construction.setConstructionEstimate(rs.getBigDecimal("construction_estimate"));

        return construction;

    }
}
