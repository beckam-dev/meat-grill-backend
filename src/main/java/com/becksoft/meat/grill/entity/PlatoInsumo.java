package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "platos_insumos")
public class PlatoInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plato_insumo")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plato", nullable = false)
    private Plato plato;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_insumo", nullable = false)
    private Insumo insumo;
    @Column(nullable = false)
    private Integer quantity;

    public PlatoInsumo(Plato plato, Insumo insumo, int quantity) {
        assignPlato(plato);
        assignInsumo(insumo);
        changeQuantity(quantity);
    }

    private void assignPlato(Plato plato){
        if(plato == null){
            throw new IllegalArgumentException("Plato cannot be null.");
        }
        this.plato = plato;
    }

    private void assignInsumo(Insumo insumo) {
        if (insumo == null){
            throw new IllegalArgumentException("Insumo cannot be null.");
        }
        this.insumo = insumo;
    }

    public void changeQuantity(Integer quantity) {
        if (quantity <= 0){
            throw new IllegalArgumentException("Quantity cannot be zero or less than zero.");
        }
        this.quantity = quantity;
    }

}
