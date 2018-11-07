package com.lindacare.demo.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lindacare.demo.model.TradeMessages;

public interface TradeMessageDao extends JpaRepository<TradeMessages, Integer> {

    public List<TradeMessages> findByEntryDateAfter(long timestamp);

}
