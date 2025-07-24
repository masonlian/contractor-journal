package com.masonlian.thejournal.service;

import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.CreateLaborRoleRequest;
import com.masonlian.thejournal.dto.request.LaborEventQueryRequest;
import com.masonlian.thejournal.model.LaborRole;
import com.masonlian.thejournal.model.Salary;

import java.util.List;


public interface HumanResourceService {
    Integer createProfile(CreateLaborRoleRequest createLaborRoleRequest);
    LaborRole getEmployeeById (Integer employeeId);
    List<LaborRole> getEmployees (QueryPara employeeQueryPara);
    void deleteProfileById (Integer employeeId);
    void  updateProfileById (Integer employeeId, LaborEventQueryRequest laborEventQueryRequest);
    void updateExpectedSalary(Salary laborMonthSalary);
    void updateActualSalary(Salary laborMonthSalary);

    List<Salary> getSalaries(QueryPara  employeeQueryPara);
}
