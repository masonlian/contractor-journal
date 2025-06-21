package com.masonlian.thejournal.dto;

import com.masonlian.thejournal.constant.Level;

public class QueryPara {

    private Integer limit;
    private Integer offset;
    private String sort;
    private String orderBy;
    private  String  search;

    public Level getJoblevel() {
        return level;
    }

    public void setJoblevel(Level level) {
        this.level = level;
    }

    private Level level;

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
