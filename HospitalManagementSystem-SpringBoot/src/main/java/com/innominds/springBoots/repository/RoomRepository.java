package com.innominds.springBoots.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.innominds.springBoots.model.Room;
@Repository
public class RoomRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private String query;
	public int addRoom(Room room) {
		
		query = "insert into room(roomNo, roomType) values(?,?)";
		return jdbcTemplate.update(query, room.getRoomNo(),room.getRoomType());
	}
	public int deleteRoom(Integer roomNo) {
		query = "delete from room where roomNo ="+roomNo;
		return jdbcTemplate.update(query);
	}
	public List<Map<String, Object>> availableRoomsByType(String roomType) {
		query = "select roomNo from room where status = 'available' and roomType = ?";
		return jdbcTemplate.queryForList(query, roomType);
	}
	public List<Map<String, Object>> allRooms() {
		query = "select * from room";
		return jdbcTemplate.queryForList(query);
	}
}
