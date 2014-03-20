package com.gmail.tlachy.impl;

import com.gmail.tlachy.IQuote;

import java.math.BigDecimal;


public class Quote implements IQuote {

    private int quantity;
    private BigDecimal price;

    public Quote(int quantity, BigDecimal price) {
        this.quantity = quantity;
        this.price = price;
    }

    // TODO not sure if this is good idea because of rounding - better to test
    public Quote(int quantity, double price) {
        this.price = new BigDecimal(price);
        this.quantity = quantity;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quote quote = (Quote) o;

        if (quantity != quote.quantity) return false;
        if (!price.equals(quote.price)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = quantity;
        result = 31 * result + price.hashCode();
        return result;
    }
}
