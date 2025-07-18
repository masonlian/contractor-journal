package com.masonlian.thejournal.dao;

import com.masonlian.thejournal.model.FinancialStatement;

import java.math.BigDecimal;

public interface FinancialDao{
    void updateReceived(Integer quarter, BigDecimal received);
    void updateMaterialPayable(Integer quarter , BigDecimal totalAmount);
    FinancialStatement  getStatementByQuarter(Integer quarter);
    void updateProfit(Integer quarter , BigDecimal profit);
    void updateLaborCost(Integer quarter , BigDecimal laborCost);


}
