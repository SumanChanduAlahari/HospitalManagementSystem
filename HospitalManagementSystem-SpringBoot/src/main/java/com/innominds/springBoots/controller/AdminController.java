package com.innominds.springBoots.controller;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.innominds.springBoots.model.Employee;
import com.innominds.springBoots.model.Patient;
import com.innominds.springBoots.model.Room;
import com.innominds.springBoots.service.AdminService;

@RestController
@RequestMapping("hospitalManagement/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping(path = "patient")
	public String registerPatient(@RequestBody Patient patient) {
		patient.setDateOfJoining(LocalDate.now());
		if (adminService.registerPatient(patient) == 1) {
			return "added Successfully....";
		}
		return "error while adding...";
	}
	
	@PostMapping(path = "employee", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addEmployee(@RequestBody Employee employee) {
		if (adminService.addEmployee(employee) == 1) {
			return "added Successfully....";
		}
		return "error while adding...";
	}
	
	@PostMapping(path = "login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestBody Map<String, String> map) {
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		if (adminService.login(Integer.parseInt(map.get("employeeId")),map.get("password"), time, date) == 1) {
			return "login successful....";
		}
		return "login unsuccessful...... ";
	}
	
	@PostMapping(path ="room", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addRoom(@RequestBody Room room) {
		if( adminService.addRoom(room) == 1)
			return "added successfully...";
		return "adding room unsuccessfull.";
	}
	
	@GetMapping(path = "employee/id")
	public Map<String, Object> employeeById(@RequestParam Integer employeeId) {
		return adminService.employeeById(employeeId);
	}
	
	@GetMapping(path = "employee/type")
	public List<Map<String, Object>> employeeByType(@RequestParam String employeeType) {
		return adminService.employeeByType(employeeType);
	}
	
	@GetMapping(path= "employee/date")
	public List<Map<String,Object>> employeePresenceByDate(@RequestParam String entryDate) {
		return adminService.employeePresenceByDate(entryDate);
	}
	
	@GetMapping(path = "patient/id")
	public List<Map<String,Object>> patientById(@RequestParam Integer patientId) {
		return adminService.patientById(patientId);
	}
	
	@GetMapping(path = "room")
	public List<Map<String,Object>> allRooms() {
		return adminService.allRooms();
	}
	
	@GetMapping(path ="room/type")
	public List<Map<String,Object>> availableRoomsByType(@RequestParam String roomType) {
		return adminService.availableRoomsByType(roomType);
	}
	
	@PutMapping(path = "logout", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String logout(@RequestBody Map<String, Integer> map) {
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		if (adminService.logout(map.get("employeeId"), time, date) == 1)
			return "Logout Successful";
		return "Logout Unsuccessful";
	}
	
	@PutMapping(path ="employee", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateEmployeeById(@RequestBody  Map<String, String> map) {
		if( adminService.updateEmployeeById(Integer.parseInt(map.get("employeeId")), map.get("columnName"), map.get("newValue")) == 1)
			return "Update Successfully...";
		return "Update Process was Unsuccessful.";
	}
	
	@PutMapping(path ="patient")
	public String dischargePatient(@RequestBody Map<String, Integer> map) {
		
		if(adminService.dischargePatient(map.get("patientId"), map.get("billAmount"), LocalDate.now()) == 1)
			return "disscharged...";
		return "not Discharged...";
	}
	
	@DeleteMapping(path ="employee")
	public String fireEmployee(@RequestParam Integer employeeId) {
		if( adminService.fireEmployee(employeeId) == 1)
			return "fired Successfully...";
		return "fired is unsuccessful";
	}
	
	@DeleteMapping(path ="room")
	public String deleteRoom(@RequestParam Integer roomNo) {
		if( adminService.deleteRoom(roomNo) == 1)
			return "room deleted Successfully...";
		return "room deletion was unsuccessful";
	}
}