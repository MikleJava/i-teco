package ru.girfanov.tm;

import ru.girfanov.tm.bootstrap.Bootstrap;

import javax.enterprise.inject.se.SeContainerInitializer;

public class ApplicationServer {
    public static void main(String[] args) {
        SeContainerInitializer.newInstance()
                .addPackages(ApplicationServer.class)
                .initialize().select(Bootstrap.class).get().init();
    }
}
