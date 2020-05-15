package com.eliz.springbootwithrest.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eliz.springbootwithrest.entity.Employee;
import com.eliz.springbootwithrest.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	public EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee findById(@PathVariable int employeeId){
		Employee employee = employeeService.findById(employeeId);
		if(employee == null)
		{
			throw new RuntimeException("Employee not Found id:"+employeeId);
		}
		return employee;
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee){
		//just in case they pass an id in JSON... set id to 0
		//this is to force a save of new item... instead of update
		employee.setId(0);
		employeeService.save(employee);
		return employee;
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee){
		
		employeeService.save(employee);
		return employee;
	}
	

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId){
		Employee employee = employeeService.findById(employeeId);
		if(employee == null) {
			throw new RuntimeException("Employee Not Found");
		}
		
		employeeService.deleteById(employeeId);
		
		return "Deleted Employee Id:"+employeeId;
	}


	
	
}
