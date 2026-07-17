package com.becksoft.meat.grill.repository;

import com.becksoft.meat.grill.entity.WorkWeek;
import com.becksoft.meat.grill.enums.WorkWeekStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkWeekRepository extends JpaRepository<WorkWeek, Long> {
    // Método que permite ver las semanas según su estado, técnicamente solo debería haber como máximo una siempre que se coloqué ABIERTA
    Optional<WorkWeek> findByStatus(WorkWeekStatus status);

}
