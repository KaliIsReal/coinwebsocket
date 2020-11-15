package com.mpek.coinwebsocket.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MessageType {
    SUBSCRIBE("subscribe"),
    UNSUBSCRIBE("unsubscribe"),
    SUBSCRIPTIONS("subscriptions"),
    TICKER("ticker"),
    HEARTBEAT("heartbeat"),
    STATUS("status"),
    SNAPSHOT("snapshot"),
    L2UPDATE("l2update");

    private final String displayName;

    MessageType(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}
