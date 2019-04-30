package ru.girfanov.tm.exception;

public class WrongSessionException extends Exception {
    public WrongSessionException(String message) {
        super(message);
    }
}
