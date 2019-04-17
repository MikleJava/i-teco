package ru.girfanov.tm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import ru.girfanov.tm.enumeration.Status;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public final class TaskDto extends AbstractSortedEntityDto implements Serializable {

    private static final long serialVersionUID = -692039978108063466L;

    @NonNull private String projectId;

    public TaskDto(@NonNull final String name, @NonNull final String description, @NonNull final Status status, @NonNull final Date dateStart, @NonNull final Date dateEnd, @NonNull final String userId, @NonNull final String projectId) {
        super(name, description, status, dateStart, dateEnd, userId);
        this.projectId = projectId;
    }
}
