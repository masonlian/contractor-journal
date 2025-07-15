package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.model.Salary;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaryRowMapper implements RowMapper<Salary> {

    public Salary mapRow(ResultSet rs, int rowNum) throws SQLException {
        Salary salary = new Salary();

        salary.setMonth(rs.getInt("month"));
        salary.setEmployeeId(rs.getInt("employee_id"));
        salary.setAttendanceNumber(rs.getInt("attendance_number"));
        salary.setActualSalary(rs.getBigDecimal("actual_salary"));
        salary.setExpectedSalary(rs.getBigDecimal("expected_salary"));

        return salary;

    }
}
