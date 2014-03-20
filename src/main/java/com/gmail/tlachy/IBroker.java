package com.gmail.tlachy;


import java.util.List;

public interface IBroker {

    int purchase(int quantity);
    List<IMerchant> getMerchants();
}
