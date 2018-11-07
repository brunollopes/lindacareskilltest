package com.lindacare.demo.mapper;

import org.mapstruct.Mapper;

import com.lindacare.demo.dto.TradeDto;
import com.lindacare.demo.model.TradeMessages;


@Mapper(componentModel = "spring")
public interface TradeMapper {

    TradeMessages toTradeMessage(TradeDto dto);

}
