package com.gmail.tlachy.impl;

import com.gmail.tlachy.IOrder;
import com.gmail.tlachy.IQuote;

public class Order implements IOrder {

    private int quantity;
    private IQuote quote;

    public Order(int quantity, IQuote quote) {
        this.quantity = quantity;
        this.quote = quote;
    }

    @Override
    public int getQuantity() {
        return 0;
    }

    @Override
    public IQuote getQuote() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (quantity != order.quantity) return false;
        if (!quote.equals(order.quote)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = quantity;
        result = 31 * result + quote.hashCode();
        return result;
    }
}
