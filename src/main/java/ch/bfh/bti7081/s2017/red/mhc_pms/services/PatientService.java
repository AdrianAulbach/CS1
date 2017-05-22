package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Patient;
import java.util.List;

/**
 * The patient service definition.
 *
 * @author Samuel Egger
 */
public interface PatientService {

    List<Patient> findPatientsByFilter(String filter);

    Patient findPatientById(long patientId);

    void saveOrUpdatePatient(long patientId);

    void deletePatient(long patientId);

    void notifyPatient(long patientId, String message);

    // TODO: Create Prescription class
    
    // void addPrescription(long patientId, Prescription prescription);
    
    // void validatePrescription(Prescription prescription);
}
