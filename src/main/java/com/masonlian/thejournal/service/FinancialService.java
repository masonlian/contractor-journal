package com.masonlian.thejournal.service;

import com.masonlian.thejournal.model.FinancialStatement;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface FinancialService {

    void updateReceived(BigDecimal received);
    void updateMaterialPayable(BigDecimal totalAmount);
    void updateLaborCost(BigDecimal laborCost);
    void updateProfit(BigDecimal profit);

    FinancialStatement  getStatementByQuarter(Integer quarter);

}
