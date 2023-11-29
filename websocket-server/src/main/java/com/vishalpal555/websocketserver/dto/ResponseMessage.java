package com.vishalpal555.websocketserver.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseMessage {
    private String content;
}
