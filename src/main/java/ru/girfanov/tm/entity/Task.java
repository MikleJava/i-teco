package ru.girfanov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.girfanov.tm.enumeration.Status;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Task extends AbstractEntity {
    private String name;
    private String description;
    private Status status;
    private Date dateStart;
    private Date dateEnd;
    private String userId;
    private String projectId;
}
