package com.masonlian.thejournal.dao.daoImpl;

import com.masonlian.thejournal.dao.MediaDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.MediaRequest;
import com.masonlian.thejournal.model.Media;
import com.masonlian.thejournal.rowmapper.MediaRowMapper;
import com.masonlian.thejournal.service.MediaService;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MediaDaoImpl implements MediaDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MediaDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Integer createMedia(MediaRequest mediaRequest){

        String sql = "INSERT INTO media_repository (media_id, media_type, construction_category, description, resource_url) VALUES (:media_id, :media_type, :construction_category, :description, :resource_url)  ";
        Map<String,Object> map = new HashMap<>();

        map.put("media_type",mediaRequest.getMediaType());
        map.put("construction_category",mediaRequest.getConstructionCategory());
        map.put("description",mediaRequest.getDescription());
        map.put("resource_url",mediaRequest.getResourceUrl());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer mediaId = keyHolder.getKey().intValue();

        return mediaId;


    }
    public Media getMediaById(Integer id) {
        String sql = " SELECT construction_category, media_type, resource_url, description FROM media_repository WHERE media_id = :media_id";

        Map<String, Object> map = new HashMap<>();
        map.put("media_id", id);

        List<Media> mediaList = namedParameterJdbcTemplate.query(sql, map, new MediaRowMapper());
        if (mediaList.size() > 0)
            return mediaList.get(0);
        return null;
    }


    @Override
    public List<Media> getMedia(QueryPara queryPara){

        String sql = " SELECT media_id, media_type, resource_url, description FROM media_repository WHERE 1=1" ;
        Map<String,Object> map = new HashMap<>();

        if(queryPara.getConstructionCategory()!=null) {
            sql = sql + " AND construction_category = :construction_category ";
            map.put("construction_category", queryPara.getConstructionCategory());
        }
        if(queryPara.getMediaType()!=null) {
            sql = sql + " AND media_type = :media_type ";
            map.put("media_type", queryPara.getMediaType());
        }
        if(queryPara.getSearch()!=null) {
            sql = sql + " AND description LIKE :search";
            map.put("search", "%" + queryPara.getSearch() + "%");
        }

        sql = sql + " ORDER BY :order"+ " " +queryPara.getSort();
        List<Media> mediaList = namedParameterJdbcTemplate.query(sql, map, new MediaRowMapper());
        return mediaList;

    }
    @Override
    public void deleteMediumById(Integer mediumId){

        String sql = "DELETE FROM media_repository WHERE media_id = :media_id";
        Map<String, Object> map = new HashMap<>();
        map.put("media_id", mediumId);
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void updateMediumById(Integer mediumId, MediaRequest mediaRequest){

        String sql  = " UPDATE media_repository SET construction_category = :construction_category, media_type=:media_type, resource_url=:resource_url, description=:description ,last_modified_date =last_modified_date WHERE media_id = :media_id";
        Map<String,Object> map = new HashMap<>();
        map.put("media_id", mediumId);
        map.put("media_type", mediaRequest.getMediaType());
        map.put("resource_url", mediaRequest.getResourceUrl());
        map.put("description", mediaRequest.getDescription());
        map.put("construction_category", mediaRequest.getConstructionCategory());
        Date now =  new Date();
        map.put("last_modified_date", now);
        namedParameterJdbcTemplate.update(sql, map);
    }

}

