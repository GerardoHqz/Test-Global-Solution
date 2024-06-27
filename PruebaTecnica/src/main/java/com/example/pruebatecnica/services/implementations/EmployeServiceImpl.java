package com.example.pruebatecnica.services.implementations;

import com.example.pruebatecnica.models.dtos.EmployeDTO;
import com.example.pruebatecnica.models.dtos.UpdateEmployeDTO;
import com.example.pruebatecnica.models.entities.Employe;
import com.example.pruebatecnica.repositories.EmployeRepository;
import com.example.pruebatecnica.services.EmployeService;
import com.example.pruebatecnica.utils.EmployeMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeServiceImpl implements EmployeService {

    @Autowired
    EmployeRepository employeRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Employe createEmploye(EmployeDTO info) throws Exception {
        try{
            Employe employe = EmployeMapper.toEntity(info);

            employeRepository.save(employe);

            return employe;
        }catch(Exception e){
            throw new Exception("Cannot save employe");
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Employe updateEmploye(UpdateEmployeDTO info) throws Exception {
        Employe employe = employeRepository.findByCode(info.getCode());
        if(employe == null)
            throw new NotFoundException();
        employe.setName(info.getName());
        employe.setLastName(info.getLastName());
        employe.setAddress(info.getAddress());
        employe.setSalary(info.getSalary());
        employe.setDepartment(info.getDepartment());

        employeRepository.save(employe);

        return employe;
    }

    @Override
    public Employe getEmployeByCode(String code) {
        return employeRepository.findByCode(code);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteEmployeByCode(String code) throws Exception{
        try{
            employeRepository.deleteByCode(code);
        }catch(Exception e){
            throw new Exception("Cannot delete employe");
        }
    }

    @Override
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }
}
