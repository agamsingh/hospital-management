package com.international_sos.hospital_management.service.impl;

import com.international_sos.hospital_management.entity.Doctor;
import com.international_sos.hospital_management.entity.Patient;
import com.international_sos.hospital_management.exception.AlreadyExistsException;
import com.international_sos.hospital_management.exception.NotFoundException;
import com.international_sos.hospital_management.respository.DoctorRepository;
import com.international_sos.hospital_management.service.DoctorService;
import com.international_sos.hospital_management.util.HospitalHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class DoctorServiceImpl implements DoctorService {

    private static final Logger log = LoggerFactory.getLogger(DoctorServiceImpl.class);

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private HospitalHelper helper;

    @Override
    public Doctor persistDoctor(Doctor doctor) {
        helper.validateDoctorData(doctor);
        log.info("Doctors Data validated : " + doctor.toString());
        try{
            return doctorRepository.save(doctor);
        }catch (Exception e){
            throw new AlreadyExistsException("Data Already present for doctor with id :"+ doctor.getId());
        }
    }

    @Override
    public List<Doctor> fetchAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        if(!CollectionUtils.isEmpty(doctors)) {
            return doctors;
        }else {
            throw new NotFoundException("No Doctors found");
        }
    }

    @Override
    public Doctor fetchDoctorDetailsById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(()->{
                    log.error("Doctor not found with ID : "+ id);
                    return new NotFoundException("Doctor not found with ID : "+ id);
                });
    }

    @Override
    public Doctor updateDoctorDetails(Doctor newdoctor, Long doctorId) {
        return doctorRepository.findById(doctorId)
                .map(updatedDoctorDetails -> {
                    if (Objects.nonNull(newdoctor.getName()) && !"".equalsIgnoreCase(newdoctor.getName())){
                        updatedDoctorDetails.setName(newdoctor.getName());
                    }
                    if (Objects.nonNull(newdoctor.getDegree()) && helper.validateEmail(newdoctor.getEmail())){
                        updatedDoctorDetails.setEmail(newdoctor.getEmail());
                    }
                    if (Objects.nonNull(newdoctor.getDegree()) && !"".equalsIgnoreCase(newdoctor.getDegree())) {
                        updatedDoctorDetails.setDegree(newdoctor.getDegree());
                    }
                    if (Objects.nonNull(newdoctor.getSpecialization()) && !"".equalsIgnoreCase(newdoctor.getSpecialization())) {
                        updatedDoctorDetails.setSpecialization(newdoctor.getSpecialization());
                    }
                    if (Objects.nonNull(newdoctor.getDepartment()) && !"".equalsIgnoreCase(newdoctor.getDepartment())) {
                        updatedDoctorDetails.setDepartment(newdoctor.getDepartment());
                    }
                    Set<Patient> patientSet = updatedDoctorDetails.getPatients();
                    newdoctor.getPatients().stream().forEach(patient -> { patientSet.add(patient); });
                    updatedDoctorDetails.setPatients(patientSet);
                    log.info("doctor details updated for id : "+ doctorId);
                    return doctorRepository.save(updatedDoctorDetails);
                }).orElseThrow(() -> {
                    log.error("Doctor not found with ID : "+ doctorId);
                    return new NotFoundException("Doctor not found with id: " + doctorId);
                });
    }

    @Override
    public void deleteDoctorDetailsById(Long id) {
        Doctor doctorDetails = doctorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Doctor not found with ID : "+ id);
                    return new NotFoundException("Doctor not found with id: " +id);
                }) ;
        doctorRepository.delete(doctorDetails);
    }

    @Override
    public Set<Patient> fetchPatientListByDoctorId(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Doctor not found with ID : "+ id);
                    return new NotFoundException("Doctor not found with id: " +id);
                }).getPatients() ;
    }
}
