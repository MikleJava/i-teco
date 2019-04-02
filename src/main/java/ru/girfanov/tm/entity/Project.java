package ru.girfanov.tm.entity;

import lombok.*;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.entity.enumeration.Status;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
public class Project extends AbstractSortedEntity implements Serializable {

    private static final long serialVersionUID = 6794340542978134826L;

    public Project(@NonNull final String name, @Nullable final String description, @NonNull final String userId, @NonNull final Status status, @Nullable final Date dateStart, @Nullable final Date dateEnd) {
        super(name, description, userId, status, dateStart, dateEnd);
    }
}
