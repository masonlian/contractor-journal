package com.masonlian.thejournal.dao;

import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.AttendanceRequest;
import com.masonlian.thejournal.dto.request.CreateLaborRoleRequest;
import com.masonlian.thejournal.dto.request.LaborEventQueryRequest;
import com.masonlian.thejournal.model.LaborRole;
import com.masonlian.thejournal.model.Salary;

import java.sql.Timestamp;
import java.util.List;


public interface HumanResourceDao {

    Integer createProfile(CreateLaborRoleRequest createLaborRoleRequest);
    LaborRole getEmployeeById (Integer employeeId);
    List<LaborRole> getEmployees (QueryPara employeeQueryPara);
    void deleteProfileById (Integer employeeId);
    void  updateProfileById (Integer employeeId, LaborEventQueryRequest laborEventQueryRequest);
    LaborRole getEmployeeByName(String Name);
    //出缺席功能
    void createAttendance(Timestamp date,List<LaborRole> laborRoleList);
    LaborRole getEmployeeByEmail(String email);
    void isAttendance (Integer employeeId, AttendanceRequest attendanceRequest);
    //薪資功能
    void createSalary(Integer month, LaborRole laborRole);
    Salary getSalary(Integer month,Integer employeeId);
    void updateExpectedSalary(Salary monthSalary);
    void updateActuallySalary (Salary monthSalary);
    List<Salary> getSalaries(QueryPara  employeeQueryPara);
}
