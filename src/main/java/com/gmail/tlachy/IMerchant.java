package com.gmail.tlachy;



public interface IMerchant {

    IQuote quote() throws Exception;
    IOrderResponse order(IOrder order) throws Exception;

}