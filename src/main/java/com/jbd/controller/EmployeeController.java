package com.jbd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jbd.config.Response;
import com.jbd.entity.Employee;
import com.jbd.service.EmployeeService;

@RestController
@Validated
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/api/v1")
	public List<Employee> getAllEmployees() throws Exception {

		return employeeService.getAllEmployees();
	}

	@DeleteMapping("/api/v1/{id}")
	public boolean deleteEmployee(@PathVariable("id") int id) {

		return employeeService.deleteEmployee(id);
	}

	@PostMapping("/api/v1/add")
	public boolean insertEmployee(@RequestBody Employee employee) {

		return employeeService.insertEmployee(employee);
	}

	@PutMapping("/api/v1/update/{id}")
	public ResponseEntity<Response> updateEmployee(@RequestBody Employee employee, @PathVariable("id") int id) {

		employee.setEmpId(id);
		Employee updatedEmployee = employeeService.updateEmployee(employee);
		return new ResponseEntity<Response>(new Response("Record Updated Successfully", updatedEmployee, null),
				HttpStatus.OK);
	}

}
