package com.innominds.springBoots.model;

import java.time.LocalDate;

public class Patient extends Person{
	private int patientId;
	private LocalDate dateOfJoining;
	private LocalDate dateOfDischarge;
	private String diagnosisInfo;
	private float billAmount;
	private int consultedDoctorId;
	private int assignedRoomNo;
	public int getAssignedRoomNo() {
		return assignedRoomNo;
	}
	public void setAssignedRoomNo(int assignedRoomNo) {
		this.assignedRoomNo = assignedRoomNo;
	}
	public int getConsultedDoctorId() {
		return consultedDoctorId;
	}
	public void setConsultedDoctorId(int consultedDoctorId) {
		this.consultedDoctorId = consultedDoctorId;
	}
	
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(LocalDate localDate) {
		this.dateOfJoining = localDate;
	}
	public LocalDate getDateOfDischarge() {
		return dateOfDischarge;
	}
	public void setDateOfDischarge(LocalDate localDate) {
		this.dateOfDischarge = localDate;
	}
	public String getDiagnosisInfo() {
		return diagnosisInfo;
	}
	public void setDiagnosisInfo(String diagnosisInfo) {
		this.diagnosisInfo = diagnosisInfo;
	}
	public float getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(float billAmount) {
		this.billAmount = billAmount;
	}
	
}
