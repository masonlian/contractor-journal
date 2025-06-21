package com.masonlian.thejournal.service;

import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.EmployeeRequest;
import com.masonlian.thejournal.model.Employee;
import org.springframework.stereotype.Component;

import java.util.List;


public interface HumanResourceService {
    Integer createProfile(EmployeeRequest employeeRequest);
    Employee getEmployeeById (Integer employeeId);
    List< Employee> getEmployees (QueryPara employeeQueryPara);
    void deleteProfileById (Integer employeeId);
    void  updateProfileById (Integer employeeId,EmployeeRequest employeeRequest);
}
