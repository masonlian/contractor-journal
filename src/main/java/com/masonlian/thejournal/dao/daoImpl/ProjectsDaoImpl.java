package com.masonlian.thejournal.dao.daoImpl;

import com.masonlian.thejournal.dao.ProjectsDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.QuotationWithItemDto;
import com.masonlian.thejournal.dto.request.QuotationRequest;
import com.masonlian.thejournal.model.Project;
import com.masonlian.thejournal.dto.request.ProjectRequest;
import com.masonlian.thejournal.model.Quotation;
import com.masonlian.thejournal.model.QuotationItem;
import com.masonlian.thejournal.rowmapper.ProjectRowMapper;
import com.masonlian.thejournal.rowmapper.QuotationItemRowMapper;
import com.masonlian.thejournal.rowmapper.QuotationRowMapper;
import com.masonlian.thejournal.rowmapper.QuotationWithItemRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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

    @Override
    public Project getProjectByName(String projectName){
         String sql = " SELECT * FROM projects WHERE project_name = :project_name  ";
         Map<String, Object> map = new HashMap();
         map.put("project_name", projectName);
         List<Project> projectList =   namedParameterJdbcTemplate.query(sql,map,new ProjectRowMapper());
         if(projectList.size()>0){

             return projectList.get(0);
         } else return null;


    }
    @Override
    public void updateCostEstimate(Integer projectId,BigDecimal costEstimate){
         String sql  =  "UPDATE projects SET cost_estimate = :cost_estimate WHERE project_id = :project_id    ";
         Map<String, Object> map = new HashMap();
         map.put("cost_estimate", costEstimate);
         map.put("project_id", projectId);
         namedParameterJdbcTemplate.update(sql, map);


    }

    @Override
    public Integer createQuotation(Integer projectId, QuotationRequest quotationRequest){

         String sql = "INSERT quotation (project_id, created_date, create_by, status, summary ) VALUES ( :project_id, :created_date, :create_by, :status, :summary ) WHERE quotation_id = :quotation_id  ";
         Map<String, Object> map = new HashMap();
         map.put("project_id", projectId);
         map.put("create_by", quotationRequest.getCreateBy());
         map.put("status", quotationRequest.getStatus());
         map.put("summary", quotationRequest.getSummary());
         Date date = quotationRequest.getCreateDate();
         map.put("created_date", date);

         KeyHolder keyHolder = new GeneratedKeyHolder();
         namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map), keyHolder );
         Integer quotationId=  keyHolder.getKey().intValue();
         return quotationId;
    }

    @Override
    public Quotation getQuotationById(Integer quotationId){

         String sql = "SELECT * FROM quotations WHERE quotation_id = :quotation_id   ";
         Map<String, Object> map = new HashMap();
         map.put("quotation_id", quotationId);
         List<Quotation> quotationList =namedParameterJdbcTemplate.query(sql,map,new QuotationRowMapper());
         if(quotationList.size()>0){
             return quotationList.get(0);
         }else return null;

    }


    @Override
    public void createQuotationItem(List<QuotationItem>  quotationItemList){

        String sql =  "INSERT quotation_item  ( quotation_id, material_name, material_unit, material_spec, material_amount, construct_item, construct_unit, construct_spec,construct_amount ) VALUES ( :quotation_id, :material_name, :material_unit, :material_spec, :material_amount, :construct_item, :construct_unit, :construct_spec, :construct_amount)  ";
        MapSqlParameterSource [] mapSqlParameterSource =  new MapSqlParameterSource[quotationItemList.size()];
        for(int i = 0; i< quotationItemList.size(); i++){

            mapSqlParameterSource[i]=new MapSqlParameterSource();
            mapSqlParameterSource[i].addValue("quotation_id", quotationItemList.get(i).getQuotationId());

            mapSqlParameterSource[i].addValue("material_name", quotationItemList.get(i).getMaterialName());
            mapSqlParameterSource[i].addValue("material_unit", quotationItemList.get(i).getMaterialUnit());
            mapSqlParameterSource[i].addValue("material_spec", quotationItemList.get(i).getMaterialSpec());
            mapSqlParameterSource[i].addValue("material_amount", quotationItemList.get(i).getMaterialAmount());

            mapSqlParameterSource[i].addValue("construct_item", quotationItemList.get(i).getConstructionItem());
            mapSqlParameterSource[i].addValue("construct_unit", quotationItemList.get(i).getConstructionUnit());
            mapSqlParameterSource[i].addValue("construct_spec", quotationItemList.get(i).getConstructionSpec());
            mapSqlParameterSource[i].addValue("construct_amount", quotationItemList.get(i).getConstructionAmount());

        }

        namedParameterJdbcTemplate.batchUpdate(sql, mapSqlParameterSource);

    }

    @Override
    public void updateTotalAmount(Integer quotationId, BigDecimal totalAmount){

         String sql = " UPDATE quotation SET total_amount = :total_amount WHERE quotation_id = :quotation_id  ";
         Map<String, Object> map = new HashMap();
         map.put("total_amount", totalAmount);
         map.put("quotation_id", quotationId);
         namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public List<QuotationWithItemDto> getQuotations (Integer projectId){

         String sql = "SELECT  qi.* , q.* FROM qi AS quotation_item LEFT JOIN q AS quotation ON qi.quotation_id = q.quotation_id WHERE project_id = :project_id ";

         Map<String, Object> map = new HashMap();
         map.put("project_id", projectId);
         List <QuotationWithItemDto> quotationWithItemDtoList =namedParameterJdbcTemplate.query(sql,map,new QuotationWithItemRowMapper() );
         if(quotationWithItemDtoList.size()>0){
             return quotationWithItemDtoList;
         } else return null;

    }










}
