package com.jbd.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbd.controller.EmployeeController;
import com.jbd.dao.EmployeeDao;
import com.jbd.entity.Employee;
import com.jbd.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class.getName());

	@Autowired
	private EmployeeDao empDao;

	public List<Employee> getAllEmployees() throws Exception {

		return empDao.getAllEmployees();
	}

	@Override
	public boolean deleteEmployee(int id) {

		return empDao.deleteEmployee(id);
	}

	@Override
	public boolean insertEmployee(Employee employee) {

		return empDao.insertEmployee(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {

		Employee updatedEmployee = empDao.updateEmployee(employee);
		return updatedEmployee;
	}

}
