package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.model.QuotationItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuotationItemRowMapper implements RowMapper<QuotationItem> {
    
    public QuotationItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuotationItem quotationItem = new QuotationItem();

        quotationItem.setQuotationId(rs.getInt("quotation_id"));

        quotationItem.setMaterialName(rs.getString("material_name"));
        quotationItem.setMaterialUnit(rs.getBigDecimal("material_unit"));
        quotationItem.setMaterialSpec(rs.getString("material_spec"));
        quotationItem.setMaterialAmount(rs.getBigDecimal("material_amount"));

        quotationItem.setConstructionItem(rs.getString("construction_item"));
        quotationItem.setConstructionUnit(rs.getBigDecimal("construction_unit"));
        quotationItem.setConstructionSpec(rs.getString("construction_spec"));
        quotationItem.setConstructionAmount(rs.getBigDecimal("construction_amount"));

        return quotationItem;

    }
}
