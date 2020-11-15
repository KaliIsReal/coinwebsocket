package com.mpek.coinwebsocket.rest.service;

import com.mpek.coinwebsocket.model.TickerMessage;
import com.mpek.coinwebsocket.rest.mapper.TickerMapper;
import com.mpek.coinwebsocket.model.TickerDTO;
import com.mpek.coinwebsocket.presistance.TickerMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
public class TickerService {
    @Autowired
    TickerMessageRepository repository;

    public List<TickerDTO> getLeast(){
        List<TickerDTO> tickerMessages = new ArrayList<>();
        Iterator<TickerMessage> tickerMapperIterator = repository.findAll().iterator();
        while(tickerMapperIterator.hasNext()){
            TickerMessage tmpMessage = tickerMapperIterator.next();
            tickerMessages.add(TickerMapper.INSTANCE.toTickerDTO(tmpMessage));
        }
        return tickerMessages;
    }
}
