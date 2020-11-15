package com.mpek.coinwebsocket.websocket;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mpek.coinwebsocket.exception.CoinBaseException;
import com.mpek.coinwebsocket.model.ErrorMessage;
import com.mpek.coinwebsocket.model.SubscriptionMessage;
import com.mpek.coinwebsocket.model.TickerMessage;
import com.mpek.coinwebsocket.presistance.TickerMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public class CoinBaseProWebSocketManager {
    private Logger logger = LoggerFactory.getLogger(CoinBaseProWebSocketManager.class);

    @Value("${coinbase_url:wss://ws-feed.pro.coinbase.com}")
    private String COINBASE_URL;
    @Autowired
    private WebSocketClient webSocketClient;
    @Autowired
    TickerMessageRepository repository;
    private ObjectMapper mapper = new ObjectMapper();
    private SubscriptionMessage subscription;
    @Value("${retry_connection:true}")
    private boolean retryConnection;

    public CoinBaseProWebSocketManager(SubscriptionMessage subscription) {
        this.subscription = subscription;
    }

    public void sendSubscriptionMessage(SubscriptionMessage subscription) throws JsonProcessingException {
        String jsonSub = mapper.writeValueAsString(subscription);
        webSocketClient.sendMessage(jsonSub);
    }

    @PostConstruct
    private void init() {
        webSocketClient.addMessageHandler(message -> {
            try {
                handleMessage(message);
            } catch (JsonProcessingException jsonException) {
                logger.warn(String.format("Unable process json"), jsonException);
            }
            catch (CoinBaseException coinBaseException) {
                logger.warn(String.format("Error during connection"), coinBaseException);
            }
        });

        webSocketClient.addConnectionHandler(reason -> {
            if (retryConnection) {
                connectToCoinBaseFeed();
                retryConnection = false;
            }
        });
        connectToCoinBaseFeed();
    }

    private void connectToCoinBaseFeed() {
        try {
            webSocketClient.connectTo(COINBASE_URL);
            sendSubscriptionMessage(subscription);
        } catch (Exception e) {
            logger.error(String.format("Unable initialize webSocket client"), e);
        }
    }

    private void handleMessage(String message) throws JsonProcessingException, CoinBaseException {
        final ObjectNode node = new ObjectMapper().readValue(message, ObjectNode.class);
        if (node.has("type")) {
            String type = node.get("type").asText();
            switch (type) {
                case "ticker":
                    TickerMessage tickerMessage = mapper.convertValue(node, TickerMessage.class);
                    repository.save(tickerMessage);
                    break;
                case "error":
                    ErrorMessage error = mapper.convertValue(node, ErrorMessage.class);
                    throw new CoinBaseException(error.getReason());
            }
        }
    }
}
