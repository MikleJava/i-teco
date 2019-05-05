package ru.girfanov.tm.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Cacheable
@NoArgsConstructor
@Table(name = "app_project", schema = "tm")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Project extends AbstractSortedEntity {

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;

}
