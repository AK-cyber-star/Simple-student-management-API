package com.akcs.learnSpring.communication;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class APIResponse {
    private String message;
    private Object data;
    private Instant timestamp;
}
