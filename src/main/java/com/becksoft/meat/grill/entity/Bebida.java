package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "bebidas")
public class Bebida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bebida")
    private Long id;
    @Column(name = "name")
    private String name;

    public Bebida(String name) {
        assingBebida(name);
    }

    private void assingBebida(String bebida) {
        if (bebida == null) {
            throw new IllegalArgumentException("Bebida cannot be null.");
        }
        this.name = bebida;
    }

}
