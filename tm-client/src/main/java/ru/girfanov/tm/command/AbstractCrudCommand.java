package ru.girfanov.tm.command;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class AbstractCrudCommand extends AbstractSystemCommand<String> {

    private final boolean isSecure = true;

    public boolean isSecure() {
        return isSecure;
    }
}
