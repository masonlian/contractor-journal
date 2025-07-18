package com.masonlian.thejournal.dto.request;

import java.util.List;

public class CreateMaterialEventRequest {

    private List<MaterialItem> UsedList; //用列表的形式一次可以輸入多筆建材資料
    private Integer projectId;


    public List<MaterialItem> getUsedList() {
        return UsedList;
    }

    public void setUsedList(List<MaterialItem> usedList) {
        UsedList = usedList;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
