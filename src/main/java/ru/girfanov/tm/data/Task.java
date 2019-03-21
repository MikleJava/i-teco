package ru.girfanov.tm.data;

import java.util.Date;

public class Task {

    private int id;
    private String name;
    private int projectId;
    private Date dateStart;
    private Date dateEnd;

    public Task() {
    }

    public Task(int id, String name, int projectId, Date dateStart, Date dateEnd) {
        this.id = id;
        this.name = name;
        this.projectId = projectId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProjectId() {
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
