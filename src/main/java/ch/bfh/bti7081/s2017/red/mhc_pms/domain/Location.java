package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Location class represents a real world place (Post Address) which may houses a room.
 * 
 * @author Florian Rindlisbacher
 */

@Entity
@Table(name="LOCATION")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "location_ID", unique = true, nullable = false)
	private EntityBase locationId;
	
	@Column(name = "street")
	private String street;

	@Column(name = "city")
	private String city;

	@Column(name = "zip_code")
	private String zip;

	@Column(name = "rooms")
	@OneToMany(mappedBy="location")
	private List<Room> rooms;
	
	@ManyToOne
	@JoinColumn(name="clinic_id")
	private Clinic clinic = null;
	
	/*
	 * @return returns street name
	 */
	public String getStreet() {
		return street;
	}
	
	/*
	 * @return returns city name 
	 */
	public String getCity() {
		return city;
	}
	
	/*
	 * @return returns zip code
	 */
	public String getZip() {
		return zip;
	}

	/*
	 * @return returns clinic object
	 */
	public Clinic getClinic() {
		return clinic;
	}

	/*
	 * @return returns location id
	 */ 
	public EntityBase getLocationId() {
		return locationId;
	}

	/*
	 * @return returns rooms List<Room>
	 */
	public List<Room> getRooms() {
		return rooms;
	}
	
	/*
	 * @param street sets street name
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	
	/*
	 * @param city sets city name
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/*
	 * @param zip sets zip code
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	/*
	 * @param sets clinic object
	 */
	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}
	
	/*
	 * @param room adds room to location
	 */
	public void add(Room room) {
		this.rooms.add(room);
	}
	
	/*
	 * @param room remove room
	 * 
	 * TODO: throw exception if its last location in List
	 * Should removed location object be returned????
	 */
	public void remove(Room room) {
		this.rooms.remove(room);
	}
	
}
