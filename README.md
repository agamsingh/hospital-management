# Hospital Management System

Hospital Management system handles all patient and doctor data. In this project we have implented API for CRUD operations for doctor and patient.

## Run the System

Navigate through the project directory that is **../hospital-management** and run below commands

We can easily run the whole with only a single command:
```bash
docker-compose up
```

Docker will pull the MySQL and Spring Boot images (if our machine does not have it before).

The services can be run on the background with command:
```bash
docker-compose up -d
```

## Stop the System
Stopping all the running containers is also simple with a single command:
```bash
docker-compose down
```

If you need to stop and remove all containers, networks, and all images used by any service in <em>docker-compose.yml</em> file, use the command:
```bash
docker-compose down --rmi all
```
**Validations** -

**email**  - should be valid and contains special character ('@')\
**contact number** - should be valid numeric

### Doctor's API:
**POST Api "http://localhost:8080/hospital/doctor"** - to create doctor entity.
```json
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
```
```bash
Note: Please create data beofre executing det and put request.
```

###### Read and update
**Get "http://localhost:8080/hospital/doctor"** - to get all doctors\
**Get "http://localhost:8080/hospital/doctor/{id}"** -  to get doctor by id\
**Get "http://localhost:8080/hospital/patient_by_doctor_id/{id}"** - to get patient by doctor ID\
**Put "http://localhost:8080//doctor/{id}"** - to update doctor/add patients\
**Delete "http://localhost:8080/hospital/doctor/{id}"** - to delete doctor by id and corresponding patients list(one to Many relationship)

### Patient's API:
```bash
Note: Creation of patient will be through doctor API as it is Many to one unidirectional relationship.
```
###### Read and update
**Get "http://localhost:8080/hospital/patients"** - to get all patients\
**Get "http://localhost:8080/hospital//patient/{id}"** - to get patient by id\
**Put "http://localhost:8080/hospital/patient/{id}"**  - to update patient entity\
**Delete "http://localhost:8080/hospital/patient/{id}"** - to delete patient by id

