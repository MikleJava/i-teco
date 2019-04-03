package ru.girfanov.tm.command.system.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.service.IDataDomainService;
import ru.girfanov.tm.command.AbstractSystemCommand;

@Getter
@NoArgsConstructor
public class DataDomainGetByFasterInJson extends AbstractSystemCommand<String> {

    @NotNull
    private final String name = "--dd gfj";

    @NotNull
    private final String description = "get data by fasterXML in json";

    @Override
    public void execute(@Nullable final String ... params) {
        IDataDomainService dataDomainService = serviceLocator.getDataDomainService();
        dataDomainService.getDataByFasterInJson();
    }
}
