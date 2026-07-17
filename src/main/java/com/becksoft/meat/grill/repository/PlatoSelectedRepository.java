package com.becksoft.meat.grill.repository;

import com.becksoft.meat.grill.entity.PlatoSelected;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatoSelectedRepository extends JpaRepository<PlatoSelected, Long> {
    // Encuentra los platos asociados a un pedido
    List<PlatoSelected> findByOrderId(Long orderId);
}
