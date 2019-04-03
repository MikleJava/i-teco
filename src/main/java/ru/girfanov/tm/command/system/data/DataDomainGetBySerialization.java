package ru.girfanov.tm.command.system.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.service.IDataDomainService;
import ru.girfanov.tm.command.AbstractSystemCommand;

@Getter
@NoArgsConstructor
public class DataDomainGetBySerialization extends AbstractSystemCommand<String> {

    @NotNull
    private final String name = "--dd gs";

    @NotNull
    private final String description = "get data by serialization";

    @Override
    public void execute(@Nullable final String ... params) {
        IDataDomainService dataDomainService = serviceLocator.getDataDomainService();
        dataDomainService.getDataBySerialization();
    }
}
