package ru.girfanov.tm.rest;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.girfanov.tm.api.service.IProjectService;
import ru.girfanov.tm.api.service.ITaskService;
import ru.girfanov.tm.api.service.IUserService;
import ru.girfanov.tm.dto.TaskDto;
import ru.girfanov.tm.entity.Task;
import ru.girfanov.tm.exception.UserNotFoundException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskControllerRest {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/show/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TaskDto> show(@PathVariable final String id, final Principal principal) {
        final String userId = userService.findOneByLogin(principal.getName()).getId();
        if(id == null || userId==null || id.isEmpty() || userId.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        TaskDto taskDto;
        try {
            taskDto = castToTaskDto(taskService.findOne(userId, id));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<TaskDto>> list(final Principal principal) {
        final String userId = userService.findOneByLogin(principal.getName()).getId();
        if(userId==null || userId.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        List<TaskDto> tasksDto;
        try {
            tasksDto = castToListTasksDto(taskService.findAllByUserId(userId));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasksDto, HttpStatus.OK);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TaskDto> create(@RequestBody final TaskDto taskDto, final Principal principal) {
        final String userId = userService.findOneByLogin(principal.getName()).getId();
        if(taskDto == null || userId == null || userId.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            taskService.persist(userId, castToTask(taskDto));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TaskDto> update(@PathVariable final String id, final Principal principal) {
        final String userId = userService.findOneByLogin(principal.getName()).getId();
        if(id == null || userId==null || id.isEmpty() || userId.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        TaskDto taskDto;
        try {
            taskDto = castToTaskDto(taskService.findOne(userId, id));
            taskService.merge(userId, castToTask(taskDto));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(taskDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TaskDto> delete(@PathVariable final String id, final Principal principal) {
        final String userId = userService.findOneByLogin(principal.getName()).getId();
        if(id == null || userId==null || id.isEmpty() || userId.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        TaskDto taskDto;
        try {
            taskDto = castToTaskDto(taskService.findOne(userId, id));
            taskService.remove(userId, castToTask(taskDto));
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

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
            task.setProject(projectService.findOne(taskDto.getUserId(), taskDto.getProjectId()));
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
