package com.becksoft.meat.grill.entity;

import com.becksoft.meat.grill.enums.PlatoStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    private PlatoStatus status = PlatoStatus.PENDIENTE;
    @Column(name = "subtotal", nullable = false)
    private BigDecimal subtotal;

    @JsonManagedReference
    @OneToMany(mappedBy = "platoSelected", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GuarnicionSelected> guarniciones = new ArrayList<>();

    public void addGuarnicion(GuarnicionSelected guarnicion) {
        if (guarnicion == null) {
            throw new IllegalArgumentException("Guarnicion cannot be null.");
        }
        this.guarniciones.add(guarnicion);
    }

    public void removeGuarnicion(GuarnicionSelected guarnicion) {
        if (guarnicion == null) {
            throw new IllegalArgumentException("Guarnicion cannot be null.");
        }
        this.guarniciones.remove(guarnicion);
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "platoSelected", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AgregadoSelected> agregados = new ArrayList<>();

    public PlatoSelected(Order order, Plato plato) {
        assignOrder(order);
        assignPlato(plato);
    }

    private void assignOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        this.order = order;
    }

    private void assignPlato(Plato plato) {
        if (plato == null) {
            throw new IllegalArgumentException("Plato cannot be null.");
        }
        this.plato = plato;
        this.subtotal = this.plato.getUnitPrice();
    }

    public void addAgregado(AgregadoSelected agregado) {
        if (agregado == null) {
            throw new IllegalArgumentException("Agregado cannot be null.");
        }
        this.agregados.add(agregado);
        this.subtotal = this.subtotal.add(agregado.getPrice());
    }

    public void removeAgregado(AgregadoSelected agregado) {
        if (agregado == null) {
            throw new IllegalArgumentException("Agregado cannot be null.");
        }
        if (this.agregados.remove(agregado)) {
            this.subtotal = this.subtotal.subtract(agregado.getPrice());
        }
    }

    public void changeStatus(PlatoStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null.");
        }
        this.status = status;
    }

}
