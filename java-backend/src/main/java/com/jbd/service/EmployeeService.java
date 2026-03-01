package com.jbd.service;

import java.util.List;

import com.jbd.entity.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees() throws Exception;

	boolean deleteEmployee(int id);

	boolean insertEmployee(Employee employee);

	Employee updateEmployee(Employee employee);

	Employee getEmployeeById(int id);

}
