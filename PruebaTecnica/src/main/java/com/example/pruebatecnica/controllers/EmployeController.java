package com.example.pruebatecnica.controllers;

import com.example.pruebatecnica.models.dtos.EmployeDTO;
import com.example.pruebatecnica.models.dtos.MessageDTO;
import com.example.pruebatecnica.models.dtos.UpdateEmployeDTO;
import com.example.pruebatecnica.models.entities.Employe;
import com.example.pruebatecnica.services.EmployeService;
import com.example.pruebatecnica.utils.RequestErrorHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employe")
public class EmployeController {
    @Autowired
    private EmployeService employeService;

    @Autowired
    private RequestErrorHandler errorHandler;

    @PostMapping("/")
    public ResponseEntity<?> saveEmploye(@RequestBody @Valid EmployeDTO info, BindingResult validations) throws Exception {
        if (validations.hasErrors()) {
            return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }
        try {
            Employe employe = employeService.createEmploye(info);
            return new ResponseEntity<>(employe, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateEmploye(@RequestBody @Valid UpdateEmployeDTO info, BindingResult validations) throws Exception {
        if (validations.hasErrors()) {
            return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }
        try {
            Employe employe = employeService.updateEmploye(info);
            return new ResponseEntity<>(employe, HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Code user not found"), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{code}/")
    public ResponseEntity<?> deleteEmploye(@PathVariable String code) {
        try {
            employeService.deleteEmployeByCode(code);
            return new ResponseEntity<>(new MessageDTO("Employe deleted"), HttpStatus.OK);
        }catch (ChangeSetPersister.NotFoundException e){
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Code user not found"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllEmployes() {
        try {
            return new ResponseEntity<>(employeService.getAllEmployes(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
