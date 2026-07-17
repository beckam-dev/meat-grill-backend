package com.becksoft.meat.grill.repository;

import com.becksoft.meat.grill.entity.Movement;
import com.becksoft.meat.grill.enums.MovementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
    // Metodo para encontrar todos los movimientos de la caja de un día en específico
    List<Movement> findByCashSessionId(Long sessionId);
    // Consulta SQL optimizada para sumar ingresos o egresos directamente de la BD
    @Query("SELECT COALESCE(SUM(m.amount), 0) FROM Movement m WHERE m.cashSession.id = :sessionId AND m.type = :type")
    BigDecimal sumAmountBySessionAndType(@Param("sessionId") Long sessionId, @Param("type") MovementType type);

}
