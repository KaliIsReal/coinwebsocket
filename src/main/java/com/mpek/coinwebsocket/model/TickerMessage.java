package com.mpek.coinwebsocket.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Entity
public class TickerMessage{
    @Id
    private String productId;
    private long tradeId;
    private MessageType type;
    private long sequence;
    private Date time;
    private String price;
    private String side;
    private String lastSize;
    private String bestBid;
    private String bestAsk;
    @JsonProperty("open_24h")
    private String open24h;
    @JsonProperty("volume_24h")
    private String volume24h;
    @JsonProperty("volume_30d")
    private String volume30d;
    @JsonProperty("low_24h")
    private String low24h;
    @JsonProperty("high_24h")
    private String high24h;
}

