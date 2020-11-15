package com.mpek.coinwebsocket.model;

import lombok.Data;

@Data
public class TickerDTO {
    private String instrument;
    private double bid;
    private double ask;
    private double last;
    private String time;
}
