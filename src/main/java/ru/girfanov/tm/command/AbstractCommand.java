package ru.girfanov.tm.command;

import ru.girfanov.tm.bootstrap.Bootstrap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public abstract class AbstractCommand<T> {

    Bootstrap bootstrap;
    Scanner scanner = new Scanner(System.in);
    DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");

    public AbstractCommand(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public abstract T getName();
    public abstract T getDescription();
    public abstract void execute();
}
