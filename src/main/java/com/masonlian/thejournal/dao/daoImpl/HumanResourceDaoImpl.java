package com.masonlian.thejournal.dao.daoImpl;

import com.masonlian.thejournal.dao.HumanResourceDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.AttendanceRequest;
import com.masonlian.thejournal.dto.request.CreateLaborRoleRequest;
import com.masonlian.thejournal.dto.request.LaborEventQueryRequest;
import com.masonlian.thejournal.model.Attendance;
import com.masonlian.thejournal.model.LaborRole;
import com.masonlian.thejournal.model.Salary;
import com.masonlian.thejournal.rowmapper.AttendanceRowMapper;
import com.masonlian.thejournal.rowmapper.EmployeeRowMapper;
import com.masonlian.thejournal.rowmapper.SalaryRowMapper;
import jakarta.validation.OverridesAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HumanResourceDaoImpl implements HumanResourceDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createProfile(CreateLaborRoleRequest createLaborRoleRequest){

        String sql = "INSERT INTO labor_role ( name,  wage, image_url, phone_number, level, role , email , created_date ) VALUES ( :name,:wage, :image_url, :phone_number , :level, :role , :email, :created_date )";
        Map<String,Object> map = new HashMap<>();
        map.put("name",createLaborRoleRequest.getName());
        map.put("wage",createLaborRoleRequest.getWage());
        map.put("image_url",createLaborRoleRequest.getImageUrl());
        map.put("phone_number",createLaborRoleRequest.getPhoneNumber());
        map.put("level",createLaborRoleRequest.getLevel().toString());
        map.put("role",createLaborRoleRequest.getRole().toString());
        map.put("email",createLaborRoleRequest.getEmail());

        Timestamp date = new Timestamp(System.currentTimeMillis()) ;
        map.put("created_date",date);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),  keyHolder);
        Integer employeeId = keyHolder.getKey().intValue();
        return employeeId;

    }

    @Override
    public LaborRole getEmployeeById (Integer employeeId){

        String sql = "SELECT employee_id, name, wage, image_url, phone_number  ,level, role, email, created_date FROM labor_role WHERE employee_id = :employee_id ";
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

        String sql =" SELECT employee_id, name, wage, image_url, phone_number FROM labor_role WHERE 1=1 ";
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

        String sql = "DELETE FROM labor_role  WHERE employee_id = :employeeId";
        Map<String,Object> map = new HashMap<>();
        map.put("employeeId",employeeId);
        namedParameterJdbcTemplate.update(sql,map);

    }

    @Override
    public void updateProfileById (Integer employeeId, LaborEventQueryRequest laborEventQueryRequest){

        String sql = " UPDATE labor_role  SET name = :name, wage = :wage, image_url = :image_url, phone_number= :phone_number where employee_id = :employee_id ";
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

        String sql = "SELECT * FROM labor_role  WHERE name= :name ";
        Map <String, Object>map = new HashMap<>();
        map.put("name",name);

        List<LaborRole> laborRoleList = namedParameterJdbcTemplate.query(sql,map,new EmployeeRowMapper());
        if(laborRoleList.size()>0){

            return laborRoleList.get(0);
        }
        else return null;

    }

    @Override
    public void createAttendance(Timestamp date, List<LaborRole> laborRoleList){

        String sql  = "INSERT attendance  ( attendance_date, name, employee_id ) VALUES  ( :attendance_date, :name, :employee_id ) ";
        MapSqlParameterSource[] mapSqlParameterSource = new MapSqlParameterSource[laborRoleList.size()];
        for(int i = 0 ; i<laborRoleList.size();i++){
            mapSqlParameterSource[i] = new MapSqlParameterSource();
            mapSqlParameterSource[i].addValue("attendance_date", date);
            mapSqlParameterSource[i].addValue(" is_attendance", false );
            mapSqlParameterSource[i].addValue("name", laborRoleList.get(i).getName());
            mapSqlParameterSource[i].addValue("employee_id", laborRoleList.get(i).getEmployeeId());

        }
        namedParameterJdbcTemplate.batchUpdate(sql,mapSqlParameterSource);

    }

    @Override
    public LaborRole getEmployeeByEmail(String email){
        String sql = "SELECT * FROM labor_role WHERE email = :email   ";
        Map<String,Object> map = new HashMap<>();
        map.put("email",email);
        List<LaborRole> laborRoleList =  namedParameterJdbcTemplate.query(sql,map,new EmployeeRowMapper());
        if(laborRoleList.size()>0){
            return laborRoleList.get(0);
        }else return null;

    }

    @Override
    public void isAttendance (Integer employeeId, AttendanceRequest attendanceRequest){

        String sql = " UPDATE attendance  SET is_attendance = :is_attendance WHERE employee_id = :employee_id  ";
        Map<String,Object> map = new HashMap<>();
        map.put( "is_attendance", attendanceRequest.getAttendance() );
        map.put( "employee_id",employeeId);
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void createSalary(Integer month, LaborRole laborRole){

        String sql = "INSERT salary (employee_id, month, expected_salary)  VALUES  (:employee_id, :month, :expected_salary)  ";
        Map<String,Object> map = new HashMap<>();
        map.put("employee_id", laborRole.getEmployeeId());
        map.put("month", month);
        map.put("expected_salary",laborRole.getWage());
        namedParameterJdbcTemplate.update(sql,map);

    }

    @Override
    public Salary getSalary(Integer month, Integer employeeId){

        System.out.println("month為:"+month);


        String sql = "SELECT * FROM salary  WHERE employee_id = :employee_id AND month = :month  ";

        Map<String,Object> map = new HashMap<>();

        map.put("employee_id",employeeId);
        map.put("month", month);

        List<Salary> salaryList = namedParameterJdbcTemplate.query(sql,map , new SalaryRowMapper());

        if(salaryList.size()>0){
            return salaryList.get(0);
        }   else return null;

    }

    @Override
    public void updateExpectedSalary (Salary monthSalary){

        String sql = " UPDATE salary SET  expected_salary = :expected_salary  WHERE employee_id = :employee_id   ";
        Map<String,Object> map = new HashMap<>();
        BigDecimal expectedSalary = monthSalary.getExpectedSalary();
        map.put("expected_salary",expectedSalary);
        map.put("employee_id",monthSalary.getEmployeeId());
        namedParameterJdbcTemplate.update(sql,map);


    }
    @Override
    public void updateActuallySalary (Salary monthSalary){

        String sql =  " UPDATE salary SET attendance_number = :attendance_number , actual_salary = :actual_salary  WHERE employee_id = :employee_id    ";
        Map<String,Object> map = new HashMap<>();
        map.put("attendance_number",monthSalary.getAttendanceNumber());
        map.put("actual_salary",monthSalary.getActualSalary());
        map.put("employee_id",monthSalary.getEmployeeId());
        namedParameterJdbcTemplate.update(sql,map);

    }

    @Override
    public List<Salary> getSalaries(QueryPara  employeeQueryPara){

        String sql = " SELECT * FROM salary  WHERE 1=1   ";
        Map<String,Object> map = new HashMap<>();

        sql = sql + "ORDER BY :orderBy "+" "+ employeeQueryPara.getSort();
        map.put("orderBy",employeeQueryPara.getOrderBy());

        sql = sql+ " LIMIT :limit OFFSET :offset";

        List<Salary> salaries =  namedParameterJdbcTemplate.query(sql,map,new SalaryRowMapper());
        if(salaries.size()>0){
            return salaries;
        }else return null;


    }

    @Override
    public List<Attendance> getAttendanceRecord(String name){

        String sql = " SELECT * FROM attendance  WHERE name = :name ";

        Map<String,Object> map = new HashMap<>();
        map.put("name",name);

        List<Attendance> attendanceList = namedParameterJdbcTemplate.query(sql,map,new AttendanceRowMapper());
        if(attendanceList.size()>0){
            return attendanceList;
        }else return null;


    }

}
