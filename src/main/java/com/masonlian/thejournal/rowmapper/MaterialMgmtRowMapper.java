package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.model.Material;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialMgmtRowMapper implements RowMapper {

    public Material mapRow(ResultSet rs, int rowNum) throws SQLException {

        Material material = new Material() ;
        material.setMaterialId(rs.getInt("material_id"));
        material.setMaterialName(rs.getString("material_name"));
        material.setSupplier(rs.getString("supplier"));
        material.setSpecification(rs.getString("specification"));
        material.setImageUrl(rs.getString("image_url"));
        material.setConstructionCategory(rs.getString("construction_category"));
        material.setUnitPrice(rs.getBigDecimal("unit_price"));

        return material;
    }
}
