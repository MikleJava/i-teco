package ru.girfanov.tm.command.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.DataDomainEndPoint;
import ru.girfanov.tm.endpoint.Session;

@Getter
@NoArgsConstructor
public class DataDomainGetByJaxbInJson extends AbstractSecureCommand {

    @NotNull private final String name = "--dd gjaxbj";

    @NotNull private final String description = "get data by jax-b in json";

    @Override
    public void execute(@NotNull final Session session) {
        final DataDomainEndPoint dataDomainEndPoint = serviceLocator.getDataDomainEndPoint();
        dataDomainEndPoint.getDataByJaxbInJson(session);
    }
}
