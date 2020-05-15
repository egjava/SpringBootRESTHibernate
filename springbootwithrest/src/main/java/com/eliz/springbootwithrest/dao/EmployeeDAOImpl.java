package com.eliz.springbootwithrest.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eliz.springbootwithrest.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
		
	}

	@Override
	public List<Employee> findAll() {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("from Employee", Employee.class);
		
		List<Employee> theEmployees = theQuery.getResultList();
		
		return theEmployees;
	}

	@Override
	public Employee findById(int theId) {
		Session currentSession =entityManager.unwrap(Session.class);
		Employee employee = currentSession.get(Employee.class, theId);
		
		return employee;
	}

	@Override
	public void save(Employee employee) {
		Session currentSession =entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(employee);
	}

	@Override
	public void deleteById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId",theId);
		theQuery.executeUpdate();
		
	}
	

}
