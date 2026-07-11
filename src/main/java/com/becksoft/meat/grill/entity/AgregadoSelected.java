package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "agregados_selected")
public class AgregadoSelected {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agregado_selected")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plato_selected", nullable = false)
    private PlatoSelected platoSelected;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agregado", nullable = false)
    private Agregado agregado;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public AgregadoSelected(PlatoSelected platoSelected, Agregado agregado, int quantity) {
        assignPlatoSelected(platoSelected);
        assignAgregado(agregado);
        assignQuantity(quantity);
        calcularPrecio();

        this.platoSelected.addAgregado(this);
    }

    private void assignPlatoSelected(PlatoSelected platoSelected) {
        if (platoSelected == null) {
            throw new IllegalArgumentException("PlatoSelected cannot be null.");
        }
        this.platoSelected = platoSelected;
    }

    private void assignAgregado(Agregado agregado) {
        if (agregado == null) {
            throw new IllegalArgumentException("Agregado cannot be null.");
        }
        this.agregado = agregado;
    }

    private void assignQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        this.quantity = quantity;
    }

    private void calcularPrecio() {
        BigDecimal unitPrice = this.agregado.getUnitPrice();
        this.price = unitPrice.multiply(new BigDecimal(this.quantity));
    }

}
