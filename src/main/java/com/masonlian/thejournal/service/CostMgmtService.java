package com.masonlian.thejournal.service;

import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.ConstructionRequest;
import com.masonlian.thejournal.dto.request.CreateMaterialEventRequest;
import com.masonlian.thejournal.dto.request.MaterialRequest;
import com.masonlian.thejournal.model.*;

import java.util.List;

public interface CostMgmtService {

    Integer createMaterial(MaterialRequest materialRequest );
    Material getMaterialById(Integer materialId);
    void updateMaterialById(Integer materialId, MaterialRequest materialRequest);
    void deleteMaterialById(Integer materialId);
    List<Material> getMaterials(QueryPara queryPara);

    Integer createConstruction(ConstructionRequest constructionRequest);
    Construction getConstructionById(Integer constructionId);
    void deleteConstructionById(Integer constructionId);
    void updateConstructionBydId (Integer constructionId,ConstructionRequest constructionRequest);
    List<Construction> getConstructionItems(QueryPara queryPara);

    Integer createMaterialEvent(CreateMaterialEventRequest createMaterialEventRequest);
    MaterialEvent  getMaterialEventById(Integer materialEventId);

    void  createAccountPayable(MaterialUsed materialUsed);


    void  payToSupplier(Integer payableId, Boolean alreadyPaid);
    AccountPayable  getPayableById( Integer payableId);

    List<AccountPayable> getPayable(QueryPara queryPara);


}
