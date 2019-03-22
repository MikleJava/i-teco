package ru.girfanov.tm;

import ru.girfanov.tm.bootstrap.Bootstrap;
import ru.girfanov.tm.create.Create;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.delete.Delete;
import ru.girfanov.tm.info.Information;
import ru.girfanov.tm.select.Select;
import ru.girfanov.tm.update.Update;

import java.util.*;

public class App {

    static {
        System.out.println("input --help to get info");
        System.out.println("input --exit to close application");
    }

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}
