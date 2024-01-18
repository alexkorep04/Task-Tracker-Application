package com.app.Task_Tracker.entity;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DateFilter {
    private LocalDate from;
    private LocalDate to;
}
