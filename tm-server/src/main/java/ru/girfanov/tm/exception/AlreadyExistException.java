package ru.girfanov.tm.exception;

import org.jetbrains.annotations.NotNull;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(@NotNull final String message) {
        super(message);
    }
}
