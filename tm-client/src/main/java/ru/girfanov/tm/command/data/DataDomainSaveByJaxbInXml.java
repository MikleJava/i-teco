package ru.girfanov.tm.command.data;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.command.AbstractSecureCommand;
import ru.girfanov.tm.endpoint.DataDomainEndPoint;
import ru.girfanov.tm.endpoint.SessionDto;

@Component
public final class DataDomainSaveByJaxbInXml extends AbstractSecureCommand {

    @Getter @NotNull private final String name = "--dd sjaxbx";

    @Getter @NotNull private final String description = "save data by jax-b in xml";

    @Autowired
    private DataDomainEndPoint dataDomainEndPoint;

    @Override
    public void execute(@NotNull final SessionDto session) {
        dataDomainEndPoint.saveDataByJaxbInXml(session);
    }
}

