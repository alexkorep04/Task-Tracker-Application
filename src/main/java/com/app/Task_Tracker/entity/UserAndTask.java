package com.app.Task_Tracker.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserAndTask {
    private int id;
    private String username;
    private int amountOfCompletedTasks;
    private int amountOfActiveTasks;
}
