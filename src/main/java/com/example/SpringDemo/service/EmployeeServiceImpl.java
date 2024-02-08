package com.example.SpringDemo.service;

import com.example.SpringDemo.dao.EmployeeDAO;
import com.example.SpringDemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO){
        this.employeeDAO = employeeDAO;
    }
    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        //it will update your entity based on your ID. If ID is 0 then it will insert new data into database
        return employeeDAO.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
       employeeDAO.deleteById(id);
    }
}
