package ru.girfanov.tm.command.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.DataDomainEndPoint;
import ru.girfanov.tm.endpoint.SessionDto;

@Getter
@NoArgsConstructor
public class DataDomainSaveByFasterInXml extends AbstractSecureCommand {

    @NotNull private final String name = "--dd sfx";

    @NotNull private final String description = "save data by fasterXML in xml";

    @Override
    public void execute(@NotNull final SessionDto session) {
        final DataDomainEndPoint dataDomainEndPoint = serviceLocator.getDataDomainEndPoint();
        dataDomainEndPoint.saveDataByFasterInXml(session);
    }
}

