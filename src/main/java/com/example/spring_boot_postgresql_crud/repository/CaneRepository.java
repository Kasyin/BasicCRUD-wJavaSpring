package com.example.spring_boot_postgresql_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.spring_boot_postgresql_crud.model.Cane;

public interface CaneRepository extends JpaRepository<Cane, Long> {}
