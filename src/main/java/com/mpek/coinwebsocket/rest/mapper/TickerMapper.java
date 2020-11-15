package com.mpek.coinwebsocket.rest.mapper;

import com.mpek.coinwebsocket.model.TickerMessage;
import com.mpek.coinwebsocket.model.TickerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface TickerMapper {
    TickerMapper INSTANCE = Mappers.getMapper(TickerMapper.class);

    @Mapping(source = "productId", target = "instrument", qualifiedByName = "productIdToInstrument")
    @Mapping(source = "bestBid", target = "bid")
    @Mapping(source = "bestAsk", target = "ask")
    @Mapping(source = "price", target = "last")
    @Mapping(source = "time", target = "time", qualifiedByName = "timeStampToTime")
    TickerDTO toTickerDTO(TickerMessage message);

    @Named("productIdToInstrument")
    static String productIdToInstrument(String productId) {
        return productId.replaceAll("-", "");
    }

    @Named("timeStampToTime")
    static String timeStampToTime(Date timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(timeStamp);
    }

}
