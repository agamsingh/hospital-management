package com.international_sos.hospital_management.controller;

import com.international_sos.hospital_management.entity.Patient;
import com.international_sos.hospital_management.service.PatientService;
import com.international_sos.hospital_management.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("hospital")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public Response<List<Patient>> fetchPatients() {
       return new Response<>(patientService.fetchAllPatient(), HttpStatus.FOUND);
    }

    @GetMapping("/patient/{id}")
    public Response<Patient> fetchPatientById(@PathVariable("id") Long id) {
        return new Response<>(patientService.fetchPatientById(id), HttpStatus.FOUND);
    }

    @PutMapping("/patient/{id}")
    public Response<Patient> updateDoctor(@Valid @RequestBody Patient patient, @PathVariable("id") Long patientId) {
        return new Response<>(patientService.updatePatientDetails(patient, patientId), HttpStatus.CREATED);
    }

    @DeleteMapping("/patient/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatientDetailsByID(id);
    }
}
