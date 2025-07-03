package com.masonlian.thejournal.controller;

import com.masonlian.thejournal.constant.ConstructionCategory;
import com.masonlian.thejournal.constant.MediaType;
import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.MediaRequest;
import com.masonlian.thejournal.model.Media;
import com.masonlian.thejournal.service.MediaService;
import com.masonlian.thejournal.util.Page;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @PostMapping("c-media")
    public ResponseEntity<Media> createMedia(@RequestBody MediaRequest mediaRequest) {

        Integer mediaId = mediaService.createMedia(mediaRequest);
        Media media =  mediaService.getMediaById(mediaId);
        return ResponseEntity.status(HttpStatus.CREATED).body(media);
    }

    @GetMapping("media")
    public ResponseEntity<Page<Media>> getMedias(@RequestParam (defaultValue = "1")@Max(5)@Min(0) Integer offset,
                                                 @RequestParam (name="limit",defaultValue="5") @Max(100)Integer limit,

                                                 @RequestParam (required = false) ConstructionCategory category,
                                                 @RequestParam(required = false) MediaType mediaType,

                                                 @RequestParam (required = false) String search,

                                                 @RequestParam (defaultValue = "created_date") String order,
                                                 @RequestParam (defaultValue = "desc")String sort
                                                 ){

        QueryPara queryPara = new QueryPara();

        queryPara.setOffset(offset);
        queryPara.setLimit(limit);
        queryPara.setSearch(search);
        queryPara.setOrderBy(order);
        queryPara.setSort(sort);
        queryPara.setConstructionCategory(category);
        queryPara.setMediaType(mediaType);
        List<Media> mediaList = mediaService.getMedia(queryPara);

        Page<Media> mediaPage = new Page<>();
        mediaPage.setTotal(mediaList.size());
        mediaPage.setLimit(limit);
        mediaPage.setOffset(offset);
        mediaPage.setResult(mediaList);

        return ResponseEntity.status(HttpStatus.OK).body(mediaPage);
    }

    @DeleteMapping("/media/{mediumId}")
    public ResponseEntity<Media> deleteMedia(@PathVariable Integer mediumId) {

        mediaService.deleteMediumById(mediumId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PostMapping("/media/{mediumId}")
    public ResponseEntity<Media> updateMedia(@PathVariable Integer mediumId, @RequestBody MediaRequest mediaRequest) {

       mediaService.updateMediumById(mediumId,mediaRequest);
       return ResponseEntity.status(HttpStatus.OK).build();

    }


}
