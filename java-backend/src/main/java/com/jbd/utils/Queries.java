package com.jbd.utils;

public class Queries {

	public static final String GET_ALL_EMPLOYEES = "select * from employee";
	public static final String GET_EMPLOYEE = "select * from employee where employeeId = ?";
	public static final String DELETE_EMPLOYEE = "delete from employee where employeeId = ?";
	public static final String INSERT_EMPLOYEE = "insert into employee (employeeName, employeeAge) values (?, ?)";
	public static final String UPDATE_EMPLOYEE = "update employee set employeeName = ?, employeeAge = ? where employeeId = ?";
	
}
