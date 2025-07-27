package com.masonlian.thejournal.dto.request;

import com.masonlian.thejournal.model.Construction;

import java.util.ArrayList;
import java.util.List;

public class CreateConstructionRequest {
    private List<ConstructionRequest> createConstructionRequests ;

    public List<ConstructionRequest> getCreateConstructionRequests() {
        return createConstructionRequests;
    }

    public void setCreateConstructionRequests(List<ConstructionRequest> createConstructionRequests) {
        this.createConstructionRequests = createConstructionRequests;
    }
}
