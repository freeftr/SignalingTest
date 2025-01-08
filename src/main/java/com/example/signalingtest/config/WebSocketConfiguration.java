package com.example.signalingtest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration{

    public void configureMessageBroker (MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); //broker url 설정
        config.setApplicationDestinationPrefixes("/app"); //send url 설정
    }

    public void registerStompEndPoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/signaling") // webSocket 접속시 endpont 설정
                .setAllowedOrigins("*") // cors에 따른 설정
                .withSockJS(); // 브라우저에서 WebSocket을 지원하지 않는 경우에 대안으로 애플리케이션의 코드를 변경할 필요 없이 런타임에 필요할 때 대체하기 위해 설정
    }
}
