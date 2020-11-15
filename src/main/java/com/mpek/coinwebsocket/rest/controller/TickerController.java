package com.mpek.coinwebsocket.rest.controller;

import com.mpek.coinwebsocket.model.TickerDTO;
import com.mpek.coinwebsocket.rest.service.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ticker")
public class TickerController {
    @Autowired
    private TickerService service;

    @RequestMapping("least")
    public List<TickerDTO> getLeastTicker(){
        return service.getLeast();
    }

}
