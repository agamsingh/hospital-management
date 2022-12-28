package com.international_sos.hospital_management.service.impl;

import com.international_sos.hospital_management.entity.Patient;
import com.international_sos.hospital_management.exception.NotFoundException;
import com.international_sos.hospital_management.respository.PatientRepository;
import com.international_sos.hospital_management.service.PatientService;
import com.international_sos.hospital_management.util.HospitalHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
public class PatientServiceImpl implements PatientService {

    private static final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HospitalHelper helper;

    @Override
    public List<Patient> fetchAllPatient() {
        List<Patient> patients = patientRepository.findAll();
        if(!CollectionUtils.isEmpty(patients)) {
            return patients;
        }else {
            throw new NotFoundException("No Patient Data found");
        }
    }

    @Override
    public Patient fetchPatientById(Long id) {
        Patient patientDetails =  patientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Patient not found with ID : "+ id);
                    return new NotFoundException("Patient not found");
                }) ;
        return patientDetails;
    }

    @Override
    public Patient updatePatientDetails(Patient newPatient, Long patientId) {
        return patientRepository.findById(patientId)
                .map(updatedPatientDetails -> {
                    if (Objects.nonNull(newPatient.getName()) && !"".equalsIgnoreCase(newPatient.getName())){
                        updatedPatientDetails.setName(newPatient.getName());
                    }
                    if (helper.validateEmail(newPatient.getEmail())){
                        updatedPatientDetails.setEmail(newPatient.getEmail());
                    }
                    if (helper.validatePhoneNumber(newPatient.getContactNo())){
                        updatedPatientDetails.setContactNo(newPatient.getContactNo());
                    }
                    if (Objects.nonNull(newPatient.getAge())){
                        updatedPatientDetails.setAge(newPatient.getAge());
                    }
                    log.info("patient details updated for id : "+ patientId);
                    return patientRepository.save(updatedPatientDetails);
                }).orElseThrow(() -> {
                    log.error("Patient not found with ID : "+ patientId);
                    return new NotFoundException("Patient not found with ID : "+ patientId);
                }) ;
    }

    @Override
    public void deletePatientDetailsByID(Long id) {
        Patient patientDetails =  patientRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Patient not found with ID : "+ id);
                    return new NotFoundException("Patient not found with ID : "+ id);
                }) ;
        patientRepository.delete(patientDetails);
    }

    @Override
    public void deleteAllPatients() {
        patientRepository.deleteAll();
    }
}
