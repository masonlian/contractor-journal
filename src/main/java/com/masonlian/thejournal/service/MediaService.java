package com.masonlian.thejournal.service;

import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.MediaRequest;
import com.masonlian.thejournal.model.Media;

import java.util.List;

public interface MediaService {
    Integer createMedia(MediaRequest mediaRequest);
    Media getMediaById(Integer id);
    List<Media> getMedia(QueryPara queryPara);
    void deleteMediumById(Integer mediumId);
    void updateMediumById(Integer mediumId, MediaRequest mediaRequest);
    List<Media> getBlueprintById(Integer projectId);
}
