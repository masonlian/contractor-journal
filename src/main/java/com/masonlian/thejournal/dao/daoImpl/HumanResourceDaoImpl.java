package com.masonlian.thejournal.dao.daoImpl;

import com.masonlian.thejournal.dao.HumanResourceDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.LaborEventQueryRequest;
import com.masonlian.thejournal.model.LaborRole;
import com.masonlian.thejournal.rowmapper.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HumanResourceDaoImpl implements HumanResourceDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createProfile(LaborEventQueryRequest laborEventQueryRequest){

        String sql = "INSERT INTO human_resource (  name,  wage, image_url, phone_number) VALUES ( :name,:wage, :image_url, :phoneNumber)";
        Map<String,Object> map = new HashMap<>();

        map.put("name", laborEventQueryRequest.getName());
        map.put("wage", laborEventQueryRequest.getWage());
        map.put("phoneNumber", laborEventQueryRequest.getPhoneNumber());
        map.put("image_url", laborEventQueryRequest.getImageUrl());


        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),  keyHolder);
        Integer employeeId = keyHolder.getKey().intValue();
        return employeeId;

    }

    @Override
    public LaborRole getEmployeeById (Integer employeeId){

        String sql = "SELECT employee_id, name, wage, image_url, phone_number FROM human_resource WHERE employee_id = :employee_id ";
        Map <String, Object>map = new HashMap<>();
        map.put("employee_id",employeeId);

        List<LaborRole> laborRoleList = namedParameterJdbcTemplate.query(sql,map,new EmployeeRowMapper());
        if(laborRoleList.size()>0){

            return laborRoleList.get(0);
        }
        else return null;

    }
@Override
public List<LaborRole> getEmployees (QueryPara employeeQueryPara){

        String sql =" SELECT employee_id, name, wage, image_url, phone_number FROM human_resource WHERE 1=1 ";
        Map<String, Object> map = new HashMap<>();

        if(employeeQueryPara.getJoblevel() != null )
            sql += " AND level = :level";
            map.put("level",employeeQueryPara.getJoblevel());
        if(employeeQueryPara.getSearch() !=null )
            sql += "AND name LIKE :=search";
            map.put("search","%"+employeeQueryPara.getSearch()+"%");

        sql += "ORDER BY"+ employeeQueryPara.getOrderBy()+" "+ employeeQueryPara.getSort();


        List <LaborRole> laborRoleList = namedParameterJdbcTemplate.query(sql,map,new EmployeeRowMapper());
        return laborRoleList;
}
    @Override
    public void deleteProfileById (Integer employeeId){

        String sql = "DELETE FROM human_resource WHERE employee_id = :employeeId";
        Map<String,Object> map = new HashMap<>();
        map.put("employeeId",employeeId);
        namedParameterJdbcTemplate.update(sql,map);

    }

    @Override
    public void updateProfileById (Integer employeeId, LaborEventQueryRequest laborEventQueryRequest){

        String sql = " UPDATE human_resource SET name = :name, wage = :wage, image_url = :image_url, phone_number= :phone_number where employee_id = :employee_id ";
        Map<String,Object> map = new HashMap<>();
        map.put("name", laborEventQueryRequest.getName());
        map.put("wage", laborEventQueryRequest.getWage());
        map.put("image_url", laborEventQueryRequest.getImageUrl());
        map.put("phone_number", laborEventQueryRequest.getPhoneNumber());
        map.put("employee_id",employeeId);
        namedParameterJdbcTemplate.update(sql,map);

    }

    @Override
    public LaborRole getEmployeeByName(String name){

        String sql = "SELECT employee_id, name, wage, image_url, phone_number FROM human_resource WHERE employee_id = :employee_id ";
        Map <String, Object>map = new HashMap<>();
        map.put("name",name);

        List<LaborRole> laborRoleList = namedParameterJdbcTemplate.query(sql,map,new EmployeeRowMapper());
        if(laborRoleList.size()>0){

            return laborRoleList.get(0);
        }
        else return null;

    }

}
