package ru.girfanov.tm.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(of = {"userId"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public final class Project extends AbstractEntity {

    @NonNull
    private String name;
    private String description;
    @NonNull
    private String userId;
    private Date dateStart;
    private Date dateEnd;

}
