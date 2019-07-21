package com.qlhx.service.device.realize.amq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class PaymentNotifySenderTests {
    @Autowired
    private PaymentNotifySender sender;
     
    @Test
    public void test_sender() {
        sender.sender("支付订单号："+System.currentTimeMillis());
    }
}