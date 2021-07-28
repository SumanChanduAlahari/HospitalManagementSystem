package com.innominds.springBoots.model;

public class Employee extends Person{
	private int EmployeeId;
	private String EmployeeType;
	private float salary;
	private String department;
	private String password;
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEmployeeId() {
		return EmployeeId;
	}
	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmployeeType() {
		return EmployeeType;
	}
	public void setEmployeeType(String employeeType) {
		EmployeeType = employeeType;
	}
	
}
