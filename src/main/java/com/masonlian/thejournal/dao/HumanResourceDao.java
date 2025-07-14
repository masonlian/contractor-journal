package com.masonlian.thejournal.dao;

import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.LaborEventQueryRequest;
import com.masonlian.thejournal.model.LaborRole;

import java.util.List;


public interface HumanResourceDao {

    Integer createProfile(LaborEventQueryRequest laborEventQueryRequest);
    LaborRole getEmployeeById (Integer employeeId);
    List<LaborRole> getEmployees (QueryPara employeeQueryPara);
    void deleteProfileById (Integer employeeId);
    void  updateProfileById (Integer employeeId, LaborEventQueryRequest laborEventQueryRequest);
    LaborRole getEmployeeByName(String Name);
}
