package com.gtv.hanhee.testlayout.model;

import com.chad.library.adapter.base.entity.SectionEntity;

public class ProductSection extends SectionEntity<Product> {

    private boolean isEnd = false;
    private boolean isChecked = true;
    private int positionShop;

    public ProductSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
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
