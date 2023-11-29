package com.vishalpal555.websocketserver.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private String messageContent;
}
