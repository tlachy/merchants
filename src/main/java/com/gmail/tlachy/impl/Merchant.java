package com.gmail.tlachy.impl;

import com.gmail.tlachy.IMerchant;
import com.gmail.tlachy.IOrder;
import com.gmail.tlachy.IOrderResponse;
import com.gmail.tlachy.IQuote;
import java.math.BigDecimal;
import java.util.Calendar;


public class Merchant implements IMerchant {

    private BigDecimal price = null;

    private IQuote quote;
    private int available;

    public Merchant(IQuote quote) {
        this.quote = quote;
    }

    @Override
    public IQuote quote() throws Exception {
        return this.quote;
    }

    @Override
    public IOrderResponse order(IOrder order) throws Exception {
        System.out.println(Calendar.getInstance().getTime() +" :Ordered " + order.getQuantity() +" at price: " + order.getPrice());

        return new IOrderResponse() {
            @Override
            public int getQuantity() {
                return order.getQuantity();
            }
        };
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
