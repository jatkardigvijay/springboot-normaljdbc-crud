package com.jbd.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbd.dao.EmployeeDao;
import com.jbd.entity.Employee;
import com.jbd.utils.Queries;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	private DataSource dataSource;

	@Override
	public List<Employee> getAllEmployees() throws Exception {

		List<Employee> employeeList = new ArrayList<Employee>();
		PreparedStatement ps = null;

		try (Connection connection = dataSource.getConnection()) {

			ps = connection.prepareStatement(Queries.GET_ALL_EMPLOYEES);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				Employee emp = new Employee(rs.getInt("empId"), rs.getString("empName"), rs.getInt("epAge"));

				employeeList.add(emp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeList;
	}

	@Override
	public boolean deleteEmployee(int id) {

		PreparedStatement ps = null;

		try (Connection connection = dataSource.getConnection()) {

			ps = connection.prepareStatement(Queries.DELETE_EMPLOYEE);

			ps.setInt(1, id);

			boolean rs = ps.execute();

			if (rs == true) {

				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean insertEmployee(Employee employee) {

		PreparedStatement ps = null;

		try (Connection connection = dataSource.getConnection()) {

			ps = connection.prepareStatement(Queries.INSERT_EMPLOYEE);

			ps.setInt(1, employee.getEmpId());
			ps.setString(2, employee.getEmpName());
			ps.setInt(3, employee.getEpAge());

			int rs = ps.executeUpdate();

			if (rs == 1) {

				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public Employee updateEmployee(Employee employee) {

		PreparedStatement ps = null;
		try (Connection connection = dataSource.getConnection()) {

			ps = connection.prepareStatement(Queries.UPDATE_EMPLOYEE);

			if (employee != null) {

				int empId = employee.getEmpId();
				ps.setString(1, employee.getEmpName());
				ps.setInt(2, employee.getEpAge());
				ps.setInt(3, empId);

				int rs = ps.executeUpdate();

				if (rs == 1) {
					System.out.println("Record updated with id : " + empId);
				}

				return employee;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employee;
	}

}
