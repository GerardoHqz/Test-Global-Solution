package com.example.pruebatecnica.repositories;

import com.example.pruebatecnica.models.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.UUID;

public interface EmployeRepository extends JpaRepository<Employe, UUID>, QuerydslPredicateExecutor<Employe> {
    Employe findByCode(String code);
    void deleteByCode(String code);
}
