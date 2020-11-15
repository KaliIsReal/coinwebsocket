package com.mpek.coinwebsocket.websocket;

import com.mpek.coinwebsocket.model.MessageType;
import com.mpek.coinwebsocket.model.SubscriptionMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebSocketConfiguration {

    @Bean
    WebSocketClient getWebSocketClient(){
        WebSocketClient webSocketClient = new WebSocketClient();
        return webSocketClient;
    }

    @Bean
    CoinBaseProWebSocketManager getCoinBaseWebSocketManager(){
        SubscriptionMessage sub = new SubscriptionMessage();
        sub.setType(MessageType.SUBSCRIBE);
        List<String> productsIds = new ArrayList<>();
        productsIds.add("BTC-USD");
        productsIds.add("BTC-EUR");
        productsIds.add("ETH-USD");
        productsIds.add("ETH-EUR");
        List<Object> channels = new ArrayList<>();
        channels.add(MessageType.TICKER);
        sub.setProductIds(productsIds);
        sub.setChannels(channels);
        CoinBaseProWebSocketManager manager = new CoinBaseProWebSocketManager(sub);
        return manager;
    }
}
