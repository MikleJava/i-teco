package ru.girfanov.tm.entity;

import lombok.*;
import ru.girfanov.tm.enumeration.Status;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
public class Project extends AbstractSortedEntity implements Serializable {

    private static final long serialVersionUID = 6794340542978134826L;

    public Project(@NonNull final String name, @NonNull final String description, @NonNull final Status status, @NonNull final Date dateStart, @NonNull final Date dateEnd, @NonNull final String userId) {
        super(name, description, status, dateStart, dateEnd, userId);
    }
}
