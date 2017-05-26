package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Perscription Class represents a Real-World drug persecution
 *
 * @author Florian Rindlisbacher
 */
@Entity
@Table(name="perscription")
public class Prescription extends PersistentObject {

	private static final long serialVersionUID = 1L;

	private Date start;

	private Date end;

	private String medicament;

	private double dose;

	private String comment;

	private String unit;
	
	@ManyToOne
	private Patient patient;
	
	
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getMedicament() {
		return medicament;
	}

	public void setMedicament(String medicament) {
		this.medicament = medicament;
	}

	public double getDose() {
		return dose;
	}

	public void setDose(double dose) {
		this.dose = dose;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
