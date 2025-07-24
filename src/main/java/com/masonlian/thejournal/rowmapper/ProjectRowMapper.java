package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.model.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectRowMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet result, int rowNumber) throws SQLException {
        Project project = new Project();

        project.setProjectId(result.getInt("project_id"));
        project.setProjectName(result.getString("project_name"));
        project.setProjectManager(result.getString("project_manager"));
        project.setOwner(result.getString("owner"));
        project.setAddress(result.getString("address"));
        project.setDescription(result.getString("description"));
        project.setBudget(result.getBigDecimal("budget"));
        project.setProfit(result.getBigDecimal("profit"));
        project.setCostEstimate(result.getBigDecimal("cost_estimate"));
        project.setCreationDate(result.getTimestamp("created_date"));
        project.setLastModifiedDate(result.getTimestamp("last_modified_date"));
        project.setFinish(result.getBoolean("finished"));
        project.setConstructionPeriod(result.getInt("construction_period"));
        project.setBalance(result.getBigDecimal("balance"));




        return project;

    }


}
