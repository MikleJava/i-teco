package ru.girfanov.tm.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_session", schema = "tm")
public class Session extends AbstractEntity {

    private Date timestamp;

    private String signature;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

}
