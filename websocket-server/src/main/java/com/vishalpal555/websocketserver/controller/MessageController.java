package com.vishalpal555.websocketserver.controller;

import com.vishalpal555.websocketserver.dto.Message;
import com.vishalpal555.websocketserver.dto.ResponseMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.time.LocalDateTime;

@Controller
public class MessageController {
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(final Message message){
        String data;
        System.out.println(message);
        data = HtmlUtils.htmlEscape(message.getMessageContent());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return ResponseMessage.builder()
                .content(data)
                .build();
    }
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Scheduled(fixedRate = 3000)
    private void sendDataAutomatically(){
        System.out.println("send data started .....");
        simpMessagingTemplate.convertAndSend("/topic/messages", Message.builder().messageContent(LocalDateTime.now().toString()).build());
    }
}
