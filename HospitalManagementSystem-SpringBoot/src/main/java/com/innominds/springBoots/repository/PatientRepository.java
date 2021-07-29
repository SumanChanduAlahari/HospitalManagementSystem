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
		query = "select * from patient where PatientId=?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(query, patient.getPatientId());
		if(!list.isEmpty())
			return 0;
		// Insert patient details into patient table
		query="Insert into patient(PatientId, Name, Age, Gender, Address, PhoneNo) values(?,?,?,?,?,?)";
		int insert=jdbcTemplate.update(query, patient.getPatientId(),patient.getName(),patient.getAge(),patient.getGender()
					,patient.getAddress(),patient.getPhoneNo());
		// save the patient transaction
		savePatientTransaction(patient);
		return insert;
	}
	public void savePatientTransaction(Patient patient) {
		if(patient.getPatientType().equalsIgnoreCase("OutPatient")) {
			query = "insert into patientTransactions (PatientId, dateOfJoining, PatientType, diagnosisInfo, consultedDoctorId, billAmount) values(?,?,?,?,?,?)";
			jdbcTemplate.update(query, patient.getPatientId(), patient.getDateOfJoining(),patient.getPatientType(), patient.getDiagnosisInfo(),patient.getConsultedDoctorId(),patient.getBillAmount());
		}
		else {
		query = "insert into patientTransactions (patientId,patientType, dateOfJoining, diagnosisInfo, consultedDoctorId, AssignedRoomNo) values(?,?,?,?,?,?)";
		jdbcTemplate.update(query, patient.getPatientId(),patient.getPatientType(), patient.getDateOfJoining(), patient.getDiagnosisInfo(),patient.getConsultedDoctorId(), patient.getAssignedRoomNo());
		// Assigned room to patient in room table
		query = "update room set status = 'Assigned',OccupiedPatientId=? where roomNo = ?";
		if(jdbcTemplate.update(query, patient.getPatientId(),patient.getAssignedRoomNo()) == 1)
			System.out.println("room was Assined to "+patient.getPatientId());
		}
	}
	public List<Map<String, Object>> patientById(Integer patientId) {
		query = "select * from patient where patientId = ?";
		return jdbcTemplate.queryForList(query,patientId);
	}
	public int dischargePatient(Integer patientId, Integer billAmount, LocalDate dateOfDischarge) {
		// update patient details on patient table
		query ="select serialNo,billAmount from patientTransactions where patientId =?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(query, patientId);
		for(Map<String, Object> mapElement : list)
		{
			if((int)mapElement.get("billAmount") == 0)
				continue;
			list.remove(mapElement);
		}
		query = "update patientTransactions set billAmount = ?, dateOfDischarge = ? where serialNo =?";
		int update = jdbcTemplate.update(query,billAmount, dateOfDischarge, list.get(0).get("serialNo"));
		// change room status of assigned room to available
		query = "select AssignedRoomNo from patientTransactions where serialNo =?";
		Map<String, Object> map =jdbcTemplate.queryForMap(query, list.get(0).get("serialNo"));
		query = "update room set status ='Available',OccupiedPatientId = 0 where roomNo =?";
		if( jdbcTemplate.update(query, map.get("AssignedRoomNo")) == 1)
			System.out.println("room status set to available on discharge of"+patientId);
		else
			System.out.println("difficult to set the room status as available on discharge of"+patientId);
		return update;
	}
	public List<Map<String, Object>> patientByType(String patientType) {
		query = "select * from patient where patientId in (select patientId from patientTransactions where patientType = ?)";
		//query = "select patient.Name,  pt.DateOfJoining, pt.billAmount from patientTransactions pt where pt.patientType= ? innerjoin patient on patient.patientId = patientTransactions.patientId";
		return jdbcTemplate.queryForList(query, patientType);
	}
	
}