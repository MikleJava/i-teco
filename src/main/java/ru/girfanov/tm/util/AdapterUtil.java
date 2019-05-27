package ru.girfanov.tm.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.dto.AbstractEntityDto;
import ru.girfanov.tm.dto.ProjectDto;
import ru.girfanov.tm.dto.TaskDto;
import ru.girfanov.tm.dto.UserDto;
import ru.girfanov.tm.entity.AbstractEntity;
import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.entity.User;
import ru.girfanov.tm.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdapterUtil<T extends AbstractEntityDto, S extends AbstractEntity> {

    @Autowired
    private IUserService userService;

    @Autowired
    private IProjectService projectService;

    @Nullable
    public T convertToEntityDto(@NotNull final S entity) {
        if(entity instanceof User) {
            final UserDto userDto = new UserDto();
            userDto.setId(entity.getId());
            userDto.setLogin(((User) entity).getLogin());
            userDto.setPassword(((User) entity).getPassword());
            return (T) userDto;
        }
        if(entity instanceof Project) {
            final ProjectDto projectDto = new ProjectDto();
            projectDto.setId(entity.getId());
            projectDto.setName(((Project) entity).getName());
            projectDto.setDescription(((Project) entity).getDescription());
            projectDto.setStatus(((Project) entity).getStatus());
            projectDto.setDateStart(((Project) entity).getDateStart());
            projectDto.setDateEnd(((Project) entity).getDateEnd());
            projectDto.setUserId(((Project) entity).getUser().getId());
            return (T) projectDto;
        }
        if(entity instanceof Task) {
            final TaskDto taskDto = new TaskDto();
            taskDto.setId(entity.getId());
            taskDto.setName(((Task) entity).getName());
            taskDto.setDescription(((Task) entity).getDescription());
            taskDto.setStatus(((Task) entity).getStatus());
            taskDto.setDateStart(((Task) entity).getDateStart());
            taskDto.setDateEnd(((Task) entity).getDateEnd());
            taskDto.setProjectId(((Task) entity).getProject().getId());
            taskDto.setUserId(((Task) entity).getUser().getId());
            return (T) taskDto;
        }
        return null;
    }

    @Nullable
    public S convertToEntity(@NotNull final T entityDto) {
        if(entityDto instanceof UserDto) {
            final User user = new User();
            user.setId(entityDto.getId());
            user.setLogin(((UserDto) entityDto).getLogin());
            user.setPassword(((UserDto) entityDto).getPassword());
            return (S) user;
        }
        if(entityDto instanceof ProjectDto) {
            final Project project = new Project();
            project.setId(entityDto.getId());
            project.setName(((ProjectDto) entityDto).getName());
            project.setDescription(((ProjectDto) entityDto).getName());
            project.setStatus(((ProjectDto) entityDto).getStatus());
            project.setDateStart(((ProjectDto) entityDto).getDateStart());
            project.setDateEnd(((ProjectDto) entityDto).getDateEnd());
            project.setUser(userService.findOne(((ProjectDto) entityDto).getUserId()));
            return (S) project;
        }
        if(entityDto instanceof TaskDto) {
            final Task task = new Task();
            task.setId(entityDto.getId());
            task.setName(((TaskDto) entityDto).getName());
            task.setDescription(((TaskDto) entityDto).getDescription());
            task.setStatus(((TaskDto) entityDto).getStatus());
            task.setDateStart(((TaskDto) entityDto).getDateStart());
            task.setDateEnd(((TaskDto) entityDto).getDateEnd());
            try {
                final User user = userService.findOne(((TaskDto) entityDto).getUserId());
                task.setUser(user);
                task.setProject(projectService.findOne(user.getId(), ((TaskDto) entityDto).getProjectId()));
            } catch (UserNotFoundException e) {
                e.printStackTrace();
                return null;
            }
            return (S) task;
        }
        return null;
    }

    public List<T> convertToListEntitiesDto(@NotNull final List<S> entities) {
        final List<T> entitiesDto = new ArrayList<>();
        for(S s : entities) {
            entitiesDto.add(convertToEntityDto(s));
        }
        return entitiesDto;
    }
}