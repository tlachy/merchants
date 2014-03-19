package com.gmail.tlachy.impl;


import com.gmail.tlachy.Merchant;
import com.gmail.tlachy.Order;
import com.gmail.tlachy.OrderResponse;
import com.gmail.tlachy.Quote;

import java.math.BigDecimal;

public class MerchantImpl implements Merchant {

    private Quote quote;
    private OrderResponse orderResponse;

    public MerchantImpl(final int quantity, final double price, final int orderResponseCount) {

        this.quote = new Quote() {
            @Override
            public int getQuantity() {
                return quantity;
            }

            @Override
            public BigDecimal getPrice() {
                return new BigDecimal(price);
            }
        };

        this.orderResponse = new OrderResponse() {
            @Override
            public int getQuantity() {
                return orderResponseCount;
            }
        };
    }

    @Override
    public Quote quote() throws Exception {
        return quote;
    }

    @Override
    public OrderResponse order(Order order) throws Exception {

        return orderResponse;
    }
}
