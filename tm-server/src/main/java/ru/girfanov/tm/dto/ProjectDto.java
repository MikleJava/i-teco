package ru.girfanov.tm.dto;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import ru.girfanov.tm.enumeration.Status;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
public final class ProjectDto extends AbstractSortedEntityDto implements Serializable {

    private static final long serialVersionUID = 6794340542978134826L;

    public ProjectDto(@NonNull final String name, @NonNull final String description, @NonNull final Status status, @NonNull final Date dateStart, @NonNull final Date dateEnd, @NonNull final String userId) {
        super(name, description, status, dateStart, dateEnd, userId);
    }
}
