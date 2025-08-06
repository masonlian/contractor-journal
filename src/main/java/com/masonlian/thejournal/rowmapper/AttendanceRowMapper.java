package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.model.Attendance;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceRowMapper implements RowMapper<Attendance> {

    public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {

        Attendance attendance = new Attendance();
        attendance.setAttendanceId(rs.getInt("attendance_id"));
        attendance.setEmployeeId(rs.getInt("employee_id"));
        attendance.setAttendance(rs.getBoolean("is_attendance"));
        attendance.setAttendanceDate(rs.getTimestamp("attendance_date"));
        attendance.setName(rs.getString("name"));

        return attendance;





    }



}
