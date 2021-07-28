package com.innominds.springBoots.repository;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.innominds.springBoots.model.Employee;

@Repository
public class EmployeeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private String query;

	public int addEmployee(Employee employee) {

		query = "Insert into Employee values(?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(query, employee.getEmployeeId(), employee.getName(), employee.getAge(),
				employee.getGender(), employee.getEmployeeType(), employee.getDepartment(), employee.getSalary(),
				employee.getAddress(), employee.getPhoneNo());
	}

	public Map<String, Object> getDoctorList() {

		query = "select employeeId,employeeName from Employee where employeeType='Doctor'";
		return jdbcTemplate.queryForMap(query);
	}

	public int login(int employeeId, String password,LocalTime time, LocalDate date) {
		// verify credentials
		query= "select password from employee where employeeId=?";
		List<Map<String, Object>> list= jdbcTemplate.queryForList(query, employeeId);
		if(list.isEmpty() || (!list.get(0).get("password").equals(password)))
			return 0;
		// after verify insert login time and date
		query = "insert into employeePresence(employeeid, logindate, logintime) values(?,?,?)";
		return jdbcTemplate.update(query, employeeId, date, time);
	}

	public int logout(int employeeId, LocalTime time, LocalDate date) {
		query = "update employeePresence set logoutTime = '" + time + "' where loginDate = '" + date
				+ "' and employeeId = " + employeeId;
		return jdbcTemplate.update(query);
	}

	public Map<String, Object> employeeById(Integer employeeId) {
		query = "select * from employee where employeeId = "+employeeId;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(query);
		if (list.isEmpty())
			return null;
		list.get(0).remove("password");
		return list.get(0);
	}

	public List<Map<String, Object>> employeePresenceByDate(String entryDate) {
		query = "select * from employeePresence where loginDate = '"+entryDate+"'";
		return jdbcTemplate.queryForList(query);
	}

	public List<Map<String, Object>> employeeByType(String employeeType) {
		query = "select * from employee where employeeType ='"+ employeeType +"'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(query);
		for(Map<String, Object> map : list)
			map.remove("password");
		return list;
	}

	public int fireEmployee(Integer employeeId) {
		query = "delete from employee where employeeId = "+employeeId;
		return jdbcTemplate.update(query);
	}

	public int updateEmployeeById(int employeeId, String columnName, String newValue) {
		query = "update employee set "+columnName+"= ? where employeeId = ?";
		return jdbcTemplate.update(query,newValue, employeeId);
	}
}