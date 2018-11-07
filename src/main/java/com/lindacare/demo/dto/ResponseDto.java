package com.lindacare.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResponseDto<T> {

    private boolean status = true;
    private T data;
    private String message;
    private long timestamp;

    public ResponseDto(String message) {
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public void setDate(T data) {
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }
}
