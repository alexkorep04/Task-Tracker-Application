package com.app.Task_Tracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name = "username", unique = true)
    @Size(min = 3, message = "Length of username should me more than 2 symbols")
    private String username;
    @Column(name = "password")
    @Size(min = 5, message = "Length of password should me more than 4 symbols")
    private String password;
    @Column(name = "email")
    @Email
    private String email;
    @Column(name = "enable")
    private boolean enabled;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompletedTask> completedTasks = new ArrayList<>();
}
