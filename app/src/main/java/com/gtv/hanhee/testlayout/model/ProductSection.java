package com.gtv.hanhee.testlayout.model;

import com.chad.library.adapter.base.entity.SectionEntity;

public class ProductSection extends SectionEntity<Product> {

    private boolean isMore;
    public ProductSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public ProductSection(Product product) {
        super(product);
    }
}
