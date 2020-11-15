package com.mpek.coinwebsocket.model;

import lombok.Data;

@Data
public class ErrorMessage {
    MessageType type;
    String message;
    String reason;
}
