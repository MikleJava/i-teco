package ru.girfanov.tmclient.command;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class AbstractCrudCommand extends AbstractSystemCommand<String> {

    private final boolean isSecure = true;

    public boolean isSecure() {
        return isSecure;
    }
}
