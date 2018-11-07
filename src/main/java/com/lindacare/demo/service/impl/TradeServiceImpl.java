package com.lindacare.demo.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lindacare.demo.dao.TradeMessageDao;
import com.lindacare.demo.dto.TradeDto;
import com.lindacare.demo.mapper.TradeMapper;
import com.lindacare.demo.model.TradeMessages;
import com.lindacare.demo.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    TradeMapper tradeMapper;
    @Autowired
    TradeMessageDao messageDao;

    @Override
    public void saveTrade(TradeDto dto) {
        TradeMessages tm = tradeMapper.toTradeMessage(dto);
        tm.setEntryDate(System.currentTimeMillis());
        messageDao.save(tm);
    }

    @Override
    public List<TradeMessages> getTradeMessage() {
        return messageDao.findByEntryDateAfter(getDate());
    }

    @Override
    public List<TradeMessages> getNewTradeMessages(long timestamp) {
        timestamp = timestamp < getDate() ? getDate() : timestamp;
        return messageDao.findByEntryDateAfter(timestamp);
    }

    private long getDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
}
