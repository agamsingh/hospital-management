package com.international_sos.hospital_management.service;

import com.international_sos.hospital_management.entity.Doctor;
import com.international_sos.hospital_management.entity.Patient;

import java.util.List;
import java.util.Set;

public interface DoctorService {

    // create doctor entity
    Doctor persistDoctor(Doctor doctor) ;
    // fetch all doctors
    List<Doctor> fetchAllDoctors();
    // fetch doctors by id
    Doctor fetchDoctorDetailsById(Long id) ;
    // update doctor details
    Doctor updateDoctorDetails(Doctor doctor, Long doctorId) ;
    // delete doctors by id
    void deleteDoctorDetailsById(Long id) ;
    // fetch all patients list by doctor id
    Set<Patient> fetchPatientListByDoctorId(Long id);
}
