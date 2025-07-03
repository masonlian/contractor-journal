package com.masonlian.thejournal.dto;

import com.masonlian.thejournal.constant.ConstructionCategory;
import com.masonlian.thejournal.constant.Level;
import com.masonlian.thejournal.constant.MediaType;

import java.util.Date;

public class QueryPara {

    private Integer limit;
    private Integer offset;
    private String sort;
    private String orderBy;
    private  String  search;
    private Level level;
    private MediaType mediaType;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Date getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(Date calendarDate) {
        this.calendarDate = calendarDate;
    }

    private Date calendarDate;

    public ConstructionCategory getConstructionCategory() {
        return constructionCategory;
    }

    public void setConstructionCategory(ConstructionCategory constructionCategory) {
        this.constructionCategory = constructionCategory;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    private ConstructionCategory constructionCategory;

    public Level getJoblevel() {
        return level;
    }

    public void setJoblevel(Level level) {
        this.level = level;
    }



    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

}
