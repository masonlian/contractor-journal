package com.masonlian.thejournal.dao.daoImpl;

import com.masonlian.thejournal.dao.ProjectsDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.model.Project;
import com.masonlian.thejournal.dto.request.ProjectRequest;
import com.masonlian.thejournal.rowmapper.ProjectRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ProjectsDaoImpl implements ProjectsDao {

     @Autowired
     NamedParameterJdbcTemplate namedParameterJdbcTemplate;

     @Override
     public Integer createProject(ProjectRequest projectRequest) {

         String sql = " INSERT INTO projects (project_id, owner, project_name, address ,project_manager ,description, budget , cost_estimate, created_date, last_modified_date) " +
                  " VALUE (project_id, :owner, :project_name, :address ,:project_manager, :description, :budget, :cost_estimate, :created_date, :last_modified_date) ";
         Map<String, Object> map = new HashMap();

         map.put("owner", projectRequest.getOwner());
         map.put("project_name", projectRequest.getProjectName());
         map.put("address", projectRequest.getAddress());
         map.put("project_manager", projectRequest.getProjectManager());
         map.put("description", projectRequest.getDescription());
         map.put("budget", projectRequest.getBudget());
         map.put("cost_estimate", projectRequest.getCostEstimate());


         Date now = new Date();

         map.put("created_date", now);
         map.put("last_modified_date", now);

         KeyHolder keyHolder = new GeneratedKeyHolder();

         namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
         Integer productId = keyHolder.getKey().intValue();
         return productId;
     }

    @Override
    public Project getProjectById(Integer projectId){

         String sql = " SELECT project_id, owner, project_name, address ,project_manager ,description, budget , profit, cost_estimate, created_date, last_modified_date, profit, stage_profit"
                  + " FROM projects WHERE project_id = :project_id";

         Map<String, Object> map = new HashMap();
         map.put("project_id", projectId);
         List<Project> projectList = namedParameterJdbcTemplate.query(sql,map,new ProjectRowMapper());
         if (projectList.size() > 0)
             return projectList.get(0);
         else return null;
    }
    @Override
    public void deleteProjectById(Integer projectId){
         String sql = " DELETE FROM projects WHERE project_id = :project_id" ;
         Map<String, Object> map = new HashMap();
         map.put("project_id", projectId);
         namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void updateProjectById(Integer projectId,ProjectRequest projectRequest) {

        String sql = "UPDATE projects SET project_name =:project_name, owner = :owner,address = :address,budget = :budget,cost_estimate = :cost_estimate, project_manager = :project_manager, description =:description   WHERE project_id = :project_id ";
        Map<String, Object> map = new HashMap();
        map.put("project_id", projectId);
        map.put("project_name", projectRequest.getProjectName());
        map.put("owner", projectRequest.getOwner());
        map.put("address", projectRequest.getAddress());
        map.put("budget", projectRequest.getBudget());
        map.put("cost_estimate", projectRequest.getCostEstimate());
        map.put("project_manager", projectRequest.getProjectManager());
        map.put("description", projectRequest.getDescription());
        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public List<Project> getProjects(QueryPara queryPara){

         String sql = " SELECT  project_id, owner, project_name, address ,project_manager ,description, budget , profit, cost_estimate, created_date, last_modified_date, stage_profit "
                 + " FROM projects WHERE 1=1";

         Map<String ,Object> map = new HashMap();//接著思考根據什麼傳入搜尋條件。

         sql = sql+ " AND address LIKE :search";
         map.put("search","%" + queryPara.getSearch()+ "%");

         sql = sql + " ORDER BY "+ queryPara.getOrderBy()+ " " + queryPara.getSort();
         sql = sql + " LIMIT :limit OFFSET :offset";

         map.put("limit", queryPara.getLimit());
         map.put("offset", queryPara.getOffset());


         List<Project> projectList = namedParameterJdbcTemplate.query(sql,map,new ProjectRowMapper());
         return projectList;
    }

}
