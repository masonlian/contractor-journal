package com.masonlian.thejournal.dto.request;

import java.util.List;

public class MaterialEventRequst {

    private List<MaterialUsed> UsedList; //用列表的形式一次可以輸入多筆建材資料

    public List<MaterialUsed> getUsedList() {
        return UsedList;

    }
    public void setUsedList(List<MaterialUsed> usedList) {
        this.UsedList = usedList;
    }
}
