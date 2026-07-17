package com.becksoft.meat.grill.repository;

import com.becksoft.meat.grill.entity.BebidaSelected;
import com.becksoft.meat.grill.entity.PlatoSelected;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BebidaSelectedRepository extends JpaRepository<PlatoSelected, Long> {

    // Encuentra las bebidas asociadas a un pedido
    List<BebidaSelected> findByOrderId(Long orderId);

}
