package ru.girfanov.tm;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.ServiceLocator;
import ru.girfanov.tm.bootstrap.Bootstrap;

public final class App {
    public static void main(String[] args) {
        @NotNull
        ServiceLocator serviceLocator = new Bootstrap();
        serviceLocator.init();
    }
}
