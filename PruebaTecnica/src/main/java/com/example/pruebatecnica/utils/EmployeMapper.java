package com.example.pruebatecnica.utils;

import com.example.pruebatecnica.models.dtos.EmployeDTO;
import com.example.pruebatecnica.models.entities.Employe;

import java.util.Random;

public class EmployeMapper {
    public static Employe toEntity(EmployeDTO dto) {
        Employe employe = new Employe();
        employe.setName(dto.getName());
        employe.setLastName(dto.getLastName());
        employe.setAddress(dto.getAddress());
        employe.setSalary(dto.getSalary());
        employe.setDepartment(dto.getDepartment());
        employe.setCode(generateEmployeeCode());

        return employe;
    }

    private static String generateEmployeeCode() {
        Random random = new Random();
        int code = random.nextInt(9000) + 1000; // Genera un número entre 1000 y 9999
        return String.valueOf(code);
    }
}
