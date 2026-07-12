package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "bebidas_sizes")
public class BebidaSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bebida_size", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    public BebidaSize(String name){
        assignSize(name);
    }

    private void assignSize(String size) {
        if (size == null) {
            throw new IllegalArgumentException("Size cannot be null.");
        }
        this.name = size;
    }

}
