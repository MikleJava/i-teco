package ru.girfanov.tm.command;

import ru.girfanov.tm.api.ServiceLocator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public abstract class AbstractCommand<T> {

    protected final ServiceLocator serviceLocator;
    protected Scanner scanner = new Scanner(System.in);
    protected final DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");

    private final boolean isSecure = false;

    public AbstractCommand(final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public boolean isSecure() {
        return isSecure;
    }

    public abstract T getName();
    public abstract T getDescription();
    public abstract void execute(final T ... parameters);
}
