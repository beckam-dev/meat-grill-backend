package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "insumos")
public class Insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insumo")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InsumoType type;

    public Insumo(String name, InsumoType type) {
        rename(name);
        changeType(type);
    }

    public void rename(String name){

        if(name == null){
            throw new IllegalArgumentException("Name cannot be null");
        }

        this.name = name;
    }

    public void changeType(InsumoType type){
        if(type == null){
            throw new IllegalArgumentException("Type cannot be null");
        }
        this.type = type;
    }

}
