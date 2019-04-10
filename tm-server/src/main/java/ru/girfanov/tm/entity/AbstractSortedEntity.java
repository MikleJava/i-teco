package ru.girfanov.tm.entity;

import lombok.*;
import ru.girfanov.tm.enumeration.Status;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class AbstractSortedEntity extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 3974430357120257949L;

    @NonNull private String name;

    @NonNull private String description;

    @NonNull private Status status;

    @NonNull private Date dateStart;

    @NonNull private Date dateEnd;

    @NonNull private String userId;

}
