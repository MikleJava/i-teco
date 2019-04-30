package ru.girfanov.tm.api;

import ru.girfanov.tm.endpoint.*;

public interface ServiceLocator {
    void init(Class[] commandClasses);
    void registerCommand(Class clazz);
    void setSessionDto(SessionDto sessionDto);
}
