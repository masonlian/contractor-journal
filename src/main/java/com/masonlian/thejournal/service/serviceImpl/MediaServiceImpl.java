package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.dao.MediaDao;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.MediaRequest;
import com.masonlian.thejournal.model.Media;
import com.masonlian.thejournal.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MediaServiceImpl implements MediaService {

    @Autowired

    private MediaDao mediaDao;

    public Integer createMedia(MediaRequest mediaRequest) {

        return mediaDao.createMedia(mediaRequest);

    }

    public Media getMediaById(Integer id) {

        return mediaDao.getMediaById(id);

    }

    @Override
    public List<Media> getMedia(QueryPara queryPara) {
        return mediaDao.getMedia(queryPara);
    }

    @Override
    public void deleteMediumById(Integer mediumId) {
        mediaDao.deleteMediumById(mediumId);
    }

    @Override
    public void updateMediumById(Integer mediumId, MediaRequest mediaRequest) {

        mediaDao.updateMediumById(mediumId, mediaRequest);

    }

    @Override
    public List<Media> getBlueprintById(Integer projectId) {

        List<Media> mediaList = mediaDao.getMediumByProjectId(projectId);
        List<Media> blueprintList = new ArrayList<>();

        for (Media media : mediaList) {
            if (media.getMediaType().equals("blueprint")) {
                blueprintList.add(media);
            }

            return blueprintList;
        }

        return null;
    }
}
