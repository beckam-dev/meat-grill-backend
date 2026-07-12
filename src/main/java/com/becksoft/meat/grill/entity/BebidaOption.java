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
    @Column(name = "unit_prize", nullable = false)
    private BigDecimal price;

    public BebidaOption(BebidaSize bebidaSize, BigDecimal price) {
        assingSize(bebidaSize);
        assingPrice(price);
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
