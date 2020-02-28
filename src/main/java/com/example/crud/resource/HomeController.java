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

    //TODO:This method displays departments and thier employess in the index page
    //TODO:This page is like landing page and has also menus
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        return "index";
    }

    //TODO: provides an object scheme of Department
// the 'department' varible is key here as used in the departmentForm
// for the layout and structure of html
    // Department
    @RequestMapping(value = "/addDepartment")
    public String addDept(Model model) {
        model.addAttribute("department", new Department());
        return "departmentForm";
    }

    //TODO: This and the above method are related and when the
    // submit button is clicked from the departmentForm, it will
    // save and then redirect the page back to the homepage i, e index
    @RequestMapping(value = "/addDepartment", method = RequestMethod.POST)
    public String addDept(@ModelAttribute Department dept, Model model) {
        departmentRepository.save(dept);
        return "redirect:/";
    }

    //TODO: when a request comes from <a th:href="@{/deleteDepartment/{id} (id= ${d.id})}">Delete</a>
// the d.id rerefs the current object so it will find and delete in the repository
// this comes form index page where the departments are displayed.
    @RequestMapping("/deleteDepartment/{id}")
    public String deleteDepartment(@PathVariable("id") long id) {
        departmentRepository.deleteById(id);
        return "redirect:/";
    }

//TODO: when a request comes from <a th:href="@{/updateDepartment/{id} (id= ${d.id})}">Update</a>
// it will find the department using the id and pass it to departmentForm by name 'department'
// this is important b/c the department form will call addDepartment and the variable 'department' should be the same

    @RequestMapping("/updateDepartment/{id}")
    public String upateDepartment(@PathVariable("id") long id, Model model) {
        model.addAttribute("department", departmentRepository.findById(id).get());
        return "departmentForm";
    }


    // Employee
    @RequestMapping(value = "/addEmployee")
    public String addEmployee(Model model) {
        Object o = departmentRepository.findAll();
        model.addAttribute("depts", departmentRepository.findAll());
        model.addAttribute("employee", new Employee());
        return "employeeForm";
    }

    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public String addEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/";
    }

    @RequestMapping("/searchDepartment")
    public String search(@RequestParam("search") String search, Model model) {
        model.addAttribute("searchResult", departmentRepository.findByName(search));
        return "search";
    }

    @RequestMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") long id) {
        employeeRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/updateEmployee/{id}")
    public String upateEmployee(@PathVariable("id") long id, Model model) {
        model.addAttribute("employee", employeeRepository.findById(id).get());
        return "employeeForm";
    }

}
