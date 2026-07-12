package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "bebidas_options")
public class BebidaOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bebida_option")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bebida", nullable = false)
    private Bebida bebida;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bebida_size", nullable = false)
    private BebidaSize bebidaSize;
    @Column(name = "unit_price", nullable = false)
    private BigDecimal price;

    public BebidaOption(Bebida bebida, BebidaSize bebidaSize, BigDecimal price) {
        assingBebida(bebida);
        assingSize(bebidaSize);
        assingPrice(price);
    }

    private void assingBebida(Bebida bebida) {
        if (bebida == null) {
            throw new IllegalArgumentException("bebida cannot be null");
        }
        this.bebida = bebida;
    }

    private void assingSize(BebidaSize size) {
        if (size == null) {
            throw new IllegalArgumentException("size cannot be null");
        }
        this.bebidaSize = size;
    }

    private void assingPrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("price cannot be null");
        }
        this.price = price;
    }

}
