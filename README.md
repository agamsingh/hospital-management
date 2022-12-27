Hospital Management System


API Implented for CRUD operations for doctor and patient.

POST Api "hospital/doctor" - to create doctor entity.
{
 "name": "test_name",
 "degree": "test_degree",
 "email": "test@gmail.com",
 "specialization": "test_speciality",
 "department": "test_dept",
 "patients": [
    {
        "name": "patient1_test_name",
        "email": "patient1_test@gmail.com",
        "age": 10,
        "contactNo": "29201001829"
     }
   ]
}

Get "hospital/doctor" - to get all doctors
Get "hospital/doctor/{id}" -  to get doctor by id
Get "hospital/patient_by_doctor_id/{id}" - to get patient by doctor ID
Put "/doctor/{id}" - to update doctor/add patients
Delete "hospital/doctor/{id}" - to delete doctor by id and corresponding patients list(one to Many relationship)

Get "hospital/patients" - to get all patients
Get "hospital//patient/{id}" - to get patient by id
Put "hospital/patient/{id}"  - to update patient entity
Delete "hospital/patient/{id}" - to delete patient by id

