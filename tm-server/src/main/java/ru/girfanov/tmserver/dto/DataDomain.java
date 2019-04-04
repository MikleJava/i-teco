package ru.girfanov.tmserver.dto;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tmserver.entity.Project;
import ru.girfanov.tmserver.entity.Task;
import ru.girfanov.tmserver.entity.User;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@XmlRootElement(name = "DataDomain")
public class DataDomain {

    @NotNull Collection<Project> projects = new ArrayList<>();

    @NotNull Collection<Task> tasks = new ArrayList<>();

    @NotNull Collection<User> users = new ArrayList<>();

}
