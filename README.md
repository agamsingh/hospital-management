Hospital Management System


POST Api "/hospital/doctor" - to create doctor entity.

{
"name": "test",
"degree": "test_degree",
"email": "test@gmail.com",
"specialization": "test_speciality",
"department": "test_dept"
}

POST Api "hospital/patient"

{
"name": "test_name",
"email": "test@“gmail.com",
"age": 10,
"contact_no": "2920100182",
"doctor": {
"name": "test_name",
"degree": "test_degree",
"email": "test@gmail.com",
"specialization": "test_speciality",
"department": "test_dept"
}
}

