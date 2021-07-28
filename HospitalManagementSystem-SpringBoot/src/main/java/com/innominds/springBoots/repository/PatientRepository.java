package com.innominds.springBoots.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.innominds.springBoots.model.Patient;

@Repository
public class PatientRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private String query;
	public int registerPatient(Patient patient) {
		
		query="Insert into patient(PatientId, Name, Age, Gender, DiagnosisInfo, DateOfJoining, Address, PhoneNo, ConsultedDoctorId, AssignedRoomNo) values(?,?,?,?,?,?,?,?,?,?)";
		int insert=jdbcTemplate.update(query,patient.getPatientId(),patient.getName(),patient.getAge(),patient.getGender(),patient.getDiagnosisInfo(),patient.getDateOfJoining()
				,patient.getAddress(),patient.getPhoneNo(),patient.getConsultedDoctorId(),patient.getAssignedRoomNo());
		query = "update room set status = 'Assigned to "+patient.getPatientId()+"' where roomNo = ?";
		if(jdbcTemplate.update(query, patient.getAssignedRoomNo()) == 1)
			System.out.println("room was Assined to "+patient.getPatientId());
		 return insert;
		 
	}
	public List<Map<String, Object>> patientById(Integer patientId) {
		query = "select * from patient where patientId = ?";
		return jdbcTemplate.queryForList(query,patientId);
	}
	public int dischargePatient(Integer patientId, Integer billAmount, LocalDate dateOfDischarge) {
		
		query = "update patient set billAmount = ?, dateOfDischarge = ? where patientId =?";
		return jdbcTemplate.update(query,billAmount, dateOfDischarge, patientId);
	}
	
}