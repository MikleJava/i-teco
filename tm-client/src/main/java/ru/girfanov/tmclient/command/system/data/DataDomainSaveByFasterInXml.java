package ru.girfanov.tmclient.command.system.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tmserver.api.service.IDataDomainService;
import ru.girfanov.tmclient.command.AbstractSystemCommand;

@Getter
@NoArgsConstructor
public class DataDomainSaveByFasterInXml extends AbstractSystemCommand<String> {

    @NotNull
    private final String name = "--dd sfx";

    @NotNull
    private final String description = "save data by fasterXML in xml";

    @Override
    public void execute(@Nullable final String ... params) {
        IDataDomainService dataDomainService = serviceLocator.getDataDomainService();
        dataDomainService.saveDataByFasterInXml();
    }
}

