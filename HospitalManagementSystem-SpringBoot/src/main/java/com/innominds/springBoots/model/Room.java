package com.innominds.springBoots.model;

public class Room {
	private int roomNo;
	private String roomType;
	private String status;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setType(String type) {
		this.roomType = type;
	}
	
}
