package com.masonlian.thejournal.dao;

import com.masonlian.thejournal.dto.QueryPara;
import com.masonlian.thejournal.dto.request.ConstructionRequest;
import com.masonlian.thejournal.dto.request.MaterialRequest;
import com.masonlian.thejournal.model.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface CostMgmtDao {
    Integer createMaterial(MaterialRequest materialRequest);
    Material getMaterialById(Integer materialId);
    void updateMaterialById(Integer materialId, MaterialRequest materialRequest);
    void deleteMaterialById(Integer materialId);
    Material getMaterialByName(String materialName);
    List<Material> getMaterials(QueryPara queryPara);



    Integer createConstruction(ConstructionRequest constructionRequest);
    Construction getConstructionById(Integer constructionId);
    void deleteConstructionById(Integer constructionId);
    void updateConstructionBydId (Integer constructionId,ConstructionRequest constructionRequest);
    List<Construction> getConstructionItems(QueryPara queryPara);
    Construction getConstructionItemByName (String constructionItem);

    Integer createMaterialEvent(BigDecimal totalAmount, Integer projectId);
    void createMaterialUsed(Integer materialEventId, List<MaterialUsed> materialUsedList);
    MaterialEvent getMaterialEventById(Integer materialEventId);
    void createAccountPayable(MaterialUsed materialUsed);

    void  payToSupplier(Integer payableId, Boolean alreadyPaid);
    AccountPayable  getPayableById(Integer payableId);
    List<AccountPayable> getPayable(QueryPara queryPara);
}
