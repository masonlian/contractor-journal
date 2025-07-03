package com.masonlian.thejournal.model;

import com.masonlian.thejournal.constant.ConstructionCategory;
import org.springframework.http.MediaType;

import java.security.Timestamp;

public class Media {
    public ConstructionCategory getConstructionCategory() {
        return constructionCategory;
    }

    public void setConstructionCategory(ConstructionCategory constructionCategory) {
        this.constructionCategory = constructionCategory;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private Integer mediaId;
    private ConstructionCategory constructionCategory;
    private MediaType mediaType;
    private String resourceUrl;
    private String description;

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    private Timestamp createdDate;
    private Timestamp lastModifiedDate;


    public void setCreatedDate(java.sql.Timestamp createdDate) {
    }

    public void setLastModifiedDate(java.sql.Timestamp timestamp) {
    }
}
