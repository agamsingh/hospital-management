package com.international_sos.hospital_management.controller;

import com.international_sos.hospital_management.entity.Doctor;
import com.international_sos.hospital_management.entity.Patient;
import com.international_sos.hospital_management.service.DoctorService;
import com.international_sos.hospital_management.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Response<Doctor> SaveDoctor(@Valid @RequestBody Doctor doctor) {
            return new Response<>(doctorService.persistDoctor(doctor), HttpStatus.CREATED);
    }

    @GetMapping("/doctors")
    public Response<List<Doctor>> fetchDoctors() {
        return new Response<>(doctorService.fetchAllDoctors(), HttpStatus.FOUND);
    }

    @GetMapping("/doctor/{id}")
    public Response<Doctor> fetchDoctorById(@PathVariable("id") Long id) {
        return new Response<>(doctorService.fetchDoctorDetailsById(id), HttpStatus.FOUND);
    }

    @GetMapping("/patient_by_doctor_id/{id}")
    public Response<Set<Patient>> fetchPatientListByDoctorId(@PathVariable("id") Long id) {
        return new Response<>(doctorService.fetchPatientListByDoctorId(id), HttpStatus.FOUND );
    }

    @PutMapping("/doctor/{id}")
    public Response<Doctor> updateDoctor(@Valid @RequestBody Doctor doctor, @PathVariable("id") Long doctorId) {
        return new Response<>(doctorService.updateDoctorDetails(doctor, doctorId), HttpStatus.CREATED );
    }

    @DeleteMapping("/doctor/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctorDetailsById(id);
    }
}
