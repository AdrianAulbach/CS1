package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Room class represents a unique identified room and is attached to a location.
 * 
 * @author Florian Rindlisbacher
 */

@SuppressWarnings("serial")
@Entity
@Table(name="ROOM")
public class Room extends PersistentObject {

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "room_ID", unique = true, nullable = false)
//	private EntityBase roomId;
	
	@Column(name = "floor")
	// TODO: REAL WORLD -> Foor numbering/naming can have characters as well Example: E as in Erdgeschoss
	private int floor;

	@Column(name = "room")
	private String room;

	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;

//	public EntityBase getRoomId() {
//		return roomId;
//	}
	/*
	 * @return returns floor name
	 */
	public int getFloor() {
		return floor;
	}
	
	/*
	 * @return returns room name
	 */
	public String getRoom() {
		return room;
	}

	/*
	 * @return returns location object
	 */
	public Location getLocation() {
		return location;
	}

	/*
	 * @param floor sets floor number/level
	 */
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	/*
	 * @param room sets room number or name as String
	 */
	public void setRoom(String room) {
		this.room = room;
	}

	/*
	 * @param location sets location object
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
}
