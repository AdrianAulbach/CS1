package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
<<<<<<< HEAD
@Table(name = "medical_record")
public class MedicalRecord extends PersistentObject {
=======
@Table(name="medical_record")
public class MedicalRecord extends PersistentObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy="clinic")
	private List<Report> reports;
	
	@OneToOne
	private Patient patient;
>>>>>>> 6dc7887d7d0d37c448e1f2492cbbdcc2d10b278a

    @OneToMany(mappedBy = "clinic")
    private List<Report> reports = new ArrayList<Report>();

    @OneToOne
    private Patient patient;

    public void add(Report report) {
        reports.add(report);
    }

    public void remove(Report report) {
        reports.remove(report);
    }
}
