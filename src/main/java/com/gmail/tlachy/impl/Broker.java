package com.gmail.tlachy.impl;


import com.gmail.tlachy.IBroker;
import com.gmail.tlachy.IMerchant;
import com.gmail.tlachy.IQuote;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Broker implements IBroker {

    private final SortedSet<Merchant> sortedMerchants;
    private List<Merchant> merchants;


    public Broker() {
        this.sortedMerchants = Collections.synchronizedSortedSet(new TreeSet<Merchant>(comparator));
        this.merchants = new ArrayList<Merchant>();
    }


    @Override
    public int purchase(final int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Cannot purchase negative quantity");

        AtomicInteger responses = new AtomicInteger(0);
        AtomicInteger available = new AtomicInteger(0);

        for (Merchant m : merchants) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        IQuote q = m.quote();

                        if (q.getQuantity() > 0) { // if is zero no need to add
                            available.addAndGet(q.getQuantity());

                            m.setAvailable(q.getQuantity());
                            m.setPrice(q.getPrice());
                            sortedMerchants.add(m);
                        }

                    } catch (Exception e) {
                    }

                    responses.incrementAndGet();
                }
            }).start();
        }

        while (true) {
            if (merchants.size() != responses.intValue()) continue;

            return order(Math.min(quantity, available.intValue()));
        }
    }


    private int order(final int count) {
        AtomicInteger bought = new AtomicInteger(0);

        while (count > bought.intValue()) { // we have what to buy

            //// Is there anything available at all - if not all threads has finished and bought variable is final
            /////////////////////////////////////////////////////////////////
            boolean available = false;
            for (Merchant m : sortedMerchants) {
                if (m.getAvailable() <= 0) continue;
                available = true;
            }
            if (!available) return bought.intValue();
            //////////////////////////////////////////////////////////////////////


            int toPurchase = count - bought.intValue();
            AtomicInteger started = new AtomicInteger(0);
            AtomicInteger finished = new AtomicInteger(0);


            for (Merchant m : sortedMerchants) {
                if(toPurchase <= 0) break;
                if (m.getAvailable() == 0) continue;

                int howMuchToOrder = Math.min(toPurchase, m.getAvailable());
                toPurchase -= howMuchToOrder;
                started.incrementAndGet();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            int i = m.order(new Order(howMuchToOrder, m.getPrice())).getQuantity();
                            bought.addAndGet(i);
                            m.setAvailable(m.getAvailable() - i);

                        } catch (Exception e) {
                            m.setAvailable(0);
                        }
                        finished.incrementAndGet();
                    }
                }).start();
            }

            while(true){
                if(started.intValue() == finished.intValue()) break; //wait for all orders
            }

        }

        return bought.intValue();

    }


    public List<Merchant> getMerchants() {
        return merchants;
    }

    private static final Comparator<Merchant> comparator = (m1, m2) -> {
        if (m1.getPrice() == null) return 1;
        if (m2.getPrice() == null) return -1;

        return m1.getPrice().compareTo(m2.getPrice());
    };

}
