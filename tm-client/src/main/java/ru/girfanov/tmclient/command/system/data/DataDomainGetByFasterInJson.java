package ru.girfanov.tmclient.command.system.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tmclient.command.AbstractSystemCommand;
import ru.girfanov.tmserver.endpoint.DataDomainEndPoint;

@Getter
@NoArgsConstructor
public class DataDomainGetByFasterInJson extends AbstractSystemCommand<String> {

    @NotNull
    private final String name = "--dd gfj";

    @NotNull
    private final String description = "get data by fasterXML in json";

    @Override
    public void execute(@Nullable final String ... params) {
        final DataDomainEndPoint dataDomainEndPoint = serviceLocator.getDataDomainEndPoint();
        dataDomainEndPoint.getDataByFasterInJson();
    }
}
