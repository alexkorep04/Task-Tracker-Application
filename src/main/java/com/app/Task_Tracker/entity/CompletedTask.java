package com.app.Task_Tracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "completed_tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CompletedTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Column(name = "deadline")
    private LocalDate deadline;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "completed_date")
    private LocalDate dateOfCompliance;
}
