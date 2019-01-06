package com.gtv.hanhee.testlayout.model;

import com.chad.library.adapter.base.entity.SectionEntity;

public class ProductSection extends SectionEntity<Product> {

    private boolean isEnd = false;
    private boolean isCheckedShop = true;
    private int positionShop;

    public ProductSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public boolean isCheckedShop() {
        return isCheckedShop;
    }

    public void setCheckedShop(boolean checkedShop) {
        isCheckedShop = checkedShop;
    }

    public int getPositionShop() {
        return positionShop;
    }

    public void setPositionShop(int positionShop) {
        this.positionShop = positionShop;
    }

    public ProductSection(Product product, int positionShop) {
        super(product);
        this.positionShop = positionShop;
    }

    public ProductSection(Product product, boolean isEnd) {
        super(product);
        this.isEnd = isEnd;
    }
    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }
}
