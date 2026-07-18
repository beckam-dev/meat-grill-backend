package com.becksoft.meat.grill.repository;

import com.becksoft.meat.grill.entity.Agregado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgregadoRepository extends JpaRepository<Agregado,Long> {}
