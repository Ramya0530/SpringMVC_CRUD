package com.example.SpringDemo.controller;

import com.example.SpringDemo.entity.Employee;
import com.example.SpringDemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    //@Autoeired not Required if using a single contructor in MVC Controller
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping("/list")
    public String getEmployees(Model model){
        //get the employees from the database
        List<Employee> employees = employeeService.findAll();

        //add to the spring model
        model.addAttribute("employees", employees);

        return "employees/list-employees";
    }
    @GetMapping("/addEmployeePage")
    public String addEmployeePage(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employees/employee-form";
    }
    @GetMapping("/updateEmployeePage")
    public String updateEmployeePage(@RequestParam int employeeId, Model model){
        Employee employee = employeeService.findById(employeeId);
        model.addAttribute("employee", employee);
        return "employees/employee-form";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.save(employee);
        //use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam int employeeId, Model model){
        employeeService.deleteById(employeeId);
        return "redirect:/employees/list";
    }

}
