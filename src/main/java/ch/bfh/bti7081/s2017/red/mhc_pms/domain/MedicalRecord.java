package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The MedicalRecodrs Class contains all Event Records informations written by doctors
 *
 * @author Florian Rindlisbacher
 */

@Entity
@Table(name="medical_record")
public class MedicalRecord extends PersistentObject {

	private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "medical_record")
    private List<Report> reports = new ArrayList<Report>();

    @OneToOne
    private Patient patient;

    /*
     * @return returns patient
     */
    public Patient getPatient() {
		return patient;
	}
    
    /*
     * @param sets patient relation
     * TODO: -> is this realy requiered? 
     */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/*
	 * @return retuns List with all reports
	 */
	public List<Report> getReports() {
		return reports;
	}

	/*
	 * @param report adds report to MedicalRecord
	 */
	public void add(Report report) {
        reports.add(report);
    }
	
	/*
	 * @param report removes report from MedicalRecord
	 */
    public void remove(Report report) {
        reports.remove(report);
    }
}
