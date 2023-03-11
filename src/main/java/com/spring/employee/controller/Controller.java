package com.spring.employee.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.employee.model.Employee;
import com.spring.employee.repository.EmplyeeRepository;

@RestController
@RequestMapping("/api/v1/")
public class Controller {
	@Autowired
	private EmplyeeRepository employeeRepository;
	
	@CrossOrigin(origins = "http://localhost:4200/")
	
	//get all employee data
	@GetMapping("/employees")
	public List<Employee> getEmployee(){
		return employeeRepository.findAll();
	}
	
	//Create Employee RestAPI
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	//get employee data by Id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		Employee employee=employeeRepository.findById(id)
				.orElseThrow(null);
		return ResponseEntity.ok(employee);	
	}
	
	
	
	//Update employee data 
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetail){
		Employee employee=employeeRepository.findById(id)   //get data using employee id
				.orElseThrow(null);
		employee.setFirstName(employeeDetail.getFirstName());
		employee.setLastName(employeeDetail.getLastName());
		employee.setEmailId(employeeDetail.getEmailId());
		
		Employee updateEmployee=employeeRepository.save(employee);
		return ResponseEntity.ok(updateEmployee);
	}
	
	
	//Delete employee data by id
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee=employeeRepository.findById(id)      //get data using employee id
				.orElseThrow(null);
		employeeRepository.delete(employee);
		
		Map<String, Boolean> response=new HashMap<>();
		response.put("delete employee data", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
}
