package com.masonlian.thejournal.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;


public class CreateMaterialEventRequest {
    public List<MaterialItem> getUsedList() {
        return usedList;
    }

    public void setUsedList(List<MaterialItem> usedList) {
        this.usedList = usedList;
    }

    private List<MaterialItem> usedList; //用列表的形式一次可以輸入多筆建材資料

    @NotNull
    private Integer projectId;



    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
