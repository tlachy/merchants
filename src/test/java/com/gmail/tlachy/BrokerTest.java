package com.gmail.tlachy;


import static org.mockito.Mockito.*;

import com.gmail.tlachy.impl.Order;
import com.gmail.tlachy.impl.OrderResponse;
import com.gmail.tlachy.impl.Quote;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class BrokerTest {

    public IMerchant m_1_1d = null;
    public IMerchant m_2_2d = null;

    @Autowired
    public IBroker broker;

    @Before
    public void createSimpleMerchants() throws Exception {
        broker.getMerchants().clear();

        m_1_1d = mock(IMerchant.class);
        when(m_1_1d.quote()).thenReturn(new Quote(1, 1d));
        when(m_1_1d.order(any())).thenReturn(new OrderResponse(1));

        m_2_2d = mock(IMerchant.class);
        when(m_2_2d.quote()).thenReturn(new Quote(2, 2d));
        when(m_2_2d.order(any())).thenReturn(new OrderResponse(2));

        broker.getMerchants().add(m_1_1d);
        broker.getMerchants().add(m_2_2d);

    }

    @Test
    public void testSimpleBuy() throws Exception {
        broker.purchase(1);

        verify(m_1_1d).order(eq(new Order(1, new Quote(1,1d))));
        verify(m_2_2d, never()).order(any());
    }


}


