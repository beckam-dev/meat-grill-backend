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

    public CashSession(WorkWeek week, Employee employee, BigDecimal openingCash) {
        assignWeek(week);
        assignEmployee(employee);
        assignOpeningCash(openingCash);
        assignDate();
    }

    private void assignOpeningCash(BigDecimal openingCash) {
        if (openingCash == null || openingCash.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Opening cash cannot be null or negative.");
        }
        this.openingCash = openingCash;
    }

    private void assignWeek(WorkWeek week) {
        if (week == null) {
            throw new IllegalArgumentException("week cannot be null");
        }
        this.week = week;
    }

    private void assignEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("employee cannot be null");
        }
        this.employee = employee;
    }

    private void assignDate() {
        workDate = LocalDateTime.now().getDayOfWeek();
    }

    public void cerrarCaja(BigDecimal closingCash) {
        if (this.status == CashSessionStatus.CLOSED) {
            throw new IllegalStateException("This cash session is already closed.");
        }
        if (closingCash == null || closingCash.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Closing cash cannot be null or negative.");
        }
        this.closingCash = closingCash;
        this.closedAt = LocalDateTime.now();
        this.status = CashSessionStatus.CLOSED;
    }

}
