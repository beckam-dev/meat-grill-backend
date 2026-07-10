package com.becksoft.meat.grill.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "mesas")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mesa")
    private Long id;
    @Column(name = "numero_mesa")
    private int numero;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MesaStatus status;

}
