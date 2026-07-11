package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "platos_selected")
public class PlatoSelected {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plato_selected")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plato", nullable = false)
    private Plato plato;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PlatoStatus status = PlatoStatus.PENDENTE;
    @Column(name = "subtotal")
    private BigDecimal subtotal;

    public PlatoSelected(Order order, Plato plato) {
        assingOrder(order);
        assingPlato(plato);
    }

    private void assingOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        this.order = order;
    }
    private void assingPlato(Plato plato) {
        if (plato == null) {
            throw new IllegalArgumentException("Plato cannot be null");
        }
        this.plato = plato;
        this.subtotal = this.plato.getUnitPrice();
    }

    public void addAgregado(AgregadoSelected agregado) {
        if (agregado == null) {
            throw new IllegalArgumentException("Agregado cannot be null");
        }
        this.subtotal = this.subtotal.add(agregado.getPrice());
    }

    public void removeAgregado(AgregadoSelected agregado) {
        if (agregado == null) {
            throw new IllegalArgumentException("Agregado cannot be null");
        }
        this.subtotal = this.subtotal.subtract(agregado.getPrice());
    }

}
