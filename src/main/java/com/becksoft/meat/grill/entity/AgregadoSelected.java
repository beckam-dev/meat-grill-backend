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
    @JoinColumn(name = "id_agregado")
    private Agregado agregado;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private BigDecimal price;

    public AgregadoSelected(PlatoSelected platoSelected, Agregado agregado, int quantity) {
        assingPlatoSelected(platoSelected);
        assingQuantity(quantity);
        assingAgregado(agregado);
        sendAgregado();
    }

    private void assingPlatoSelected(PlatoSelected platoSelected) {
        if (platoSelected == null) {
            throw new IllegalArgumentException("platoSelected cannot be null");
        }
        this.platoSelected = platoSelected;
    }

    private void assingAgregado(Agregado agregado) {
        if (agregado == null) {
            throw new IllegalArgumentException("agregado cannot be null");
        }
        this.agregado = agregado;
        calcularPrecio();
    }

    private void assingQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    private void calcularPrecio() {
        this.price = this.agregado.getUnitPrice().multiply(new BigDecimal(this.quantity));
    }

    private void sendAgregado() {
        this.platoSelected.addAgregado(this);
    }

}
