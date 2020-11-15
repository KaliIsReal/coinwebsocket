package com.mpek.coinwebsocket.websocket;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

@Data
@ClientEndpoint
public class WebSocketClient {
    private Logger logger = LoggerFactory.getLogger(WebSocketClient.class);

    @Value("${text-buffer-size:3276800}")
    private int textBufferSize;
    
    @Value("${binary-buffer-size:3276800}")
    private int binaryBufferSize;

    private Session session = null;
    private MessageHandler messageHandler;
    private ConnectionHandler connectionHandler;


    public void connectTo(String url){
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.setDefaultMaxTextMessageBufferSize(textBufferSize);
            container.setDefaultMaxBinaryMessageBufferSize(binaryBufferSize);
            container.connectToServer(this, URI.create(url));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void OnOpen (Session session) throws IOException {
        logger.info(String.format("Established connection with id %s", session.getId()));
        this.session = session;
    }

    @OnMessage
    public void OnMessage (String message) {
        logger.info(String.format("Recived message %s", message));
        if (messageHandler != null) {
            try {
                messageHandler.handleMessage(message);
            }catch (Exception ex){
                logger.warn(String.format("During handling message occurred exception"), ex);
            }
        }
    }
    @OnClose
    public void OnClose (CloseReason reason) {
        logger.info(String.format("Closing connection, reason: %s", reason.getReasonPhrase()));
        if(connectionHandler != null){
            connectionHandler.handleClosingSession(reason);
        }
    }

    public void sendMessage(String message) {
        this.session.getAsyncRemote().sendText(message);
    }

    public void addMessageHandler(final MessageHandler msgHandler) {
        messageHandler = msgHandler;
    }
    public void addConnectionHandler(final ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    public static interface MessageHandler {
        public void handleMessage(String message);
    }

    public static interface ConnectionHandler {
        public void handleClosingSession(CloseReason reason);
    }
}
