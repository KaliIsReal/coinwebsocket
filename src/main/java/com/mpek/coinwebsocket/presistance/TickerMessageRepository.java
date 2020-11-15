package com.mpek.coinwebsocket.presistance;

import com.mpek.coinwebsocket.model.TickerMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TickerMessageRepository extends CrudRepository<TickerMessage, String> {

}
