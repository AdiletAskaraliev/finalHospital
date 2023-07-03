package service.serviceImpl;

import db.Database;
import model.Hospital;
import model.Patient;
import service.PatientService;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientServiceImpl implements PatientService {

    private Database database;

    public PatientServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        List<Hospital> hospitals = database.getHospitals();
        hospitals.stream()
                .filter(hospital -> hospital.getId()==id)
                .forEach(hospital -> hospital.setPatients(patients));
        return "Successfully added" + patients.toString();
    }

    @Override
    public Patient getPatientById(Long id) {
        List<Hospital> hospitals = database.getHospitals();
        return hospitals.stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .filter(patient -> patient.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Map<Integer, Patient> getPatientByAge() {
        List<Hospital> hospitals = database.getHospitals();
        Map<Integer, Patient> patientByAge = new HashMap<>();

        hospitals.stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .forEach(patient -> patientByAge.put(patient.getAge(), patient));

        return patientByAge;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        List<Hospital> hospitals = database.getHospitals();

        List<Patient> patients = hospitals.stream()
                .flatMap(hospital -> hospital.getPatients().stream()).toList();

        Comparator<Patient> comparator = Comparator.comparing(Patient::getAge);

        if (ascOrDesc.equalsIgnoreCase("desc")){
            comparator = comparator.reversed();
        }
        patients.sort(comparator);

        return patients;
    }
}
