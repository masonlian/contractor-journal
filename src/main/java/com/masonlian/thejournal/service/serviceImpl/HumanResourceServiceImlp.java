package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.dao.HumanResourceDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.CreateLaborRoleRequest;
import com.masonlian.thejournal.dto.request.LaborEventQueryRequest;
import com.masonlian.thejournal.model.LaborRole;
import com.masonlian.thejournal.model.Salary;
import com.masonlian.thejournal.service.FinancialService;
import com.masonlian.thejournal.service.HumanResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class HumanResourceServiceImlp implements HumanResourceService {

    @Autowired
    private HumanResourceDao humanResourceDao;
    @Autowired
    private FinancialService financialService;


    @Override
    public Integer createProfile(CreateLaborRoleRequest createLaborRoleRequest) {


        return humanResourceDao.createProfile(createLaborRoleRequest);

    }

    @Override
    public LaborRole getEmployeeById(Integer employeeId) {

        return humanResourceDao.getEmployeeById(employeeId);
    }

    @Override
    public List<LaborRole> getEmployees(QueryPara employeeQueryPara) {

        return humanResourceDao.getEmployees(employeeQueryPara);

    }

    @Override
    public void deleteProfileById(Integer employeeId) {

        humanResourceDao.deleteProfileById(employeeId);

    }
    @Override
    public void  updateProfileById (Integer employeeId, LaborEventQueryRequest laborEventQueryRequest){

      humanResourceDao.updateProfileById(employeeId, laborEventQueryRequest);
    }


    @Override
    public void updateExpectedSalary( Salary laborMonthSalary) {

        LaborRole laborRole = humanResourceDao.getEmployeeById(laborMonthSalary.getEmployeeId());

        BigDecimal expectedSalary =  laborMonthSalary.getExpectedSalary().add(laborRole.getWage());
        laborMonthSalary.setExpectedSalary(expectedSalary);

        humanResourceDao.updateExpectedSalary(laborMonthSalary);

    }

    @Override
    public void updateActualSalary(Salary laborMonthSalary) {

       Integer attendance =  laborMonthSalary.getAttendanceNumber()+1;
       laborMonthSalary.setAttendanceNumber(attendance);

       LaborRole laborRole = humanResourceDao.getEmployeeById(laborMonthSalary.getEmployeeId());
       BigDecimal actualSalary =  laborMonthSalary.getActualSalary().add(laborRole.getWage());
       laborMonthSalary.setActualSalary(actualSalary);

       humanResourceDao.updateActuallySalary(laborMonthSalary);
       financialService.updateLaborCost(laborMonthSalary.getActualSalary());

    }


}