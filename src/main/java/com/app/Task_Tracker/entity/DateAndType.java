package com.app.Task_Tracker.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DateAndType {
    private DateFilter dateFilter;
    private Type type;
}
