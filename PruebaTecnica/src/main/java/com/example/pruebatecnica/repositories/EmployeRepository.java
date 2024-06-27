package com.example.pruebatecnica.repositories;

import com.example.pruebatecnica.models.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeRepository extends JpaRepository<Employe, UUID> {
    Employe findByCode(String code);
    void deleteByCode(String code);
}
