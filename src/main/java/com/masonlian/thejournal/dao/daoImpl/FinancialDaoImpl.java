package com.masonlian.thejournal.dao.daoImpl;

import com.masonlian.thejournal.dao.FinancialDao;
import com.masonlian.thejournal.model.FinancialStatement;
import com.masonlian.thejournal.rowmapper.FinancialStatementRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FinancialDaoImpl implements FinancialDao {


    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

   @Override
   public  void updateReceived(Integer quarter, BigDecimal received) {

       String sql = "UPDATE fianancial_statement SET received = received WHERE quarter = :quarter";
       Map<String, Object> map = new HashMap<>();
       map.put("quarter", quarter);
       map.put("received", received);
       namedParameterJdbcTemplate.update(sql, map);

   }

   @Override
   public  void updateMaterialPayable(Integer quarter , BigDecimal totalAmount){

       String sql = " UPDATE  financial_statement SET material_payable = total_amount WHERE quarter = :quarter ";
       Map<String, Object> map = new HashMap<>();
       map.put("quarter", quarter);
       map.put("total_amount", totalAmount);
       namedParameterJdbcTemplate.update(sql, map);

   }

    @Override
    public FinancialStatement getStatementByQuarter(Integer quarter){
      String sql =  " SELECT * FROM financial_statement WHERE quarter = :quarter  ";
      Map<String, Object> map = new HashMap<>();
      map.put("quarter", quarter);

      List<FinancialStatement> statementList = namedParameterJdbcTemplate.query(sql, map, new FinancialStatementRowMapper());
      if(statementList.size()>0){
          return statementList.get(0);
      }else {
          return null;
      }
    }
    @Override
    public void updateProfit(Integer quarter, BigDecimal profit){
       String sql =  " UPDATE financial_statement SET profit= :profit  WHERE quarter = :quarter  ";
       Map<String, Object> map = new HashMap<>();
       map.put("quarter", quarter);
       map.put("profit", profit);
       namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void updateLaborCost(Integer quarter, BigDecimal laborCost){

       String sql = " UPDATE financial_statement SET labor_cost =  :labor_cost  WHERE quarter = :quarter  ";
       Map<String, Object> map = new HashMap<>();
       map.put("quarter", quarter);
       map.put("labor_cost", laborCost);
       namedParameterJdbcTemplate.update(sql, map);

    }


}
