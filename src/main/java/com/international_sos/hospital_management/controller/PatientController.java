package com.international_sos.hospital_management.controller;

import com.international_sos.hospital_management.entity.Patient;
import com.international_sos.hospital_management.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("hospital")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/patient")
    public Patient SaveDoctor(@Valid @RequestBody Patient patient) {
        return patientService.persistPatient(patient);
    }

    @GetMapping("/patients")
    public List<Patient> fetchPatients() {
       return patientService.fetchAllPatient();
    }


    @GetMapping("/patient/{id}")
    public Patient fetchPatientById(@PathVariable("id") Long id) {
        return patientService.fetchPatientById(id);
    }

    @PutMapping("/patient/{id}")
    public Patient updateDoctor(@Valid @RequestBody Patient patient, @PathVariable("id") Long patientId) {
        return patientService.updatePatientDetails(patient, patientId);
    }

    @DeleteMapping("/patient/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatientDetailsByID(id);
    }
}
