package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.model.Quotation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuotationRowMapper implements RowMapper<Quotation> {

    public Quotation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Quotation quotation = new Quotation();

        quotation.setQuotationId(rs.getInt("quotationId"));
        quotation.setProjectId(rs.getInt("projectId"));
        quotation.setCreateBy(rs.getString("createBy"));
        quotation.setCreateDate(rs.getTimestamp("createDate"));
        quotation.setStatus(rs.getString("status"));
        quotation.setSummary(rs.getString("summary"));
        quotation.setTotalAmount(rs.getBigDecimal("totalAmount"));

        return quotation;

    }

}
