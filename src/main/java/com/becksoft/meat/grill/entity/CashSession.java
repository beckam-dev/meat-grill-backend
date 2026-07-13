package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "cash_sessions")
public class CashSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_session")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_week", nullable = false)
    private WorkWeek week;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee employee;
    @Column(name = "work_date", nullable = false)
    private DayOfWeek workDate;
    @Column(name = "opened_at", nullable = false)
    private  LocalDateTime openedAt = LocalDateTime.now();
    @Column(name = "closedAt", nullable = true)
    private LocalDateTime closedAt;
    @Column(name = "opening_cash")
    private BigDecimal openingCash;
    @Column(name = "closing_cash")
    private BigDecimal closingCash;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CashSessionStatus status = CashSessionStatus.OPEN;

    public CashSession(WorkWeek week, Employee employee) {
        assingWeek(week);
        assingEmployee(employee);
        assingDate();
    }

    private void assingWeek(WorkWeek week) {
        if (week == null) {
            throw new IllegalArgumentException("week cannot be null");
        }
        this.week = week;
    }

    private void assingEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("employee cannot be null");
        }
        this.employee = employee;
    }

    private void assingDate() {
        workDate = LocalDateTime.now().getDayOfWeek();
    }

    private void cerrarCaja() {
        closedAt = LocalDateTime.now();
        status = CashSessionStatus.CLOSED;
    }

}
