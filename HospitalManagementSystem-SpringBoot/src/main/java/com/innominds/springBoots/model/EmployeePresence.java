package com.innominds.springBoots.model;
// for JPA table(EmployeeAttendence) creation 
import java.time.LocalDate;
import java.time.LocalTime;

public class EmployeePresence {
	private int EmployeeId;
	private LocalDate entryDate;
	private LocalTime loginTime;
	private LocalTime logoutTime;
}
