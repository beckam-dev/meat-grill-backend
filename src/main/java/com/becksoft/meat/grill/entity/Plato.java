package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "platos")
public class Plato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plato")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal unitPrice;

    @ManyToMany
    @JoinTable(
            name = "platos_guarniciones",
            joinColumns = @JoinColumn(name = "id_plato"),
            inverseJoinColumns = @JoinColumn(name = "id_guarnicion")
    )
    private List<Guarnicion> guarniciones = new ArrayList<>();

    public Plato(String name, BigDecimal unitPrice) {
        rename(name);
        changePrice(unitPrice);
    }

    public void rename(String name) {
        if(name==null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be null or blank");
        }
        this.name = name;
    }

    public void changePrice(BigDecimal unitPrice) {
        if(unitPrice==null) {
            throw new IllegalArgumentException("unitPrice cannot be null");
        }
        if(unitPrice.compareTo(BigDecimal.ZERO)<=0) {
            throw new IllegalArgumentException("unitPrice cannot be less than zero");
        }
        this.unitPrice = unitPrice;
    }

    public void addGuarnicion(Guarnicion guarnicion) {
        if(guarnicion==null) {
            throw new IllegalArgumentException("guarnicion cannot be null");
        }
        this.guarniciones.add(guarnicion);
    }

    public void removeGuarnicion(Guarnicion guarnicion) {
        if(guarnicion==null) {
            throw new IllegalArgumentException("guarnicion cannot be null");
        }
        this.guarniciones.remove(guarnicion);
    }

}
