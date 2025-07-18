package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.model.AccountPayable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountPayableRowMapper implements RowMapper<AccountPayable> {

    public AccountPayable mapRow(ResultSet rs, int rowNum) throws SQLException {

        AccountPayable accountPayable = new AccountPayable();
        accountPayable.setPayableId(rs.getInt("payable_id"));
        accountPayable.setSupplier(rs.getString("supplier"));
        accountPayable.setMaterialEventId(rs.getInt("material_event_id"));
        accountPayable.setMonth(rs.getInt("month"));
        accountPayable.setPayableAmount(rs.getBigDecimal("payable_amount"));
        accountPayable.setAlreadyPaid(rs.getBoolean("already_paid"));
        accountPayable.setLastModifiedDate(rs.getTimestamp("last_modified_date"));

        return accountPayable;

    }
}
