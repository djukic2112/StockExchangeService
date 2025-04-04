package com.example.stock_exchange_service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.stock_exchange_service.config.OrderWebSocketHandler;
import com.example.stock_exchange_service.util.Enums.OrderType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final OrderWebSocketHandler orderWebSocketHandler;

    @Autowired
    public OrderService(OrderRepository orderRepository, OrderWebSocketHandler orderWebSocketHandler) {
        this.orderRepository = orderRepository;
        this.orderWebSocketHandler = orderWebSocketHandler;
    }
 
    public Mono<Order> createOrder(Order order) {
    	return orderRepository.save(order)
                .doOnSuccess(savedOrder -> {
                    orderWebSocketHandler.sendToAll(savedOrder);
                });
    }
   
    public Flux<Order> getTopBuyOrders() {
        return orderRepository.findTop10ByTypeOrderByPriceDesc(OrderType.BUY);
    }

    public Flux<Order> getTopSellOrders() {
        return orderRepository.findTop10ByTypeOrderByPriceAsc(OrderType.SELL);
    }
}
