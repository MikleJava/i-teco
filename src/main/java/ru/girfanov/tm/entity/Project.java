package ru.girfanov.tm.entity;

import java.util.Date;

public class Project {

    private String uuid;
    private String name;
    private String description;
    private Date dateStart;
    private Date dateEnd;

    public Project() {
    }

    public Project(String uuid, String name, String description, Date dateStart, Date dateEnd) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
