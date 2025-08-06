package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.model.Quotation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuotationRowMapper implements RowMapper<Quotation> {

    public Quotation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Quotation quotation = new Quotation();

        quotation.setQuotationId(rs.getInt("quotation_id"));
        quotation.setProjectId(rs.getInt("project_id"));
        quotation.setCreateBy(rs.getString("create_by"));
        quotation.setCreateDate(rs.getTimestamp("created_date"));
        quotation.setStatus(rs.getString("status"));
        quotation.setSummary(rs.getString("summary"));
        quotation.setTotalAmount(rs.getBigDecimal("total_amount"));

        return quotation;

    }

}
