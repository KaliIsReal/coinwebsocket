package com.mpek.coinwebsocket.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mpek.coinwebsocket.model.MessageType;
import com.mpek.coinwebsocket.model.SubscriptionMessage;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class CoinBaseProWebSocketManagerTest {

    @Autowired
    CoinBaseProWebSocketManager manager;

    @Test
    public void test_correct_subscription() throws JsonProcessingException {
        List<String> productIds = new ArrayList<>();
        productIds.add("ETH-USD");
        SubscriptionMessage sub = new SubscriptionMessage();
        sub.setType(MessageType.UNSUBSCRIBE);
        sub.setProductIds(productIds);

        manager.sendSubscriptionMessage(sub);
    }

    @Test
    public void test_incorrect_subscription() throws JsonProcessingException {
        List<String> productIds = new ArrayList<>();
        SubscriptionMessage sub = new SubscriptionMessage();
        sub.setType(MessageType.UNSUBSCRIBE);
        sub.setProductIds(productIds);

        manager.sendSubscriptionMessage(sub);
    }
}