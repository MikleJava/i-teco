package ru.girfanov.tm.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(of = {"projectId", "userId"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public final class Task extends AbstractEntity {

    @NonNull
    private String name;
    private String description;
    @NonNull
    private String projectId;
    @NonNull
    private String userId;
    private Date dateStart;
    private Date dateEnd;

}
