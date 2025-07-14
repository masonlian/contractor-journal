package com.masonlian.thejournal.dto.request;

import java.util.List;

public class QuotationItemRequest {


    private List<GiveAQuote> quotationItemsRequest;

    public List<GiveAQuote> getQuotationItemsRequest() {
        return quotationItemsRequest;
    }

    public void setQuotationItemsRequest(List<GiveAQuote> quotationItemsRequest) {
        this.quotationItemsRequest = quotationItemsRequest;
    }
}
