package com.lindacare.demo.dto;

import java.util.Date;
import lombok.Data;

@Data
public class TradeDto {

    private Integer userId;

    private String originatingCountry;

    private Double rate;

    private String currencyFrom;

    private String currencyTo;

    private Date timePlaced;

    private Double amountBuy;

    private Double amountSell;
}
