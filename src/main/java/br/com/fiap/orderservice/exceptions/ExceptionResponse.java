package br.com.fiap.orderservice.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import lombok.*;

@Getter
public class ExceptionResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String details;
    public ExceptionResponse(LocalDateTime timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
