package com.gmail.tlachy;

import static org.mockito.Mockito.*;

import com.gmail.tlachy.impl.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(JUnit4.class)
//@ContextConfiguration("classpath:spring-config.xml")
public class BrokerTest {

    public Broker broker = new Broker();


    @Before
    public void createSimpleMerchants() throws Exception {
        broker.getMerchants().clear();

//        m_1_1d = mock(Merchant.class);
//        when(m_1_1d.quote()).thenReturn(new Quote(1, 1d));
//        when(m_1_1d.order(any())).thenReturn(new OrderResponse(1));
//
//        m_2_2d = mock(Merchant.class);
//        when(m_2_2d.quote()).thenReturn(new Quote(2, 2d)).thenReturn(new Quote(0,2d));
//        when(m_2_2d.order(any())).thenReturn(new OrderResponse(2));

         Merchant m_1_1d = new Merchant(new Quote(1,1));
         Merchant m_2_2d = new Merchant(new Quote(2,2));

        broker.getMerchants().add(m_1_1d);
        broker.getMerchants().add(m_2_2d);

    }

//    @Test
//    public void testBuy_from_1_merchant() throws Exception {
//        broker.purchase(1);
//
//        verify(m_1_1d).order(eq(new Order(1, new Quote(1,1d))));
//        verify(m_2_2d, never()).order(any());
//    }

    @Test
    public void testBuy_1() throws Exception {
        System.out.println("---------");
        int purchased = broker.purchase(1);
        System.out.println("-----"+purchased+"----");
    }

    @Test
    public void testBuy_2() throws Exception {
        System.out.println("---------");
        int purchased = broker.purchase(2);
        System.out.println("-----"+purchased+"----");
    }

    @Test
    public void testBuy_3() throws Exception {
        System.out.println("---------");
        int purchased = broker.purchase(3);
        System.out.println("-----"+purchased+"----");

    }

    @Test
    public void testBuy_4() throws Exception {
        System.out.println("---------");
        int purchased = broker.purchase(4);
        System.out.println("-----"+purchased+"----");
    }


}


