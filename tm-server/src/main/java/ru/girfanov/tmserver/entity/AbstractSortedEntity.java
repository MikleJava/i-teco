package ru.girfanov.tmserver.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class AbstractSortedEntity extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 3974430357120257949L;

    @Setter
    @NonNull
    private String name;

    @Setter
    @NonNull
    private String description;

    @Setter
    @NonNull
    private String userId;

    @Setter //temporary
    @NonNull
    private String status;

    @Setter
    @NonNull
    private Date dateStart;

    @Setter
    @NonNull
    private Date dateEnd;

//    public void setStatus(@NotNull final String status) {
//        if(status.isEmpty()) { this.status = Status.PLANNING; }
//        if("В процессе".equals(status)) { this.status = Status.PROCESS; }
//        if("Готово".equals(status)) { this.status = Status.READY; }
//    }
}
