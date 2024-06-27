package com.example.pruebatecnica.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employe")
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idEmploye;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String salary;

    @Column(nullable = false)
    private String department;

    public Employe(String code,String name, String lastName, String address, String salary, String department) {
        this.code = code;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.salary = salary;
        this.department = department;
    }
}
