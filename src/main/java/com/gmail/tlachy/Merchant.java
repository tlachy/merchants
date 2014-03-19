package com.gmail.tlachy;



public interface Merchant {

    Quote quote() throws Exception;
    OrderResponse order(Order order) throws Exception;

}