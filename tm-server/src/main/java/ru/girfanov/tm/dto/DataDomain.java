package ru.girfanov.tm.dto;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@XmlRootElement(name = "DataDomain")
public final class DataDomain {

    @NotNull Collection<Project> projects = new ArrayList<>();

    @NotNull Collection<Task> tasks = new ArrayList<>();

    @NotNull Collection<User> users = new ArrayList<>();

}
