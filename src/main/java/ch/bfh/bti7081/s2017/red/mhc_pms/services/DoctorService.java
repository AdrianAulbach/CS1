package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Doctor;
import java.util.List;

/**
 * The event service allows to manage doctors.
 *
 * @author Samuel Egger
 */
public interface DoctorService {

    /**
     * Finds the doctor with the given id.
     *
     * @param doctorId the doctor id
     * @return the doctor with the given id or null
     */
    Doctor findDoctorById(int doctorId);

    /**
     * Returns all doctors that match the given filter.
     *
     * @param filter the search filter
     * @return a list of doctors
     */
    List<Doctor> findDoctorsByFilter(String filter);

    /**
     * Save or updates a doctor.
     *
     * @param doctor the doctor
     */
    void saveOrUpdateDoctor(Doctor doctor);

    /**
     * Delete a doctor.
     *
     * @param doctorId the id of the doctor to delete
     */
    void deleteDoctor(int doctorId);
}
