package com.example.crud.resource;

import com.example.crud.model.Department;
import com.example.crud.model.Employee;
import com.example.crud.repository.DepartmentRepository;
import com.example.crud.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
@Controller
public class HomeController {
    public DepartmentRepository departmentRepository;
    //what is the purpose of the ff line???
    public EmployeeRepository employeeRepository;
    @GetMapping("/")
    public String index(Model model) {
       // create a department object
        Department d = new Department();
        d.setName("Eng");
        // prepare Arraylist to hold employees
        List<Employee> list = new ArrayList<>();
        //Employee #1
        Employee e1= new Employee();
        e1.setFirstName("Bililign");
        e1.setLastName("Gebru");
        e1.setSalary(1000);
        e1.setRole("manager");

        list.add(e1);



        // Employee #2
        Employee e2= new Employee();
        e2.setFirstName("Mihret ");
        e2.setLastName("Alemu");
        e2.setSalary(2000);
        e2.setRole("Role Player");
        list.add(e2);
        d.setEmployees(list);
        departmentRepository.save(d);
        model.addAttribute("departments", departmentRepository.findAll());
        return "index";
    }
   /* @GetMapping(value="/update/{name}")
    public List<Employee> update(@PathVariable final String name){
        Employee employee= new Employee();
        Department department = new Department();
        department.setName("Engineering");
        employee.setFirstName("Bililign");
        employee.setLastName("Gebru");
        employee.setRole("Head");
        employee.setSalary(1000.99);


        employeeRepository.save(employee);
        return (List<Employee>) employeeRepository.findAll();
    }*/
}
