package ru.girfanov.tm.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_task", schema = "tm")
public class Task extends AbstractSortedEntity {

    @ManyToOne
    @JoinColumn(name = "id")
    @Column(name = "project_id")
    private Project projectId;
}
