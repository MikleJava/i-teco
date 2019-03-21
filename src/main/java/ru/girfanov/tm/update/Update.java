package ru.girfanov.tm.update;

import ru.girfanov.tm.data.Project;
import ru.girfanov.tm.data.Task;

import java.util.List;

public class Update {
    public void updateProject(List<Project> projects, int id, String name) {
        for(Project project : projects) {
            if(project.getId() == id) {
                project.setName(name);
            }
        }
    }
    public void updateTask(List<Task> tasks, int id, String name) {
        for(Task task : tasks) {
            if(task.getId() == id) {
                task.setName(name);
            }
        }
    }
}
