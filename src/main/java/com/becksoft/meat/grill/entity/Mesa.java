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
    @Column(name = "numero_mesa", nullable = false, unique = true)
    private int numero;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MesaStatus status = MesaStatus.LIBRE;

    public Mesa(int numero) {
        assignNumero(numero);
    }

    private void assignNumero(int numero) {
        if(numero <= 0){
            throw new IllegalArgumentException("number must be greater than zero");
        }
        this.numero = numero;
    }

    private void changeStatus(MesaStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("status cannot be null");
        }
        this.status = status;
    }

    public void ocupar() {
        changeStatus(MesaStatus.OCUPADA);
    }

    public void desocupar() {
        changeStatus(MesaStatus.LIBRE);
    }

    public void quitar() {
        changeStatus(MesaStatus.FUERA_DE_SERVICIO);
    }

    public void reservar() {
        changeStatus(MesaStatus.RESERVADA);
    }

    public boolean isOcupada() {
        return status == MesaStatus.OCUPADA;
    }

}
