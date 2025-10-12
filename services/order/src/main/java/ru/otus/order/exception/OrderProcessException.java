package ru.otus.order.exception;

public class OrderProcessException extends RuntimeException {
    public OrderProcessException(String message) {
        super(message);
    }
    public OrderProcessException(String message, Throwable cause) {}
}
