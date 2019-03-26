package ru.girfanov.tm.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;
import java.util.Objects;

public final class Task extends AbstractEntity {

    @NotNull
    private String name;
    @Nullable
    private String description;
    @NotNull
    private String projectId;
    @NotNull
    private String userId;
    @Nullable
    private Date dateStart;
    @Nullable
    private Date dateEnd;

    public Task() {
    }

    public Task(@NotNull final String name, @Nullable final String description, @NotNull final String projectId, @NotNull final String userId, @Nullable final Date dateStart, @Nullable final Date dateEnd) {
        this.name = name;
        this.description = description;
        this.projectId = projectId;
        this.userId = userId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable final String description) {
        this.description = description;
    }

    @NotNull
    public String getProjectId() {
        return projectId;
    }

    public Task setName(@NotNull final String name) {
        this.name = name;
        return this;
    }

    public void setProjectId(@NotNull final String projectId) {
        this.projectId = projectId;
    }

    @NotNull
    public String getUserId() {
        return userId;
    }

    public void setUserId(@NotNull final String userId) {
        this.userId = userId;
    }

    @Nullable
    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(@Nullable final Date dateStart) {
        this.dateStart = dateStart;
    }

    @Nullable
    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(@Nullable final Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public boolean equals(@Nullable final Object o) {
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
