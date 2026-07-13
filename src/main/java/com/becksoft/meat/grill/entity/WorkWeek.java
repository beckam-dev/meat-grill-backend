package com.becksoft.meat.grill.entity;

import com.becksoft.meat.grill.enums.WorkWeekStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "work_weeks")
public class WorkWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_week")
    private Long id;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private WorkWeekStatus status = WorkWeekStatus.ABIERTA;

    // Recibe las fechas ya procesadas y validadas por el Service
    public WorkWeek(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Dates cannot be null.");
        }
        if (!endDate.isAfter(startDate)) {
            throw new IllegalArgumentException("End date must be after start date.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void cerrarSemana() {
        if (this.status == WorkWeekStatus.CERRADA) {
            throw new IllegalStateException("This work week is already closed.");
        }
        this.status = WorkWeekStatus.CERRADA;
    }
}