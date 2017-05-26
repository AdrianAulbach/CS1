package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Doctors Class represents a real world medical specialist
 *
 * @author Florian Rindlisbacher
 */

@Entity
@Table(name="doctor")
public class Doctor extends Person {
	
	private static final long serialVersionUID = 1L;

	@OneToOne
	private User user;
	
	@ManyToMany
	private List<Patient> patients;
	
	/*
	 * @return returns user
	 */
	public User getUser() {
		return user;
	}

	/*
	 * @param set user of doctor
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/*
	 * @return returns list of patients
	 */
	public List<Patient> getPatients() {
		return patients;
	}

	/*
	 * @param patient adds patient to List
	 */
	public void add(Patient patient) {
		this.patients.add(patient);
	}
	
	/*
	 * @param patient removes patient from List
	 */
	public void remove(Patient patient) {
		this.patients.remove(patient);
	}
	
	
}
