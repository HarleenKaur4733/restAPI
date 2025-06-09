package com.example.restApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.restApi.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	
}
