package ru.girfanov.tm.command;

import ru.girfanov.tm.bootstrap.Bootstrap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public abstract class AbstractCommand<T> {

    protected Bootstrap bootstrap;
    protected Scanner scanner = new Scanner(System.in);
    protected DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");

    private boolean isSecure = false;

    public boolean isSecure() {
        return isSecure;
    }

    public AbstractCommand(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public abstract T getName();
    public abstract T getDescription();
    public abstract void execute(T ... parameters);
}
