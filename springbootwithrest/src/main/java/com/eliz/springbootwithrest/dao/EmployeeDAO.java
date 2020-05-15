package com.eliz.springbootwithrest.dao;

import java.util.List;

import com.eliz.springbootwithrest.entity.Employee;

public interface EmployeeDAO {
	
	//Find all employees
	public List<Employee> findAll();
	
	//GetEmployee by ID
	public Employee findById(int theId);
	
	//save Employee
	public void save(Employee employee);
	
	//Delete Employee by ID
	public void deleteById(int theId);
	
	

}
