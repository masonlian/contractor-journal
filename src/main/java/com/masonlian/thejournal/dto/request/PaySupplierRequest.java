package com.masonlian.thejournal.dto.request;

public class PaySupplierRequest {
    public Boolean getAlreadyPaid() {
        return alreadyPaid;
    }

    public void setAlreadyPaid(Boolean alreadyPaid) {
        this.alreadyPaid = alreadyPaid;
    }

    Boolean alreadyPaid;

}
