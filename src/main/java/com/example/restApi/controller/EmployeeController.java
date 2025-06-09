package com.example.restApi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restApi.model.Employee;
import com.example.restApi.repository.EmployeeRepository;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
		
	//get
	@GetMapping("/get")
	public ResponseEntity<Object> getAllEmployees(){
		return ResponseEntity.status(HttpStatus.FOUND).body(employeeRepository.findAll());
	}
	
	
	//post
	@PostMapping("/save")
	public ResponseEntity<Object> create(@RequestBody Employee employee){
		employeeRepository.save(employee);
		System.out.println(employee);
		System.out.println("Received: " + employee.getName() + ", " + employee.getCity() + ", " + employee.getAge());
		return ResponseEntity.status(HttpStatus.CREATED).body("Employee created successfully");
	}
	
	//put
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody Employee employee ){
		Optional<Employee> emp = employeeRepository.findById(id);
		if(emp.isPresent()) {
			Employee existingEmp = emp.get();
			existingEmp.setName(employee.getName());
			existingEmp.setCity(employee.getCity());
			existingEmp.setAge(employee.getAge());
			employeeRepository.save(existingEmp);
			return ResponseEntity.status(HttpStatus.OK).body("Employee updated successfully for id " + id);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employee found for id " + id);
		}
	}
	//delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id){
		Optional<Employee> empContainer = employeeRepository.findById(id);
		if(empContainer.isPresent()) {
			employeeRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully for id " + id);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No employee found for id " + id);
		}
		
		
	}
}
