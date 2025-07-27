package com.masonlian.thejournal.dao.daoImpl;

import com.masonlian.thejournal.dao.CostMgmtDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.ConstructionRequest;
import com.masonlian.thejournal.dto.request.CreateConstructionRequest;
import com.masonlian.thejournal.dto.request.CreateMaterialRequest;
import com.masonlian.thejournal.dto.request.MaterialRequest;
import com.masonlian.thejournal.model.*;
import com.masonlian.thejournal.rowmapper.AccountPayableRowMapper;
import com.masonlian.thejournal.rowmapper.ConstructionRowMapper;
import com.masonlian.thejournal.rowmapper.MaterialEventRowMapper;
import com.masonlian.thejournal.rowmapper.MaterialMgmtRowMapper;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class CostMgmtDaoImpl implements CostMgmtDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void createMaterial(List<Material> materialList){


        String sql = " INSERT INTO material (construction_category, unit_price, image_url, specification, material_name ,supplier )" +
                " VALUE ( :construction_category, :unit_price, :image_url, :specification, :material_name, :supplier ) ";

        MapSqlParameterSource [] mapSqlParameterSources = new MapSqlParameterSource[materialList.size()];
        for( int i = 0; i < materialList.size()  ; i++ ){

            mapSqlParameterSources[i] = new MapSqlParameterSource();
            mapSqlParameterSources[i].addValue("construction_category", materialList.get(i).getConstructionCategory());
            mapSqlParameterSources[i].addValue("unit_price", materialList.get(i).getUnitPrice());
            mapSqlParameterSources[i].addValue("image_url", materialList.get(i).getImageUrl());
            mapSqlParameterSources[i].addValue("specification", materialList.get(i).getSpecification());
            mapSqlParameterSources[i].addValue("material_name", materialList.get(i).getMaterialName());
            mapSqlParameterSources[i].addValue("supplier", materialList.get(i).getSupplier());

        }

        namedParameterJdbcTemplate.batchUpdate(sql, mapSqlParameterSources);

    }

    @Override
    public Material getMaterialById (Integer materialId){

        String sql= "SELECT material_type, unit_price, image_url, specification, material_name  FROM material WHERE material_id = :material_id  ";

        Map<String, Object> map = new HashMap<>();
        map.put("material_id", materialId);
        List <Material> materialList = namedParameterJdbcTemplate.query(sql,map, new MaterialMgmtRowMapper());
        if(materialList.size() >0)
            return materialList.get(0);
        return null;

    }

    @Override
    public void deleteMaterialById(Integer materialId) {
        String sql= "DELETE material_id, material_type, unit_price, image_url, specification, material_name FROM material WHERE material_id = :material_id  ";

        Map<String, Object> map = new HashMap<>();
        map.put("material_id", materialId);
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void updateMaterialById(Integer materialId, MaterialRequest materialRequest) {

        String sql= "UPDATE  material SET   material_type = :material_type, unit_price = :unit_price, image_url = :image_url, specification :=specification , material_name= material_name WHERE  material_id = :material_id ";
        Map<String, Object> map = new HashMap<>();
        map.put("material_id", materialId);
        map.put("material_type", materialRequest.getConstructionCategory());
        map.put("unit_price", materialRequest.getUnitPrice());
        map.put("image_url", materialRequest.getImageUrl());
        map.put("specification", materialRequest.getSpecification());
        map.put("material_name", materialRequest.getMaterialName());
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public Material getMaterialByName(String materialName) {

        String sql = "SELECT material_id, material_type, unit_price, image_url, specification WHERE material_name = :material_name  ";
        Map<String, Object> map = new HashMap<>();
        map.put("material_name", materialName);
        List<Material> materialList = namedParameterJdbcTemplate.query(sql, map, new MaterialMgmtRowMapper());
        if(materialList.size() >0)
            return materialList.get(0);
        else return null;
    }
    @Override
    public int [] createConstruction(List<Construction> constructionList){

        String sql =  "INSERT construction ( construction_item, construction_spec, construction_estimate, construction_category ) VALUES ( :construction_item, :construction_spec, :construction_estimate, :construction_category )";

        MapSqlParameterSource [] mapSqlParameterSource = new MapSqlParameterSource[constructionList.size()];

        for(int i = 0 ; i < constructionList.size() ; i++){

            mapSqlParameterSource[i] = new MapSqlParameterSource();

            mapSqlParameterSource[i].addValue("construction_item", constructionList.get(i).getConstructionItem());
            mapSqlParameterSource[i].addValue("construction_spec", constructionList.get(i).getConstructionSpec());
            mapSqlParameterSource[i].addValue("construction_estimate", constructionList.get(i).getConstructionEstimate());
            mapSqlParameterSource[i].addValue("construction_category", constructionList.get(i).getConstructionCategory());

        }

      int [] result =namedParameterJdbcTemplate.batchUpdate(sql, mapSqlParameterSource);
        if(result.length > 0){
            return result;
        }else return null;


    }

    @Override
    public Construction getConstructionById(Integer constructionId){

        String sql =   " SELECT (construction_item, construction_spec, construction_estimate, media_repository_category) FROM construction WHERE  construction_id = :construction_id   ";
        Map<String, Object> map = new HashMap<>();
        map.put("construction_id", constructionId);

        List<Construction>  constructionList =  namedParameterJdbcTemplate.query(sql,map, new ConstructionRowMapper());
        if(constructionList.size() >0)
            return constructionList.get(0);
        else return null;


    }

    @Override
    public void deleteConstructionById(Integer constructionId) {

        String sql = "DELETE FROM  construction WHERE  construction_id = :construction_id    ";
        Map<String, Object> map = new HashMap<>();
        map.put("construction_id", constructionId);
        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void updateConstructionBydId (Integer constructionId,ConstructionRequest constructionRequest){

        String sql = "UPDATE construction SET construction_item = :construction_item ,construction_spec = :construction_spec, construction_estimate = :construction_estimate, media_repository_category = :media_repository_category WHERE construction_id = :construction_id   ";
        Map<String, Object> map = new HashMap<>();
        map.put("construction_id", constructionId);
        map.put("construction_item", constructionRequest.getConstructionItem());
        map.put("construction_spec", constructionRequest.getConstructionSpec());
        map.put("construction_estimate", constructionRequest.getConstructionEstimate());

        namedParameterJdbcTemplate.update(sql, map);

    }
    @Override
    public List<Material> getMaterials(QueryPara queryPara){

        String sql = "SELECT * FROM material WHERE 1=1 ";
        Map<String, Object> map = new HashMap<>();

        if(queryPara.getConstructionCategory() != null)
        sql = sql + " construction_category = :construction_category ";
        map.put("construction_category", queryPara.getConstructionCategory());
        if(queryPara.getSearch() != null)
        sql = sql + " AND  material_name LIKE :search ";
        map.put("search", queryPara.getSearch());

        sql = sql + " ORDER BY "+queryPara.getOrderBy() + " " +queryPara.getSort();

        sql= sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", queryPara.getLimit());
        map.put("offset", queryPara.getOffset());

        List<Material> materialList =  namedParameterJdbcTemplate.query(sql, map, new MaterialMgmtRowMapper());
        if(materialList.size() >0)
            return materialList;
        else return null;
        }



 public List<Construction> getConstructionItems(QueryPara queryPara) {

     String sql = "SELECT * FROM material WHERE 1=1 ";
     Map<String, Object> map = new HashMap<>();

     if (queryPara.getSearch() != null) {

         sql = sql + " AND material_name LIKE :search  ";
         map.put("search", queryPara.getSearch());
     }

     sql = sql + " ORDER BY " + queryPara.getOrderBy() + " " + queryPara.getSort();
     sql = sql + " LIMIT :limit OFFSET :offset";
     map.put("limit", queryPara.getLimit());
     map.put("offset", queryPara.getOffset());
     List<Construction> constructionList = namedParameterJdbcTemplate.query(sql, map, new ConstructionRowMapper());
     if(constructionList.size() >0)
         return constructionList;
     else return null;


 }
 @Override
 public Construction getConstructionItemByName (String constructionItem){

        String sql = "SELECT * FROM construction WHERE construction_item = :construction_item ";
        Map<String, Object> map = new HashMap<>();
        map.put("construction_item", constructionItem);
        List<Construction> constructionList = namedParameterJdbcTemplate.query(sql, map, new ConstructionRowMapper());
        if(constructionList.size() >0)
            return constructionList.get(0);
        else return null;

 }

 @Override
 public Integer createMaterialEvent(BigDecimal totalAmount, Integer projectId){
        String sql = " INSERT  material_events (total_amount, project_id, created_time ) VALUES (:total_amount, :project_id, created_time) ";
        Map<String, Object> map = new HashMap<>();
        map.put("total_amount", totalAmount);
        map.put("project_id", projectId);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        map.put("created_time", timestamp);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update( sql, new MapSqlParameterSource(map), keyHolder);
        Integer materialEventId = keyHolder.getKey().intValue();

        return materialEventId;

 }
  @Override
  public void createMaterialUsed(Integer materialEventId, List<MaterialUsed> materialUsedList){
      String sql = "INSERT  material_used ( material_name, unit, amount, material_event_id)  VALUES (:material_name, :unit, :amount, material_event_id) ";
      MapSqlParameterSource [] mapSqlParameterSources = new MapSqlParameterSource[materialUsedList.size()];
      for(int i = 0; i < materialUsedList.size(); i++){

          mapSqlParameterSources[i] = new MapSqlParameterSource();
          mapSqlParameterSources[i].addValue("material_name", materialUsedList.get(i).getMaterialName());
          mapSqlParameterSources[i].addValue("unit", materialUsedList.get(i).getUnit());
          mapSqlParameterSources[i].addValue("amount", materialUsedList.get(i).getAmount());
          mapSqlParameterSources[i].addValue("material_event_id", materialEventId);


      }
      namedParameterJdbcTemplate.batchUpdate(sql, mapSqlParameterSources);

  }

    @Override
    public MaterialEvent getMaterialEventById(Integer materialEventId){
        String sql = " SELECT * FROM material_events WHERE material_event_id = :material_event_id ";
        Map<String, Object> map = new HashMap<>();
        map.put("material_event_id", materialEventId);

        List<MaterialEvent> materialEventList =  namedParameterJdbcTemplate.query(sql, map, new MaterialEventRowMapper());
        if(materialEventList.size() >0)
            return materialEventList.get(0);
        else return null;

    }

    @Override
    public void createAccountPayable(MaterialUsed materialUsed){
        String sql = " INSERT account_payable ( supplier, month, material_event_id, payable_amount, last_modified_date, already_paid) VALUES ( :supplier, :month, :material_event_id, :payable_amount, :last_modified_date, :already_paid) ";
        Map<String, Object> map = new HashMap<>();

        String name =  materialUsed.getMaterialName();
        Material material = getMaterialByName(name);
        map.put(" supplier",material.getSupplier());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Integer month = timestamp.toLocalDateTime().getMonthValue();
        map.put("month", month);
        map.put("payable_amount", materialUsed.getAmount());
        map.put("last_modified_date", timestamp);
        map.put("already_paid", false   );
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update( sql, new MapSqlParameterSource(map), keyHolder);


    }

    @Override
    public void  payToSupplier(Integer payableId, Boolean alreadyPaid){

        String sql = "  UPDATE account_payable SET already_paid = :already_paid WHERE payableId = :payableId  ";
        Map<String, Object> map = new HashMap<>();
        map.put("already_paid", alreadyPaid);
        map.put("payableId", payableId);
        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public AccountPayable getPayableById(Integer payableId){

        String sql = " SELECT * FROM account_payable WHERE payable_id = :payableId   ";
        Map<String, Object> map = new HashMap<>();
        map.put("payableId", payableId);
        List<AccountPayable> payableList =  namedParameterJdbcTemplate.query(sql,map, new AccountPayableRowMapper());

        if(payableList.size() >0)
            return payableList.get(0);
        else return null;
    }

    @Override
    public List<AccountPayable> getPayable(QueryPara queryPara){

        String sql = " SELECT * FROM account_payable WHERE 1=1 ";
        Map<String, Object> map = new HashMap<>();

        if(queryPara.getSearch() != null){
            sql = sql+ " AND search LIKE :search  ";
            map.put("search", queryPara.getSearch());

        }

        sql = sql+ " ORDER BY :orderBy " + " "+ queryPara.getSort();
        map.put("orderBy", queryPara.getOrderBy());
        sql = sql+ " LIMIT :limit  OFFSET :offset  ";
        map.put("limit", queryPara.getLimit());
        map.put("offset", queryPara.getOffset());

        List<AccountPayable> accountPayableList = namedParameterJdbcTemplate.query(sql, map, new AccountPayableRowMapper());
        if (accountPayableList.size() >0)
            return accountPayableList;
        else return null;

    }


}


