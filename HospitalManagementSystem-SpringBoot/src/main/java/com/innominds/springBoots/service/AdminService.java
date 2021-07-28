package com.innominds.springBoots.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innominds.springBoots.model.Employee;
import com.innominds.springBoots.model.Patient;
import com.innominds.springBoots.model.Room;
import com.innominds.springBoots.repository.EmployeeRepository;
import com.innominds.springBoots.repository.PatientRepository;
import com.innominds.springBoots.repository.RoomRepository;

@Service
public class AdminService {
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private RoomRepository roomRepository;
	// Adding patients
	public int registerPatient(Patient patient) {
		return patientRepository.registerPatient(patient);
	}

	// adding employee
	public int addEmployee(Employee employee) {

		return employeeRepository.addEmployee(employee);
	}

	// get doctorsList
	public Map<String, Object> getDoctorList() {

		return employeeRepository.getDoctorList();
	}
	// login
	public int login(int employeeId,String password, LocalTime time, LocalDate date) {

		return employeeRepository.login(employeeId,password, time, date);
	}
	//logout
	public int logout(int employeeId, LocalTime time, LocalDate date) {
		
		return employeeRepository.logout(employeeId, time, date);

	}

	public Map<String, Object> employeeById(Integer employeeId) {
		return employeeRepository.employeeById(employeeId);
	}

	public List<Map<String, Object>> employeePresenceByDate(String entryDate) {
		return employeeRepository.employeePresenceByDate(entryDate);
	}

	public List<Map<String, Object>> employeeByType(String employeeType) {
		
		return employeeRepository.employeeByType(employeeType);
	}

	public int fireEmployee(Integer employeeId) {
		return employeeRepository.fireEmployee(employeeId);
	}

	public int updateEmployeeById(int employeeId, String ColumnName, String newValue) {
		
		return employeeRepository.updateEmployeeById(employeeId, ColumnName, newValue);
	}

	public int addRoom(Room room) {
		
		return roomRepository.addRoom(room);
	}

	public int deleteRoom(Integer roomNo) {
		
		return roomRepository.deleteRoom(roomNo);
	}

	public List<Map<String, Object>> availableRoomsByType(String roomType) {
		
		return roomRepository.availableRoomsByType(roomType);
	}

	public List<Map<String, Object>> allRooms() {
		
		return roomRepository.allRooms();
	}

	public List<Map<String, Object>> patientById(Integer patientId) {
		
		return patientRepository.patientById(patientId);
	}

	public int dischargePatient(Integer patientId, Integer billAmount, LocalDate dateOfDischarge) {
	
		return patientRepository.dischargePatient(patientId, billAmount, dateOfDischarge);
	}
}