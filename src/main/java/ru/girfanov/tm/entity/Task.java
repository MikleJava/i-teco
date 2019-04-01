package ru.girfanov.tm.entity;

import lombok.*;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tm.entity.enumeration.Status;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public final class Task extends AbstractSortedEntity {

    @NonNull private String projectId;

    public Task(@NonNull final String name, @Nullable final String description, @NonNull final String userId, @NonNull final Status status, @Nullable final Date dateStart, @Nullable final Date dateEnd, @NonNull final String projectId) {
        super(name, description, userId, status, dateStart, dateEnd);
        this.projectId = projectId;
    }
}
