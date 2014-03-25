package com.gmail.tlachy.impl;

import com.gmail.tlachy.IOrder;
import com.gmail.tlachy.IQuote;

import java.math.BigDecimal;

public class Order implements IOrder {

    private int quantity;
    private BigDecimal price;

    public Order(int quantity, BigDecimal price) {
        if(price == null || quantity <= 0) throw new IllegalArgumentException();

        this.quantity = quantity;
        this.price = price;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (quantity != order.quantity) return false;
        if (!price.equals(order.price)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = quantity;
        result = 31 * result + price.hashCode();
        return result;
    }
}
