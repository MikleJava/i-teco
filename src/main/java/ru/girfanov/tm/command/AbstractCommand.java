package ru.girfanov.tm.command;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.ServiceLocator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public abstract class AbstractCommand<T> {

    @NotNull
    protected final ServiceLocator serviceLocator;
    @NotNull
    protected Scanner scanner = new Scanner(System.in);
    @NotNull
    protected final DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");

    private final boolean isSecure = false;

    public AbstractCommand(@NotNull final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public boolean isSecure() {
        return isSecure;
    }

    public abstract T getName();
    public abstract T getDescription();
    public abstract void execute(final T ... parameters);
}
