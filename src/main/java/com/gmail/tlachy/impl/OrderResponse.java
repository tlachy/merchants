package com.gmail.tlachy.impl;

import com.gmail.tlachy.IOrderResponse;


public class OrderResponse implements IOrderResponse {

    private int quantity;

    public OrderResponse(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }
}
