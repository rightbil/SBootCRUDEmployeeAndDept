package com.example.crud.repository;
import com.example.crud.model.Department;

import org.hibernate.validator.internal.constraintvalidators.bv.time.futureorpresent.FutureOrPresentValidatorForDate;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
  public ArrayList<Department> findByName(String name);
  public void deleteById(Long id);

}
