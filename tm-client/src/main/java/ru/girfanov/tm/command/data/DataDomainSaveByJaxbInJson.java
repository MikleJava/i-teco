package ru.girfanov.tm.command.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.Session;
import ru.girfanov.tm.endpoint.DataDomainEndPoint;

@Getter
@NoArgsConstructor
public class DataDomainSaveByJaxbInJson extends AbstractSecureCommand {

    @NotNull private final String name = "--dd sjaxbj";

    @NotNull private final String description = "save data by jax-b in json";

    @Override
    public void execute(@NotNull final Session session) {
        final DataDomainEndPoint dataDomainEndPoint = serviceLocator.getDataDomainEndPoint();
        dataDomainEndPoint.saveDataByJaxbInJson(session);
    }
}
