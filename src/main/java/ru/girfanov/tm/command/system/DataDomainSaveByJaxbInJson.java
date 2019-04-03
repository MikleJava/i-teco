package ru.girfanov.tm.command.system;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.api.service.IDataDomainService;
import ru.girfanov.tm.command.AbstractSystemCommand;

@Getter
@NoArgsConstructor
public class DataDomainSaveByJaxbInJson extends AbstractSystemCommand<String> {

    @NotNull
    private final String name = "--dd sjaxbj";

    @NotNull
    private final String description = "save data by jax-b in json";

    @Override
    public void execute(@Nullable final String ... params) {
        IDataDomainService dataDomainService = serviceLocator.getDataDomainService();
        dataDomainService.saveDataByJaxbInJson();
    }
}

