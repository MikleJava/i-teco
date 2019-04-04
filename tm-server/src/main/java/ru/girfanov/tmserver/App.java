package ru.girfanov.tmserver;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tmserver.api.ServiceLocator;
import ru.girfanov.tmserver.bootstrap.Bootstrap;

public final class App {
    public static void main(String[] args) {
        @NotNull
        ServiceLocator serviceLocator = new Bootstrap();
        serviceLocator.init();
    }
}
