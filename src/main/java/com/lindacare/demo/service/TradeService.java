package com.lindacare.demo.service;

import com.lindacare.demo.dto.TradeDto;
import com.lindacare.demo.model.TradeMessages;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeService {

    void saveTrade(TradeDto dto);

    public List<TradeMessages> getTradeMessage();

    public List<TradeMessages> getNewTradeMessages(long timestamp);
}
