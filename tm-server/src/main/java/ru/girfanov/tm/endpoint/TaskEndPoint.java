package ru.girfanov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.api.service.ISessionService;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.dto.SessionDto;
import ru.girfanov.tm.dto.TaskDto;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.exception.UserNotFoundException;
import ru.girfanov.tm.exception.WrongSessionException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@Component
@WebService
@NoArgsConstructor
public class TaskEndPoint {

    @Autowired
    private ITaskService taskService;
    @Autowired
    private IProjectService projectService;
    @Autowired
    private ISessionService sessionService;
    @Autowired
    private IUserService userService;

    @WebMethod
    public void persistTask(@WebParam(name = "session") final SessionDto sessionDto, @WebParam(name = "task") final TaskDto taskDto) {
        try {
            if(sessionService.existSession(sessionDto)) taskService.persist(userService.findOne(sessionDto.getUserId()), castToTask(taskDto));
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @WebMethod
    public void mergeTask(@WebParam(name = "session") final SessionDto sessionDto, @WebParam(name = "task") final TaskDto taskDto) {
        try {
            if(sessionService.existSession(sessionDto)) taskService.merge(userService.findOne(sessionDto.getUserId()), castToTask(taskDto));
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @WebMethod
    public void removeTask(@WebParam(name = "session") final SessionDto sessionDto, @WebParam(name = "task") final TaskDto taskDto) {
        try {
            if(sessionService.existSession(sessionDto)) taskService.remove(userService.findOne(sessionDto.getUserId()), castToTask(taskDto));
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @WebMethod
    public void removeAllTasks(@WebParam(name = "session") final SessionDto sessionDto) {
        try {
            if(sessionService.existSession(sessionDto)) taskService.removeAllByUserId(userService.findOne(sessionDto.getUserId()));
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Nullable
    @WebMethod
    public TaskDto findOneTask(@WebParam(name = "session") final SessionDto sessionDto, @WebParam(name = "taskUuid") final String taskUuid) {
        try {
            if(sessionService.existSession(sessionDto)) return castToTaskDto(taskService.findOne(userService.findOne(sessionDto.getUserId()), taskUuid));
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Nullable
    @WebMethod
    public List<TaskDto> findAllTasks(@WebParam(name = "session") final SessionDto sessionDto) {
        try {
            if(sessionService.existSession(sessionDto)) return castToListTasksDto(taskService.findAllByUserId(userService.findOne(sessionDto.getUserId())));
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Nullable
    @WebMethod
    public List<TaskDto> findAllTasksByProjectId(@WebParam(name = "session") final SessionDto sessionDto, @WebParam(name = "projectId") final String projectId) {
        try {
            if(sessionService.existSession(sessionDto)) return castToListTasksDto(taskService.findAllTasksByProjectId(userService.findOne(sessionDto.getUserId()), projectId));
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @WebMethod
    public void removeAllTasksByProjectId(@WebParam(name = "session") final SessionDto sessionDto, @WebParam(name = "projectId") final String projectId) {
        try {
            if(sessionService.existSession(sessionDto)) taskService.removeAllTasksByProjectId(userService.findOne(sessionDto.getUserId()), projectId);
        } catch (WrongSessionException | UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

//    @Nullable
//    @WebMethod
//    public List<TaskDto> findAllTasksSortedByValue(@WebParam(name = "session") final SessionDto sessionDto, @WebParam(name = "value") final String value) {
//        try {
//            if(sessionService.existSession(sessionDto)) return castToListTasksDto(taskService.findAllSortedByValue(userService.findOne(sessionDto.getUserId()), value));
//        } catch (WrongSessionException | UserNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }

    private TaskDto castToTaskDto(@NotNull final Task task) {
        final TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatus(task.getStatus());
        taskDto.setDateStart(task.getDateStart());
        taskDto.setDateEnd(task.getDateEnd());
        taskDto.setProjectId(task.getProject().getId());
        taskDto.setUserId(task.getUser().getId());
        return taskDto;
    }

    private Task castToTask(@NotNull final TaskDto taskDto) {
        final Task task = new Task();
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setDateStart(taskDto.getDateStart());
        task.setDateEnd(taskDto.getDateEnd());
        try {
            task.setUser(userService.findOne(taskDto.getUserId()));
            task.setProject(projectService.findOne(userService.findOne(taskDto.getUserId()), taskDto.getProjectId()));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return task;
    }

    private List<TaskDto> castToListTasksDto(@NotNull final List<Task> tasks) {
        final List<TaskDto> tasksDto = new ArrayList<>();
        for (Task task : tasks) {
            tasksDto.add(castToTaskDto(task));
        }
        return tasksDto;
    }
}
