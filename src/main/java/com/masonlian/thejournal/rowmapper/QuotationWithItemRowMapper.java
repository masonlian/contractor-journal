package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.dto.QuotationWithItemDto;
import com.masonlian.thejournal.model.Quotation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuotationWithItemRowMapper implements RowMapper<QuotationWithItemDto> {

    public QuotationWithItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        QuotationWithItemDto quotationWithItemDto = new QuotationWithItemDto();
        quotationWithItemDto.setQuotationId(rs.getInt("quotation_id"));
        quotationWithItemDto.setProjectId(rs.getInt("project_id"));
        quotationWithItemDto.setCreateBy(rs.getString("create_by"));
        quotationWithItemDto.setCreatedDate(rs.getTimestamp("created_date"));
        quotationWithItemDto.setStatus(rs.getString("status"));
        quotationWithItemDto.setSummary(rs.getString("summary"));
        quotationWithItemDto.setTotalAmount(rs.getBigDecimal("total_amount"));

        return quotationWithItemDto;

    }


}
