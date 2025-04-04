package com.example.stock_exchange_service.order;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.stock_exchange_service.util.Enums.OrderType;

import reactor.core.publisher.Flux;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, Long>{
	
	Flux<Order> findTop10ByTypeOrderByPriceDesc(OrderType type);

    Flux<Order> findTop10ByTypeOrderByPriceAsc(OrderType type);
}
