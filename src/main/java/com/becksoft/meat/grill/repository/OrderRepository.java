package com.becksoft.meat.grill.repository;

import com.becksoft.meat.grill.entity.Order;
import com.becksoft.meat.grill.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // para ver los pedidos según su estado (PENDING, PREPARING, etc.)
    List<Order> findByStatus(OrderStatus status);
    // para ver el historial de órdenes atendidas por un empleado en específico.
    List<Order> findByEmployeeId(Long employeeId);

}
