package ru.girfanov.tmclient.command.system.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tmserver.api.service.IDataDomainService;
import ru.girfanov.tmclient.command.AbstractSystemCommand;

@Getter
@NoArgsConstructor
public class DataDomainGetByJaxbInXml extends AbstractSystemCommand<String> {

    @NotNull
    private final String name = "--dd gjaxbx";

    @NotNull
    private final String description = "get data by jax-b in xml";

    @Override
    public void execute(@Nullable final String ... params) {
        IDataDomainService dataDomainService = serviceLocator.getDataDomainService();
        dataDomainService.getDataByJaxbInXml();
    }
}
