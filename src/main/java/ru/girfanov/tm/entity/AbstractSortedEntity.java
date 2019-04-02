package ru.girfanov.tm.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.entity.enumeration.Status;

import java.io.Serializable;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AbstractSortedEntity extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 3974430357120257949L;
    @Setter
    @NonNull
    private String name;

    @Setter
    private String description;

    @Setter
    @NonNull
    private String userId;

    @NonNull
    private Status status;

    @Setter
    private Date dateStart;

    @Setter
    private Date dateEnd;

    public void setStatus(@NotNull final String status) {
        if(status.isEmpty()) { this.status = Status.PLANNING; }
        if("В процессе".equals(status)) { this.status = Status.PROCESS; }
        if("Готово".equals(status)) { this.status = Status.READY; }
    }
}
