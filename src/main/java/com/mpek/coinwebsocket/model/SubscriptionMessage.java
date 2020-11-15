package com.mpek.coinwebsocket.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SubscriptionMessage {
    private MessageType type;
    private List<String> productIds;
    private List<Object> channels;
}
