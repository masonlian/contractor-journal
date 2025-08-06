package com.masonlian.thejournal.dto;

import com.masonlian.thejournal.constant.ConstructionCategory;
import com.masonlian.thejournal.constant.Level;
import com.masonlian.thejournal.constant.MediaType;

import java.time.LocalDate;
import java.util.Date;


//設定請求的參數
public class QueryPara {

    private Integer limit;
    private Integer offset;

    private String sort;
    private String orderBy;

    private  String  search;
    private ConstructionCategory constructionCategory;

    private Level level;
    private MediaType mediaType;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public LocalDate getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(LocalDate calendarDate) {
        this.calendarDate = calendarDate;
    }

    private LocalDate calendarDate;

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
