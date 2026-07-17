package com.becksoft.meat.grill.repository;

import com.becksoft.meat.grill.entity.Order;
import com.becksoft.meat.grill.enums.MesaStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MesaRepository extends JpaRepository<Order, Long> {

    // para ver que mesas se encuentran libres (LIBRE, OCUPADA, RESERVADA, FUERA_DE_SERVICIO)
    List<Order> findByStatus(MesaStatus status);
    // para buscar una mesa específica por su número
    Optional<Order> findByNumeroMesa(int numeroMesa);

}
