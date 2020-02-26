package com.example.crud.repository;

import com.example.crud.model.Department;

import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
