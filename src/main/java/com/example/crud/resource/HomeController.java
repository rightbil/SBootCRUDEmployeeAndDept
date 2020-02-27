package com.example.crud.resource;

import com.example.crud.model.Department;
import com.example.crud.model.Employee;
import com.example.crud.repository.DepartmentRepository;
import com.example.crud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    public DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeRepository employeeRepository;
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        return "index";
    }

    // Department
    @RequestMapping(value="/addDepartment")
    public String addDept( Model model){
     model.addAttribute("department",new Department());
            return "departmentForm";
        }
    @RequestMapping(value="/addDepartment", method = RequestMethod.POST)
    public String addDept(@ModelAttribute Department dept, Model model){
         departmentRepository.save(dept);
        return "redirect:/";
    }


    @RequestMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable("id") long id){
        departmentRepository.deleteById(id);
        return "redirect:/";
    }


    @RequestMapping("/updateDepartment/{id}")
    public String upateDepartment(@PathVariable("id") long id, Model model){
        model.addAttribute("department", departmentRepository.findById(id).get());
        return "departmentForm";
    }


    // Employee
    @RequestMapping(value="/addEmployee")
    public String addEmployee( Model model){
        Object o= departmentRepository.findAll();
        model.addAttribute("depts",departmentRepository.findAll());
        model.addAttribute("employee",new Employee());
        return "employeeForm";
    }
    @RequestMapping(value="/addEmployee", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute Employee employee){
        employeeRepository.save(employee);
        return "redirect:/";
    }

    @RequestMapping("/search")
    public String search(@RequestParam("search") String search, Model model){
        model.addAttribute("searchResult",departmentRepository.findByName(search));
        return "search";
    }

    @RequestMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") long id){
        employeeRepository.deleteById(id);
        return "redirect:/";
    }


    @RequestMapping("/updateEmployee/{id}")
    public String upateEmployee(@PathVariable("id") long id, Model model){
       model.addAttribute("update", employeeRepository.findById(id).get());
       return "index";
    }

}
