package com.becksoft.meat.grill.repository;

import com.becksoft.meat.grill.entity.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatoRepository extends JpaRepository<Plato,Long> {}
