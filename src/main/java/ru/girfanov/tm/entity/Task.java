package ru.girfanov.tm.entity;

import java.util.Date;

public class Task extends AbstractEntity {

    private String name;
    private String description;
    private String projectId;
    private Date dateStart;
    private Date dateEnd;

    public Task() {
    }

    public Task(String name, String description, String projectId, Date dateStart, Date dateEnd) {
        this.name = name;
        this.description = description;
        this.projectId = projectId;
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

    public void setName(String name) {
        this.name = name;
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
}
