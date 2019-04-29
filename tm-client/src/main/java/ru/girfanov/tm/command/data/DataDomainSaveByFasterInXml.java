package ru.girfanov.tm.command.data;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.DataDomainEndPoint;
import ru.girfanov.tm.endpoint.SessionDto;

import javax.inject.Inject;

public final class DataDomainSaveByFasterInXml extends AbstractSecureCommand {

    @Getter @NotNull private final String name = "--dd sfx";

    @Getter @NotNull private final String description = "save data by fasterXML in xml";

    @Inject
    private DataDomainEndPoint dataDomainEndPoint;

    @Override
    public void execute(@NotNull final SessionDto session) {
        dataDomainEndPoint.saveDataByFasterInXml(session);
    }
}

