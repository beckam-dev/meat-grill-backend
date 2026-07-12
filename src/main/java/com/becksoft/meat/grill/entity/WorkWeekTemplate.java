package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "work_week_config")
public class WorkWeekTemplate {

    @Id
    private Long id = 1L; // Forzamos a que siempre sea el registro único de configuración

    @Enumerated(EnumType.STRING)
    @Column(name = "start_day", nullable = false)
    private DayOfWeek startDay = DayOfWeek.MONDAY; // Por defecto Lunes

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime = LocalTime.of(6, 0); // Por defecto 6:00 AM

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime = LocalTime.of(2, 0); // Por defecto 2:00 AM (del siguiente lunes)

    public WorkWeekTemplate(DayOfWeek startDay, LocalTime startTime, LocalTime endTime) {
        updateConfig(startDay, startTime, endTime);
    }

    public void updateConfig(DayOfWeek startDay, LocalTime startTime, LocalTime endTime) {
        if (startDay == null || startTime == null || endTime == null) {
            throw new IllegalArgumentException("Configuration parameters cannot be null.");
        }
        this.startDay = startDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}