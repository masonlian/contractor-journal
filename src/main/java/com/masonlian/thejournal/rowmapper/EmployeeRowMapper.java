package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.model.LaborRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class EmployeeRowMapper implements RowMapper<LaborRole> {

    public LaborRole mapRow(ResultSet rs, int rowNum) throws SQLException {
        LaborRole laborRole = new LaborRole();
        laborRole.setEmployeeId(rs.getInt("employee_id"));
        laborRole.setName(rs.getString("name"));
        laborRole.setWage(rs.getBigDecimal("wage"));
        laborRole.setImageUrl(rs.getString("image_url"));
        laborRole.setPhoneNumber(String.valueOf(rs.getInt("phone_number")));

        return laborRole;
    }
}
