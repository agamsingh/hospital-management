package com.international_sos.hospital_management.util;

import com.international_sos.hospital_management.entity.Doctor;
import com.international_sos.hospital_management.entity.Patient;
import com.international_sos.hospital_management.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class HospitalHelper {

    private String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

    private Pattern phoneNumberPattern = Pattern.compile("[0-9]+"); //we can make country specifc regex also
    private Pattern emailPattern = Pattern.compile(emailRegex);

    public void validateDoctorData(Doctor doctor) {
        Map<String , String> errorMap = new HashMap<>();
        if (!Objects.nonNull(doctor.getEmail()) || !validateEmail(doctor.getEmail())){
            errorMap.put("email","Doctor email should be valid");
        }
        if (!Objects.nonNull(doctor.getName()) || "".equalsIgnoreCase(doctor.getName())){
            errorMap.put("name","Doctor name should not be null");
        }
        if (!Objects.nonNull(doctor.getDepartment()) || doctor.getDepartment().isEmpty()){
            errorMap.put("department","Doctor department should not be null");
        }
        if(!errorMap.isEmpty())
            throw new BadRequestException("Some parameters are invalid", errorMap);
    }

    public void validatePatientData(Patient patient) {
        Map<String , String> errorMap = new HashMap<>();
        if (!Objects.nonNull(patient.getEmail()) || !validateEmail(patient.getEmail())){
            errorMap.put("email","Patient email should be valid");
        }
        if (!Objects.nonNull(patient.getName()) || patient.getName().isEmpty()){
            errorMap.put("name","Patient name should not be null");
        }
        if (!Objects.nonNull(patient.getContactNo()) || !validatePhoneNumber(patient.getContactNo())){
            errorMap.put("Contact number","Patient Contact Number should be valid");
        }
        if(!errorMap.isEmpty())
            throw new BadRequestException("Some parameters are invalid", errorMap);
    }

    public boolean validateEmail(String email){
        if(!email.isEmpty())
            return emailPattern.matcher(email).matches();
        else
            return false;
    }

    public boolean validatePhoneNumber(String number){
        if(!number.isEmpty())
            return phoneNumberPattern.matcher(number).matches();
        else
            return false;
    }

}
