package com.gtv.hanhee.testlayout.model;

import com.chad.library.adapter.base.entity.SectionEntity;

public class OrderSection extends SectionEntity<Order> {

    private boolean isEnd = false;
    private boolean isCheckedShop = true;
    private int positionShop;

    public OrderSection(boolean isHeader, String header) {
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

    public OrderSection(Order order, int positionShop) {
        super(order);
        this.positionShop = positionShop;
    }

    public OrderSection(Order order, boolean isEnd) {
        super(order);
        this.isEnd = isEnd;
    }
    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }
}