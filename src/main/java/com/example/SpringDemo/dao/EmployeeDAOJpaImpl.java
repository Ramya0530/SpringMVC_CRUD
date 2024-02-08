package com.example.SpringDemo.dao;

import com.example.SpringDemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    //define entity manager
    private EntityManager entityManager;

    /* setup constructor injection
    EntityManager Object Automatically initialized or created by Spring boot, we just injected here using Autowired. Constructor Injection */
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public List<Employee> findAll() {
        //Create Query
        TypedQuery<Employee> typedQuery = entityManager.createQuery("from Employee", Employee.class);

        //execute query and get result List;
        List<Employee> employees = typedQuery.getResultList();

        //return the resultsr
        return employees;
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee save(Employee employee) {
        //it will update your entity based on your ID. If ID is 0 then it will insert new data into database
        return entityManager.merge(employee);
    }

    @Override
    public void deleteById(int id) {
        //find the employee by ID
        Employee employee = entityManager.find(Employee.class, id);
        //remove the employee from Database
        entityManager.remove(employee);
    }

}
