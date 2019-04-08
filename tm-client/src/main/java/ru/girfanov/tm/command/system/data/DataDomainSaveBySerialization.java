package ru.girfanov.tm.command.system.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.command.AbstractSystemCommand;
import ru.girfanov.tmserver.endpoint.DataDomainEndPoint;

@Getter
@NoArgsConstructor
public class DataDomainSaveBySerialization extends AbstractSystemCommand<String> {

    @NotNull
    private final String name = "--dd ss";

    @NotNull
    private final String description = "save data by serialization";

    @Override
    public void execute(@Nullable final String ... params) {
        final DataDomainEndPoint dataDomainEndPoint = serviceLocator.getDataDomainEndPoint();
        dataDomainEndPoint.saveDataBySerialization();

    }
}

