package com.international_sos.hospital_management.controller;

import com.international_sos.hospital_management.entity.Doctor;
import com.international_sos.hospital_management.entity.Patient;
import com.international_sos.hospital_management.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/hospital")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/doctor")
    public ResponseEntity<Doctor> SaveDoctor(@Valid @RequestBody Doctor doctor) {
            return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.persistDoctor(doctor));
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> fetchDoctors() {
        return ResponseEntity.status(HttpStatus.FOUND).body(doctorService.fetchAllDoctors());
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<Doctor> fetchDoctorById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(doctorService.fetchDoctorDetailsById(id));
    }

    @GetMapping("/patient_by_doctor_id/{id}")
    public ResponseEntity<Set<Patient>> fetchPatientListByDoctorId(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(doctorService.fetchPatientListByDoctorId(id));
    }

    @PutMapping("/doctor/{id}")
    public ResponseEntity<Doctor> updateDoctor(@Valid @RequestBody Doctor doctor, @PathVariable("id") Long doctorId) {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.updateDoctorDetails(doctor, doctorId));
    }

    @DeleteMapping("/doctor/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctorDetailsById(id);
    }
}
