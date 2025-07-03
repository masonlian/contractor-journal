package com.masonlian.thejournal.dao.daoImpl;

import com.masonlian.thejournal.dao.CostMgmtDao;
import com.masonlian.thejournal.dto.request.MaterialRequest;
import com.masonlian.thejournal.model.Material;
import com.masonlian.thejournal.rowmapper.CostMgmtRowMapper;
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
public class CostMgmtDaoImpl implements CostMgmtDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public Integer createMaterial(MaterialRequest materialRequest){

        String sql = " INSERT INTO material ( material_type, unit_price, image_url, specification, material_name )" +
                " VALUE ( :material_type, :unit_price, :image_url, :specification, :material_name ) ";

        Map<String, Object> map = new HashMap<>();

        map.put("material_type", materialRequest.getMaterialType());
        map.put("unit_price", materialRequest.getUnitPrice());
        map.put("image_url", materialRequest.getImageUrl());
        map.put("specification", materialRequest.getSpecification());
        map.put("material_name", materialRequest.getMaterialName());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map),  keyHolder);
        Integer materialId = keyHolder.getKey().intValue();
        return materialId;
    }

    @Override
    public Material getMaterialById (Integer materialId){

        String sql= "SELECT :material_type, :unit_price, :image_url, :specification, :material_name  FROM material WHERE material_id = :material_id  ";

        Map<String, Object> map = new HashMap<>();
        map.put("material_id", materialId);
        List <Material> materialList = namedParameterJdbcTemplate.query(sql,map, new CostMgmtRowMapper());
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
        map.put("material_type", materialRequest.getMaterialType());
        map.put("unit_price", materialRequest.getUnitPrice());
        map.put("image_url", materialRequest.getImageUrl());
        map.put("specification", materialRequest.getSpecification());
        map.put("material_name", materialRequest.getMaterialName());
        namedParameterJdbcTemplate.update(sql, map);
    }
}
