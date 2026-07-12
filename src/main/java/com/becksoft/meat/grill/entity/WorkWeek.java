package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

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

    // El constructor ahora es dinámico: requiere la plantilla de configuración del admin
    public WorkWeek(WorkWeekTemplate config) {
        if (config == null) {
            throw new IllegalArgumentException("System configuration template cannot be null.");
        }
        calcularRangoConConfiguracion(config);
    }

    private void calcularRangoConConfiguracion(WorkWeekTemplate config) {
        LocalDateTime ahora = LocalDateTime.now();

        // 1. Calculamos el inicio basándonos en el día y hora que eligió el administrador
        LocalDateTime inicioSemana = ahora.with(TemporalAdjusters.previousOrSame(config.getStartDay()))
                .with(config.getStartTime())
                .withSecond(0).withNano(0);

        // Control de QA: Si estamos en el día de inicio pero aún no se llega a la hora de apertura,
        // pertenecemos a la semana laboral anterior.
        if (ahora.isBefore(inicioSemana)) {
            inicioSemana = inicioSemana.minusWeeks(1);
        }

        this.startDate = inicioSemana;

        // 2. Calculamos el fin: Sumamos 1 semana al inicio y le aplicamos la hora de cierre del admin
        this.endDate = inicioSemana.plusWeeks(1).with(config.getEndTime());
    }

    public void cerrarSemana() {
        if (this.status == WorkWeekStatus.CERRADA) {
            throw new IllegalStateException("This work week is already closed.");
        }
        this.status = WorkWeekStatus.CERRADA;
    }
}