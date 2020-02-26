package com.example.crud.repository;

import com.example.crud.model.Department;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    ArrayList<Department> findByName(String name);
}
