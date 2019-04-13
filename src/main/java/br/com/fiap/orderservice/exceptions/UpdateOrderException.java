package br.com.fiap.orderservice.exceptions;

public class UpdateOrderException extends RuntimeException {
    public UpdateOrderException(String message) {
        super(message);
    }
}