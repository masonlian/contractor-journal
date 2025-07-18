package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.dao.FinancialDao;
import com.masonlian.thejournal.model.FinancialStatement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FinancialStatementRowMapper  implements RowMapper<FinancialStatement> {

    public FinancialStatement mapRow(ResultSet rs, int rowNum) throws SQLException {
        FinancialStatement financialStatement = new FinancialStatement();

        financialStatement.setStatementId(rs.getInt("statement_id"));
        financialStatement.setQuarter(rs.getInt("quarter"));
        financialStatement.setLaborCost(rs.getBigDecimal("labor_cost"));
        financialStatement.setProfit(rs.getBigDecimal("profit"));
        financialStatement.setMaterialPayable(rs.getBigDecimal("material_payable"));
        financialStatement.setReceived(rs.getBigDecimal("received"));
        financialStatement.setSummary(rs.getString("summary"));
        return financialStatement;

    }
}
