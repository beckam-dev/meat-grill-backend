package com.becksoft.meat.grill.repository;

import com.becksoft.meat.grill.entity.CashSession;
import com.becksoft.meat.grill.entity.WorkWeek;
import com.becksoft.meat.grill.enums.CashSessionStatus;
import com.becksoft.meat.grill.enums.MovementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface CashSessionRepository extends JpaRepository<CashSession, Long> {
    // Método para encontrar las sesiones según su estado, nuevamente, solo debería devolver una si en caso se ingresará OPEN
    Optional<CashSession> findByStatus(CashSessionStatus status);
    // Método para listar todas las sesiones(días) de una semana en específica
    Optional<CashSession> findByWeek(WorkWeek week);

}
