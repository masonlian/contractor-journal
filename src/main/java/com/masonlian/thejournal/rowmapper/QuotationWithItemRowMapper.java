package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.dto.QuotationWithItemDto;
import com.masonlian.thejournal.model.Quotation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuotationWithItemRowMapper implements RowMapper<QuotationWithItemDto> {

    public QuotationWithItemDto mapRow(ResultSet rs, int rowNum) throws SQLException {

        QuotationWithItemDto quotationWithItemDto = new QuotationWithItemDto();
        quotationWithItemDto.setQuotationId(rs.getInt("quotationId"));
        quotationWithItemDto.setProjectId(rs.getInt("projectId"));
        quotationWithItemDto.setCreateBy(rs.getString("createBy"));
        quotationWithItemDto.setCreatedDate(rs.getTimestamp("createDate"));
        quotationWithItemDto.setStatus(rs.getString("status"));
        quotationWithItemDto.setSummary(rs.getString("summary"));
        quotationWithItemDto.setTotalAmount(rs.getBigDecimal("totalAmount"));

        quotationWithItemDto.setMaterialName(rs.getString("material_name"));
        quotationWithItemDto.setMaterialUnit(rs.getBigDecimal("material_unit"));
        quotationWithItemDto.setMaterialSpec(rs.getString("material_spec"));
        quotationWithItemDto.setMaterialAmount(rs.getBigDecimal("material_amount"));

        quotationWithItemDto.setConstructionItem(rs.getString("construction_item"));
        quotationWithItemDto.setConstructionUnit(rs.getBigDecimal("construction_unit"));
        quotationWithItemDto.setConstructionSpec(rs.getString("construction_spec"));
        quotationWithItemDto.setConstructionAmount(rs.getBigDecimal("construction_amount"));

        return quotationWithItemDto;

    }


}
