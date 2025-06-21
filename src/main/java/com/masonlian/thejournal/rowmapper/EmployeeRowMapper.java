package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt("employee_id"));
        employee.setName(rs.getString("name"));
        employee.setWage(rs.getBigDecimal("wage"));
        employee.setImageUrl(rs.getString("image_url"));
        employee.setPhoneNumber(String.valueOf(rs.getInt("phone_number")));

        return employee;
    }
}
