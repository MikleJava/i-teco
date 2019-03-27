package ru.girfanov.tm.util;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Terminal {

    @NotNull
    public static final Scanner scanner = new Scanner(System.in);

    @NotNull
    public static final DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");

}
