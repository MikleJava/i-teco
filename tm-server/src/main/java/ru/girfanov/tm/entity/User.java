package ru.girfanov.tm.entity;

import lombok.*;
import ru.girfanov.tm.enumeration.Role;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_user", schema = "tm")
public class User extends AbstractEntity {

    @Column(unique = true)
    private String login;

    @Column(name = "password_hash")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
    private List<Session> sessions;

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
    private List<Task> tasks;

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
    private List<Project> projects;
}
