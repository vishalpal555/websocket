package com.vishalpal555.websocketclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

@Service
public class ClientService {
    @Autowired
    private WebSocketStompClient stompClient;
    private String webSocketServerUrl = "ws://127.0.0.1:8080/our-websocket";

    public void connect() throws ExecutionException, InterruptedException {
        StompSessionHandler sessionHandler = new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                System.out.println("session connected");
                session.subscribe("/topic/messages", this);
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                System.out.println("received: " +payload);
            }

            @Override
            public Type getPayloadType(StompHeaders headers) {
                return HashMap.class;
            }

            @Override
            public void handleTransportError(StompSession session, Throwable exception) {
                System.out.println("transport error " +exception.getLocalizedMessage());
            }

            @Override
            public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
                System.out.println("exception occured at sessionHandler " +exception.getLocalizedMessage());
            }
        };

        StompSession stompSession = stompClient.connect(webSocketServerUrl, sessionHandler).get();
        // Perform actions after successful connection
    }
}
