package com.becksoft.meat.grill.entity;

import com.becksoft.meat.grill.enums.MovementType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "movements")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movement")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_session")
    private CashSession session;

    @Enumerated(EnumType.STRING)
    @Column(name = "id_type")
    private MovementType type;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Movement(CashSession session, MovementType type, String description, BigDecimal amount) {
        assignCashSession(session);
        assignAmount(amount);
        assignDescription(description);
        assignType(type);
    }

    private void assignCashSession(CashSession cashSession) {
        if (cashSession == null) throw new IllegalArgumentException("CashSession cannot be null.");
        this.session = cashSession;
    }

    private void assignAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
        this.amount = amount;
    }

    private void assignDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        this.description = description;
    }

    private void assignType(MovementType type) {
        if (type == null) throw new IllegalArgumentException("Movement type cannot be null.");
        this.type = type;
    }

}
