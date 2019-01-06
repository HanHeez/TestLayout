package com.gtv.hanhee.testlayout.manager;

public class ChangeProductAmountEvent {
    public long priceProduct;
    public String formula;

    public ChangeProductAmountEvent(long priceProduct, String formula) {
        this.priceProduct = priceProduct;
        this.formula = formula;
    }
}
