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
       // create a department object
      /*  Department d = new Department();
        d.setName("Google");
        // prepare Arraylist to hold employees
        List<Employee> list = new ArrayList<>();
        //Employee #1
        Employee e1= new Employee();
        e1.setFirstName("Bililign");
        e1.setLastName("Gebru");
        e1.setSalary(1000);
        e1.setRole("manager");
        employeeRepository.save(e1);

        list.add(e1);

        // Employee #2
        Employee e2= new Employee();
        e2.setFirstName("Mihret ");
        e2.setLastName("Alemu");
        e2.setSalary(2000);
        e2.setRole("Role Player");
        list.add(e2);
        employeeRepository.save(e2);

        d.setEmployees(list);
        departmentRepository.save(d);*/
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

    // Employee
    @RequestMapping(value="/addEmployee")
    public String addEmployee( Model model){
//        model.addAttribute("emps", employeeRepository.findAll());
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
        return "searchResult";
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
