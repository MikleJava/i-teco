package ru.girfanov.tm;

import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.bootstrap.Bootstrap;

public final class App {

    static {
        System.out.println("input --help to get info");
        System.out.println("input --exit to close application");
    }

    public static void main(String[] args) {
        ServiceLocator serviceLocator = new Bootstrap();
        serviceLocator.init();
    }
}
