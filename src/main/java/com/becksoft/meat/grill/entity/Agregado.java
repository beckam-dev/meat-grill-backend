package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "agregados")
public class Agregado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agregado")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_insumo")
    private Insumo insumo;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    public Agregado(Insumo insumo, BigDecimal unitPrice) {
        assingInsumo(insumo);
        changeUnitPrice(unitPrice);
    }

    private void assingInsumo(Insumo insumo){
        this.insumo = insumo;
    }

    private void changeUnitPrice(BigDecimal unitPrice){
        this.unitPrice = unitPrice;
    }

}
