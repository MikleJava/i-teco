package ru.girfanov.tm.util;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class TerminalUtil {

    @NotNull
    public static final Scanner scanner = new Scanner(System.in);

    @NotNull
    public static final String[] sortValue = {"date start", "date end", "status"};
}
