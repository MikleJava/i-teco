package ru.girfanov.tmclient.command.system.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tmserver.api.service.IDataDomainService;
import ru.girfanov.tmclient.command.AbstractSystemCommand;

@Getter
@NoArgsConstructor
public class DataDomainSaveByFasterInJson extends AbstractSystemCommand<String> {

    @NotNull
    private final String name = "--dd sfj";

    @NotNull
    private final String description = "save data by fasterXML in json";

    @Override
    public void execute(@Nullable final String ... params) {
        IDataDomainService dataDomainService = serviceLocator.getDataDomainService();
        dataDomainService.saveDataByFasterInJson();
    }
}

