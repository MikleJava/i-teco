package ru.girfanov.tm.entity;

import java.util.Date;
import java.util.Objects;

public class Task extends AbstractEntity {

    private String name;
    private String description;
    private String projectId;
    private String userId;
    private Date dateStart;
    private Date dateEnd;

    public Task() {
    }

    public Task(String name, String description, String projectId, String userId, Date dateStart, Date dateEnd) {
        this.name = name;
        this.description = description;
        this.projectId = projectId;
        this.userId = userId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectId() {
        return projectId;
    }

    public Task setName(String name) {
        this.name = name;
        return this;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(getUuid(), task.getUuid()) && Objects.equals(projectId, task.projectId) &&
                Objects.equals(userId, task.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), projectId, userId);
    }
}
