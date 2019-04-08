package ru.girfanov.tm.exception;

public class WrongSessionException extends RuntimeException {
    public WrongSessionException(String message) {
        super(message);
    }
}
