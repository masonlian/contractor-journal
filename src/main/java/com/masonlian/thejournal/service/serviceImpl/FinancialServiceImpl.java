package com.masonlian.thejournal.service.serviceImpl;

import com.masonlian.thejournal.dao.FinancialDao;
import com.masonlian.thejournal.model.FinancialStatement;
import com.masonlian.thejournal.service.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Component
public class FinancialServiceImpl implements FinancialService {

    @Autowired
    FinancialDao financialDao;

    @Override
    public void updateReceived(BigDecimal received){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Integer month = timestamp.toLocalDateTime().getMonthValue();

        Integer quarter = 0;
        getQuarter(month);

        FinancialStatement financialStatement =  financialDao.getStatementByQuarter(quarter);
        BigDecimal newReceived = financialStatement.getReceived().add(received);

        financialDao.updateReceived(quarter, newReceived );

    }    @Override
    public void updateMaterialPayable(BigDecimal totalAmount){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Integer month = timestamp.toLocalDateTime().getMonthValue();
        Integer quarter = 0;
        getQuarter(month);

        FinancialStatement financialStatement =  financialDao.getStatementByQuarter(quarter);
        BigDecimal newAmount = financialStatement.getMaterialPayable().add(totalAmount);

        financialDao.updateMaterialPayable(quarter , newAmount );

    }

    @Override
    public void updateProfit(BigDecimal profit){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Integer month = timestamp.toLocalDateTime().getMonthValue();
        Integer quarter = 0;
        getQuarter(month);

        FinancialStatement financialStatement =  financialDao.getStatementByQuarter(quarter);
        BigDecimal newProfit = financialStatement.getProfit().add(profit);

        financialDao.updateProfit(quarter,newProfit);

    }

    @Override
    public FinancialStatement  getStatementByQuarter(Integer quarter){

        return financialDao.getStatementByQuarter(quarter);

    }

    @Override
    public void updateLaborCost(BigDecimal laborCost){

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Integer month = timestamp.toLocalDateTime().getMonthValue();
        Integer quarter = 0;
        getQuarter(month);

        FinancialStatement financialStatement = financialDao.getStatementByQuarter(quarter);
        BigDecimal newLaborCost =  financialStatement.getLaborCost().add(laborCost);
        financialDao.updateLaborCost(quarter, newLaborCost);


    }


    Integer getQuarter(Integer month){

        Integer quarter=0;
        if( month >= 10 ) {
            quarter = 4;
        }else if( month >= 7){
            quarter =3;
        }else if( month >= 4){
            quarter =2;
        } else quarter =1;

        return quarter;
    }
}
