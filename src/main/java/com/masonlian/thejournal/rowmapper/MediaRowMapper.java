package com.masonlian.thejournal.rowmapper;

import com.masonlian.thejournal.constant.ConstructionCategory;
import com.masonlian.thejournal.model.Media;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MediaRowMapper implements RowMapper<Media> {

    public Media mapRow(ResultSet rs, int rowNum) throws SQLException {

        Media media = new Media();
        media.setMediaId(rs.getInt("media_id"));
        media.setDescription(rs.getString("description"));

        String category= rs.getString("construction_category");
        ConstructionCategory constructionCategory = ConstructionCategory.valueOf(category);
        media.setConstructionCategory(constructionCategory);
        media.setResourceUrl(rs.getString("resource_url"));

        String type = rs.getString("type");
        MediaType mediaType = MediaType.valueOf(type);
        media.setMediaType(mediaType);

        media.setCreatedDate(rs.getTimestamp("created_date"));
        media.setLastModifiedDate(rs.getTimestamp("last_modified_date)"));


        return media;


    }
}
