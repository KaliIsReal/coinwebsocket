package com.mpek.coinwebsocket.exception;

import com.mpek.coinwebsocket.websocket.CoinBaseProWebSocketManager;

public class CoinBaseException extends Exception{
    public CoinBaseException(String message){
        super(message);
    }
}
