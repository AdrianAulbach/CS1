package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The Patient class represents a real world patient.
 *
 * @author Samuel Egger
 */
@Entity
@Table(name = "patient")
public class Patient extends Person {

	
    private User doctor = null;
    
    
    // TODO doctor, prescriptions, records
    
//    @ManyToMany
//    private List<Doctor> doctors;
//
//    @OneToMany(mappedBy = "patient")
//    private List<Prescription> perscriptions;

//    @OneToOne
//    private MedicalRecord medicalRecord;

//    public MedicalRecord getMedicalRecord() {
//        return medicalRecord;
//    }
//
//    /*
//	 * @param medicalRecord sets medicalRecord 
//     */
//    public void setMedicalRecord(MedicalRecord medicalRecord) {
//        this.medicalRecord = medicalRecord;
//    }

//    /*
//	 * @return returns List of doctors
//     */
//    public List<Doctor> getDoctors() {
//        return doctors;
//    }
//
//    /*
//	 * @return returns List of Prescriptions
//     */
//    public List<Prescription> getPerscriptions() {
//        return perscriptions;
//    }

//    /*
//     * @param doctor adds doctor to patient 
//     */
//    public void add(Doctor doctor) {
//        this.doctors.add(doctor);
//    }
//
//    /*
//     * @param doctor removes doctor from patient
//     * 
//     */
//    public void remove(Doctor doctor) {
//        this.doctors.remove(doctor);
//    }
}
