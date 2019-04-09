package ru.girfanov.tm.command.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.DataDomainEndPoint;
import ru.girfanov.tm.endpoint.Session;

@Getter
@NoArgsConstructor
public class DataDomainGetByFasterInJson extends AbstractSecureCommand {

    @NotNull private final String name = "--dd gfj";

    @NotNull private final String description = "get data by fasterXML in json";

    @Override
    public void execute(@NotNull final Session session) {
        final DataDomainEndPoint dataDomainEndPoint = serviceLocator.getDataDomainEndPoint();
        dataDomainEndPoint.getDataByFasterInJson(session);
    }
}
