package service.serviceImpl;

import db.Database;
import model.Hospital;
import model.Patient;
import service.GenericService;

import java.util.List;

public class GenericPatientServiceImpl implements GenericService<Patient> {

    private Database database;

    public GenericPatientServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        List<Hospital> hospitals = database.getHospitals();

        hospitals.stream()
                .filter(hospital -> hospital.getId() == hospitalId)
                .forEach(hospital -> hospital.setPatient(patient));
        return "Added " + patient.toString();
    }

    @Override
    public void removeById(Long id) {
        List<Hospital> hospitals = database.getHospitals();
        hospitals.forEach(hospital -> {
            List<Patient> patients = hospital.getPatients();
            patients.removeIf(patient -> patient.getId() == id);
        });

        System.out.println("Deleted!!!");
    }

    @Override
    public String updateById(Long id, Patient patient) {
        List<Hospital> hospitals = database.getHospitals();

        hospitals.forEach(hospital -> {
            List<Patient> patients = hospital.getPatients();
            for (Patient p : patients) {
                if (p.getId() == id){
                    p.setFirstName(patient.getFirstName());
                    p.setLastName(patient.getLastName());
                    p.setAge(patient.getAge());
                    p.setGender(patient.getGender());
                    break;
                }
            }
        });
        return "Updated!!! " + patient.toString();
    }
}
