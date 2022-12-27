package com.international_sos.hospital_management.service;

import com.international_sos.hospital_management.entity.Patient;

import java.util.List;

public interface PatientService {

    // CRUD opperation for patient entity
    // create patient entity
    Patient persistPatient(Patient patient) ;
    // fetch all patients list
    List<Patient> fetchAllPatient();
    // fetch patient by id
    Patient fetchPatientById(Long id) ;
    // update patient details
    Patient updatePatientDetails(Patient patient, Long patientId) ;
    //delete patient by id
    void deletePatientDetailsByID(Long id) ;
    //delete all patients
    void deleteAllPatients();

}
