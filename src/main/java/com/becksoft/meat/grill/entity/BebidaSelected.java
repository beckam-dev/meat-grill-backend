package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "bebidas_selected")
public class BebidaSelected {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bebida_selected")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bebida_option")
    private BebidaOption bebidaOption;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private BebidaStatus status;
    private BigDecimal subtotal;

    public BebidaSelected(Order order, BebidaOption bebidaOption, int quantity, BigDecimal subtotal) {
        assingOrder(order);
        assingBebidaOption(bebidaOption);
        assingQuantity(quantity);
        assingPrice(subtotal);
    }

    private void assingOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        this.order = order;
    }

    private void assingBebidaOption(BebidaOption bebidaOption) {
        if (bebidaOption == null) {
            throw new IllegalArgumentException("BebidaOption cannot be null");
        }
        this.bebidaOption = bebidaOption;
    }

    private void assingQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        this.quantity = quantity;
    }

    private void assingPrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        this.subtotal = price.multiply(BigDecimal.valueOf(quantity));
    }

}
