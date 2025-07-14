package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.dao.HumanResourceDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.LaborEventQueryRequest;
import com.masonlian.thejournal.model.LaborRole;
import com.masonlian.thejournal.service.HumanResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HumanResourceServiceImlp implements HumanResourceService {

    @Autowired
    private HumanResourceDao humanResourceDao;


    @Override
    public Integer createProfile(LaborEventQueryRequest laborEventQueryRequest) {

        return humanResourceDao.createProfile(laborEventQueryRequest);

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
}