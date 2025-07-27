package com.masonlian.thejournal.dto.request;

import java.util.ArrayList;
import java.util.List;

public class CreateMaterialRequest {
    List<MaterialRequest> createMaterialRequests ;

    public List<MaterialRequest> getCreateMaterialRequests() {
        return createMaterialRequests;
    }

    public void setCreateMaterialRequests(List<MaterialRequest> createMaterialRequests) {
        this.createMaterialRequests = createMaterialRequests;
    }
}
