package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.model.QuotationItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuotationItemRowMapper implements RowMapper<QuotationItem> {
    
    public QuotationItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuotationItem quotationItem = new QuotationItem();

        quotationItem.setQuotationId(rs.getInt("quotation_id"));

        quotationItem.setConstructionItem(rs.getString("construct_item"));
        quotationItem.setConstructionUnit(rs.getBigDecimal("construct_unit"));
        quotationItem.setConstructionSpec(rs.getString("construct_spec"));
        quotationItem.setConstruction_estimate(rs.getBigDecimal("construct_estimate"));

        return quotationItem;

    }
}
