package com.example.stock_exchange_service.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.stock_exchange_service.order.Order;
import com.example.stock_exchange_service.util.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class OrderWebSocketHandler extends TextWebSocketHandler {

	private final ObjectMapper objectMapper;

    public OrderWebSocketHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    private final List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }
    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session); 
    }
    
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        
        String jsonMessage = message.getPayload();
        Order order = objectMapper.readValue(jsonMessage, Order.class);

        // **Business Logic**:
        // At this point, you can validate the order object if necessary 
        // or just handle a received message on the other way.
        
        // For example, check if the price, amount, and type are valid.
        // You could also store the order in the database if needed and then return this
        // Response response = new Response("success", "New order successfully recorded." , order);
        

        Response response = new Response("success", "Successfully received message." , null);
        String responseJson = objectMapper.writeValueAsString(response);
        session.sendMessage(new TextMessage(responseJson));
    }

    public void sendToAll(Order order) {
        try {
            Response response = new Response("success", "New order successfully recorded.", order);
            String responseJson = objectMapper.writeValueAsString(response);

            for (WebSocketSession session : sessions) {
                try {
                    session.sendMessage(new TextMessage(responseJson));
                } catch (Exception e) {
                	e.printStackTrace();
                    Response errorResponse = new Response("error", "Failed to send message", null);
                    String errorJson = objectMapper.writeValueAsString(errorResponse);
                    session.sendMessage(new TextMessage(errorJson));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }
}
