package ru.girfanov.tm.command.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.DataDomainEndPoint;
import ru.girfanov.tm.endpoint.SessionDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@NoArgsConstructor
public class DataDomainGetByFasterInXml extends AbstractSecureCommand {

    @Getter @NotNull private final String name = "--dd gfx";

    @Getter @NotNull private final String description = "get data by fasterXML in xml";

    @Inject
    private DataDomainEndPoint dataDomainEndPoint;

    @Override
    public void execute(@NotNull final SessionDto session) {
        dataDomainEndPoint.getDataByFasterInXml(session);
    }
}

