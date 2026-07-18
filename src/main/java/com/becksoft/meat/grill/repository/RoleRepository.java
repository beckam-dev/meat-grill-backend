package com.becksoft.meat.grill.repository;

import com.becksoft.meat.grill.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {}
