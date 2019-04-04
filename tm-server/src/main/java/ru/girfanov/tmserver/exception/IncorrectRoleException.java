package ru.girfanov.tmserver.exception;

public class IncorrectRoleException extends RuntimeException {
    public IncorrectRoleException(String message) {
        super(message);
    }
}
