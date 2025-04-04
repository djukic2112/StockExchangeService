package com.example.stock_exchange_service.util;

import com.example.stock_exchange_service.order.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
	
	private String status;
    private String message;
    private Order order;
}
