package com.lindacare.demo.controller;

import com.lindacare.demo.dto.ResponseDto;
import com.lindacare.demo.dto.TradeDto;
import com.lindacare.demo.service.TradeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class TradeController {

    @Autowired
    TradeService tradeService;

    @PostMapping("trade")
    public ResponseEntity<?> insert(@RequestBody TradeDto dto) {
        tradeService.saveTrade(dto);
        return ResponseEntity.ok(new ResponseDto("Trade Message Save"));
    }

    @GetMapping("trade")
    public ResponseEntity<?> get() {
        ResponseDto response = new ResponseDto("Trade Message Save");
        response.setData(tradeService.getTradeMessage());
        return ResponseEntity.ok(response);
    }

    @GetMapping("trade/{timestamp}")
    public ResponseEntity<?> getByTime(@PathVariable long timestamp) {
        ResponseDto response = new ResponseDto("Trade Message Save");
        response.setData(tradeService.getNewTradeMessages(timestamp));
        return ResponseEntity.ok(response);
    }

}
