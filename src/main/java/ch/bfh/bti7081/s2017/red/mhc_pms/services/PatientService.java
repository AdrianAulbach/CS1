package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Patient;
import java.util.List;

/**
 * The patient service definition.
 *
 * @author Samuel Egger
 */
public interface PatientService {

    /**
     * Finds a patient with the given id.
     *
     * @param patientId the patient id
     * @return a patient object or null
     */
    Patient findPatientById(long patientId);

    /**
     * Finds patients which match the input filter
     *
     * @param filter the filter criteria
     * @return a list of patients or null
     */
    List<Patient> findPatientsByFilter(String filter);

    /**
     * Creates or updates a patient
     *
     * @param patient a patient object
     */
    void saveOrUpdatePatient(Patient patient);

    /**
     * Deletes the patient with the given id.
     *
     * @param patientId the patient id
     */
    void deletePatient(long patientId);

    /**
     * Notifies a patient with (e.g. by SMS or Email).
     *
     * @param patientId the id of the patient to notify
     * @param message the message to send
     */
    void notifyPatient(long patientId, String message);

    // TODO: Create Prescription class
    // void addPrescription(long patientId, Prescription prescription);
    // void validatePrescription(Prescription prescription);
}
