package com.international_sos.hospital_management.controller;

import com.international_sos.hospital_management.entity.Doctor;
import com.international_sos.hospital_management.entity.Patient;
import com.international_sos.hospital_management.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("hospital")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/doctor")
    public Doctor SaveDoctor(@Valid @RequestBody Doctor doctor) {
        return doctorService.persistDoctor(doctor);
    }

    @GetMapping("/doctors")
    public List<Doctor> fetchDoctors() {
        return doctorService.fetchAllDoctors();
    }

    @GetMapping("/doctor/{id}")
    public Doctor fetchDoctorById(@PathVariable("id") Long id) {
        return doctorService.fetchDoctorDetailsById(id);
    }

    @GetMapping("/patient_by_doctor_id/{id}")
    public Set<Patient> fetchPatientListByDoctorId(@PathVariable("id") Long id) {
        return doctorService.fetchPatientListByDoctorId(id);
    }

    @PutMapping("/doctor/{id}")
    public Doctor updateDoctor(@Valid @RequestBody Doctor doctor, @PathVariable("id") Long doctorId) {
        return doctorService.updateDoctorDetails(doctor, doctorId);
    }

    @DeleteMapping("/doctor/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctorDetailsById(id);
    }
}
