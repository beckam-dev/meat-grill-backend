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
    private BebidaStatus status = BebidaStatus.PENDIENTE;
    private BigDecimal subtotal;

    public BebidaSelected(Order order, BebidaOption bebidaOption, int quantity) {
        assignOrder(order);
        assignBebidaOption(bebidaOption);
        assignQuantity(quantity);
        calcularSubtotal();
    }

    private void assignOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        this.order = order;
    }

    private void assignBebidaOption(BebidaOption bebidaOption) {
        if (bebidaOption == null) {
            throw new IllegalArgumentException("BebidaOption cannot be null");
        }
        this.bebidaOption = bebidaOption;
    }

    private void assignQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        this.quantity = quantity;
    }

    private void calcularSubtotal() {
        BigDecimal unitPrice = this.bebidaOption.getPrice();
        this.subtotal = unitPrice.multiply(BigDecimal.valueOf(this.quantity));
    }

    public void aumentar() {
        this.subtotal = this.subtotal.add(this.bebidaOption.getPrice());
        quantity ++;
    }

    public void restar()  {
        if(quantity <= 1) {
            throw new IllegalArgumentException("Quantity must be greater than 1");
        }
        this.subtotal = this.subtotal.subtract(this.bebidaOption.getPrice());
        quantity --;
    }

}
