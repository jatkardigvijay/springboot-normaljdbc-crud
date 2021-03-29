package com.jbd.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.jbd.dao.EmployeeDao;
import com.jbd.entity.Employee;
import com.jbd.exception.JbdException;
import com.jbd.utils.Queries;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class.getName());

	@Autowired
	private DataSource dataSource;

	@Override
	public List<Employee> getAllEmployees() throws Exception {

		List<Employee> employeeList = new ArrayList<Employee>();
		PreparedStatement ps = null;

		try (Connection connection = dataSource.getConnection()) {

			ps = connection.prepareStatement(Queries.GET_ALL_EMPLOYEES);

			logger.info("Executing query : " + Queries.GET_ALL_EMPLOYEES);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Employee emp = new Employee(rs.getInt("employee_Id"), rs.getString("employee_Name"),
						rs.getInt("employee_Age"));

				employeeList.add(emp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new JbdException("Error executing stored procedure", HttpStatus.INTERNAL_SERVER_ERROR);
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

			ps.setInt(1, employee.getEmployeeId());
			ps.setString(2, employee.getEmployeeName());
			ps.setInt(3, employee.getEmployeeAge());

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

				int empId = employee.getEmployeeId();
				ps.setString(1, employee.getEmployeeName());
				ps.setInt(2, employee.getEmployeeAge());
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
