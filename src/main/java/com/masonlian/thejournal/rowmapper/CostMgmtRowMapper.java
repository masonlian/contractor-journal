package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.model.Material;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CostMgmtRowMapper implements RowMapper {

    public Material mapRow(ResultSet rs, int rowNum) throws SQLException {

        Material material = new Material() ;
        material.setMaterialId(rs.getInt("material_id"));
        material.setMaterialName(rs.getString("material_name"));
        material.setMaterialType(rs.getString("material_type"));
        material.setImageUrl(rs.getString("image_url"));
        material.setUnitPrice(rs.getBigDecimal("unit_price"));
        material.setSpecification(rs.getString("specification"));

        return material;
    }
}
