package com.example.pruebatecnica.services;

import com.example.pruebatecnica.models.dtos.EmployeDTO;
import com.example.pruebatecnica.models.dtos.UpdateEmployeDTO;
import com.example.pruebatecnica.models.entities.Employe;

import java.util.List;

public interface EmployeService {
    Employe createEmploye(EmployeDTO info) throws Exception;
    Employe updateEmploye(UpdateEmployeDTO info) throws Exception;
    Employe getEmployeByCode(String code);
    void deleteEmployeByCode(String code) throws Exception;
    List<Employe> getAllEmployes();

}
