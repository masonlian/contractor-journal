package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.dao.HumanResourceDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.EmployeeRequest;
import com.masonlian.thejournal.model.Employee;
import com.masonlian.thejournal.service.HumanResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HumanResourceServiceImlp implements HumanResourceService {

    @Autowired
    private HumanResourceDao humanResourceDao;


    @Override
    public Integer createProfile(EmployeeRequest employeeRequest) {

        return humanResourceDao.createProfile(employeeRequest);

    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {

        return humanResourceDao.getEmployeeById(employeeId);
    }

    @Override
    public List<Employee> getEmployees(QueryPara employeeQueryPara) {

        return humanResourceDao.getEmployees(employeeQueryPara);

    }

    @Override
    public void deleteProfileById(Integer employeeId) {

        humanResourceDao.deleteProfileById(employeeId);

    }
    @Override
    public void  updateProfileById (Integer employeeId,EmployeeRequest employeeRequest){

      humanResourceDao.updateProfileById(employeeId,employeeRequest );
    }
}