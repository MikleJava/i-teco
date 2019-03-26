package ru.girfanov.tm.entity;

import java.util.Date;
import java.util.Objects;

public final class Project extends AbstractEntity {

    private String name;
    private String description;
    private String userId;
    private Date dateStart;
    private Date dateEnd;

    public Project() {
    }

    public Project(final String name, final String description, final String userId, final Date dateStart, final Date dateEnd) {
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getName() {
        return name;
    }

    public Project setName(final String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(final Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(final Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public boolean equals(final Object o) {
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
