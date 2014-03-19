package com.gmail.tlachy.impl;

import com.gmail.tlachy.*;
import java.util.List;


public class BestBrokerEver implements Broker {

    private final List<Merchant> merchants;


    public BestBrokerEver(List<Merchant> merchants) {
        this.merchants = merchants;
    }


    @Override
    public int purchase(final int quantity) {
        if(quantity < 0) throw new IllegalArgumentException("Cannot purchase negative quantity");

        int purchased = 0;
        int attempts = merchants.size(); //that's question how to calculate attempts as merchants can change behavious over time

        while (purchased < quantity && attempts-- > 0) {

            Integer count = purchaseFromBestMerchant(quantity - purchased); //it's looking for best merchant after every order
            if(count == null) break;
            purchased += count;

        }

        System.out.printf("Purchased totally:%d%n", purchased);
        return purchased;
    }


    /**
     *
     * @param quantity
     * @return how much it was purchased from best merchant or NULL if there is no merchant to purchase from
     */
    private Integer purchaseFromBestMerchant(int quantity){
        Merchant bestMerchant = null;
        Quote bestQuote = null;

        for (Merchant m : merchants) {
            try {
                Quote q = m.quote();
                if (q.getQuantity() <= 0) continue;

                if (bestMerchant == null || (q.getPrice().compareTo(bestQuote.getPrice()) == -1)) {
                    bestMerchant = m;
                    bestQuote = q;
                }
            } catch (Exception e) {
                continue;
            }
        }

        if (bestMerchant == null) return null; // no merchant we can purchase from - ending

        int count = purchase(bestMerchant, bestQuote, Math.min(bestQuote.getQuantity(), quantity ));
        System.out.printf("Purchased %d at price: %s%n", count, bestQuote.getPrice()); // I
        return count;
    }

    /**
     *
     * @param bestMerchant
     * @param bestQuote
     * @param count
     * @return how much was purchased form merchant
     */
    private int purchase(final Merchant bestMerchant, final Quote bestQuote, final int count) {
        try {

            return bestMerchant.order(new Order() {
                @Override
                public int getQuantity() {
                    return count;
                }

                @Override
                public Quote getQuote() {
                    return bestQuote;
                }
            }).getQuantity();

        } catch (Exception e) {
            return 0;
        }
    }


//    private List<Merchant> sort()  {
//        return merchants.stream().filter(m -> {
//
//            try {
//
//                return m.quote().getQuantity() > 0;
//
//            } catch (Exception e) {
//                return false;
//            }
//
//        }).sorted((m1, m2) -> {
//
//            try {
//                return m1.quote().getPrice().compareTo(m2.quote().getPrice());
//
//            } catch (Exception e) {
//                //this might happen only if quote() method will first return value but will throw exception on the second invocation
//                // IF THIS BEHAVIOUR IS POSSIBLE THEN IT WOULD BE HARD TO SORT MERCHANTS
//                //ONE POSSIBLE SOLUTION IS TO WRAP THEM AND STORE LAST RETURN VALUE
//                System.out.println();
//                return 0;
//            }
//
//        }).collect(Collectors.toList());


}
