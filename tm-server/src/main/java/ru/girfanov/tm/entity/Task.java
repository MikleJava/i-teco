package ru.girfanov.tm.entity;

import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Cacheable
@NoArgsConstructor
@Table(name = "app_task", schema = "tm")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task extends AbstractSortedEntity {

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private Project project;
}
