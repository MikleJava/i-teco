package ru.girfanov.tm.exception;

import org.jetbrains.annotations.NotNull;

public class AlreadyExistException extends Exception {
    public AlreadyExistException(@NotNull final String message) {
        super(message);
    }
}
