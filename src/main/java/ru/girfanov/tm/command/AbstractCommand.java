package ru.girfanov.tm.command;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.ServiceLocator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

@RequiredArgsConstructor
@NoArgsConstructor
public abstract class AbstractCommand<T> {

    @NonNull
    protected ServiceLocator serviceLocator;
    @NotNull
    protected Scanner scanner = new Scanner(System.in);
    @NotNull
    protected final DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");

    private final boolean isSecure = false;

    public boolean isSecure() {
        return isSecure;
    }

    public abstract T getName();
    public abstract T getDescription();
    public abstract void execute(final T ... parameters);
}
