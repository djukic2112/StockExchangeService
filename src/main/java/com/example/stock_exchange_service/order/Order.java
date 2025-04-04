package com.example.stock_exchange_service.order;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.example.stock_exchange_service.util.Enums.OrderType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	 	@Id
	    private Long id; 
	    private BigDecimal price;  
	    private BigDecimal amount; 
	    private OrderType type;
}
