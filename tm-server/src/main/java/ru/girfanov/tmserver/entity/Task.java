package ru.girfanov.tmserver.entity;

import lombok.*;
import org.jetbrains.annotations.Nullable;
import ru.girfanov.tmserver.enumeration.Status;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Task extends AbstractSortedEntity implements Serializable {

    private static final long serialVersionUID = -692039978108063466L;

    @NonNull private String projectId;

    public Task(@NonNull final String name, @Nullable final String description, @NonNull final String userId, @NonNull final String status, @Nullable final Date dateStart, @Nullable final Date dateEnd, @NonNull final String projectId) {
        super(name, description, userId, status, dateStart, dateEnd);
        this.projectId = projectId;
    }
}
