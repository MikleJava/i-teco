package ru.girfanov.tm.exception;

public class IncorrectRoleException extends RuntimeException {
    public IncorrectRoleException(String message) {
        super(message);
    }
}
