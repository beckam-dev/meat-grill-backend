package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_session", nullable = false)
    private CashSession session;
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "paid_at", nullable = false)
    private LocalDateTime paidAt = LocalDateTime.now();

    public Payment(Order order, CashSession cashSession, PaymentMethod paymentMethod, BigDecimal amount) {
        assignOrder(order);
        assignCashSession(cashSession);
        assignPaymentMethod(paymentMethod);
        assignAmount(amount);
    }

    private void assignOrder(Order order) {
        if (order == null) throw new IllegalArgumentException("Order cannot be null.");
        this.order = order;
    }

    private void assignCashSession(CashSession cashSession) {
        if (cashSession == null) throw new IllegalArgumentException("CashSession cannot be null.");
        this.session = cashSession;
    }

    private void assignPaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == null) throw new IllegalArgumentException("Payment method cannot be null.");
        this.paymentMethod = paymentMethod;
    }

    private void assignAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
        this.amount = amount;
    }

}
