package com.becksoft.meat.grill.repository;

import com.becksoft.meat.grill.entity.Payment;
import com.becksoft.meat.grill.entity.WorkWeek;
import com.becksoft.meat.grill.enums.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Metodo para encontrar todos los pagos de un día en específico
    List<Payment> findBySessionId(Long sessionId);
    // Metodo para encontrar todos los pagos de una semana en específica sin necesidad de específicar el tipo de pago
    List<Payment> findBySessionWeek(WorkWeek workWeek);
    // Metodo para traer todos los pagos filtrados por el metodo de pago (YAPE, PLIN, EFECTIVO)
    List<Payment> findBySessionWeekAndPaymentMethod(WorkWeek week, PaymentMethod paymentMethod);
    // Consulta optimizada para sumar directamente los montos en la BD (Evita traer toda la lista a memoria)
    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.session.id = :sessionId AND p.paymentMethod = :method")
    BigDecimal sumAmountBySessionAndMethod(@Param("sessionId") Long sessionId, @Param("method") PaymentMethod method);

}
