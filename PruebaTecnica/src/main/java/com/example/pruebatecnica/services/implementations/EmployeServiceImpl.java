package com.example.pruebatecnica.services.implementations;

import com.example.pruebatecnica.models.dtos.EmployeDTO;
import com.example.pruebatecnica.models.dtos.UpdateEmployeDTO;
import com.example.pruebatecnica.models.entities.Employe;
import com.example.pruebatecnica.models.entities.QEmploye;
import com.example.pruebatecnica.repositories.EmployeRepository;
import com.example.pruebatecnica.services.EmployeService;
import com.example.pruebatecnica.utils.EmployeMapper;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeServiceImpl implements EmployeService {

    @Autowired
    EmployeRepository employeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

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
        QEmploye qEmploye = QEmploye.employe;
        BooleanExpression predicate = qEmploye.code.eq(code);
        return queryFactory.selectFrom(qEmploye).where(predicate).fetchOne();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteEmployeByCode(String code) throws Exception{
        try {
            QEmploye qEmploye = QEmploye.employe;
            BooleanExpression predicate = qEmploye.code.eq(code);
            Employe employe = queryFactory.selectFrom(qEmploye).where(predicate).fetchOne();

            if (employe != null) {
                employeRepository.delete(employe);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            throw new Exception("Cannot delete employe");
        }
    }

    @Override
    public List<Employe> getAllEmployes() {
        QEmploye qEmploye = QEmploye.employe;
        return queryFactory.selectFrom(qEmploye).fetch();
    }
}
