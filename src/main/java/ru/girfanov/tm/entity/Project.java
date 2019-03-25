package ru.girfanov.tm.entity;

import java.util.Date;
import java.util.Objects;

public class Project extends AbstractEntity {

    private String name;
    private String description;
    private String userId;
    private Date dateStart;
    private Date dateEnd;

    public Project() {
    }

    public Project(String name, String description, String userId, Date dateStart, Date dateEnd) {
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getName() {
        return name;
    }

    public Project setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Project project = (Project) o;
        return Objects.equals(getUuid(), project.getUuid()) && Objects.equals(userId, project.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), userId);
    }
}
