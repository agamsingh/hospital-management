package com.international_sos.hospital_management.controller;

import com.international_sos.hospital_management.entity.Patient;
import com.international_sos.hospital_management.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hospital")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> fetchPatients() {
       return ResponseEntity.status(HttpStatus.FOUND).body(patientService.fetchAllPatient());
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<Patient> fetchPatientById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(patientService.fetchPatientById(id));
    }

    @PutMapping("/patient/{id}")
    public ResponseEntity<Patient> updateDoctor(@Valid @RequestBody Patient patient, @PathVariable("id") Long patientId) {
        return ResponseEntity.status(HttpStatus.OK).body(patientService.updatePatientDetails(patient, patientId));
    }

    @DeleteMapping("/patient/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatientDetailsByID(id);
    }
}
