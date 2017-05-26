package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Clinics class represents a real world clinic.
 * 
 * @author Florian Rindlisbacher
 */

@Entity
@Table(name = "CLINIC")
public class Clinic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "clinic_ID", unique = true, nullable = false)
	private EntityBase clinicId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "locations")
	@OneToMany(mappedBy="clinic")
	private List<Location> locations;
	
	private Clinic(String n, Location l){
		this.name = n;
		this.locations.add(l);
	}
	
	/*
	 * @return returns clinic ID
	 */
	public EntityBase getClinic_ID() {
		return this.clinicId;
	}
	
	/*
	 * @return returns clinic locations
	 */
	public List<Location> getLocations() {
		return locations;
	}
	
	/*
	 * @return returns clinic name
	 */
	public String getName() {
		return name;
	}
		
	/*
	 * @param name sets clinic name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * @param location adds clinic location 
	 */
	public void add(Location location) {
		this.locations.add(location);
	}
	
	/*
	 * @param location remove clinic location
	 * 
	 * TODO: throw exception if its last location in List
	 * Should removed location be returned????
	 */
	public void remove(Location location) {
		this.locations.remove(location);
	}
	
	
}
