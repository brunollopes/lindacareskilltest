package com.lindacare.demo.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import lombok.Data;

@Data
@Entity
public class TradeMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer userId;

    private String originatingCountry;

    private Double rate;

    private String currencyFrom;

    private String currencyTo;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date timePlaced;

    private Long entryDate;

    private Double amountBuy;

    private Double amountSell;

}
