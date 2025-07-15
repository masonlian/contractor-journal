package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.constant.ConstructionCategory;
import com.masonlian.thejournal.model.Received;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceivedRowMapper implements RowMapper<Received> {

    public Received mapRow(ResultSet rs, int rowNum) throws SQLException {
        Received received = new Received();

        received.setReceived_id(rs.getInt("received_id"));
        received.setName(rs.getString("name"));
        received.setProjectId(rs.getInt("project_id"));

        ConstructionCategory constructionCategory = ConstructionCategory.valueOf(rs.getString("category"));
        received.setConstructionCategory(constructionCategory);

        received.setReceivedPayment(rs.getBigDecimal("received_payment"));
        received.setReceivedTime(rs.getTimestamp("received_time"));

        return  received;

    }
}
