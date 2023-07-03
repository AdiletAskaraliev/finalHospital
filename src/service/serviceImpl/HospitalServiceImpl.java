package service.serviceImpl;

import db.Database;
import model.Hospital;
import model.Patient;
import service.HospitalService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HospitalServiceImpl implements HospitalService {

    private Database database;

    public HospitalServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public String addHospital(Hospital hospital) {
        database.getHospitals().add(hospital);
        return "Saved" + "-" + hospital.toString();
    }

    @Override
    public Hospital findHospitalById(Long id) {
        List<Hospital> hospitals = database.getHospitals();
        return hospitals.stream()
                .filter(hospital -> hospital.getId()==id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return database.getHospitals();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        List<Hospital> hospitals = database.getHospitals();
        return hospitals.stream()
                .filter(hospital -> hospital.getId() == id)
                .flatMap(hospital -> hospital.getPatients().stream())
                .collect(Collectors.toList());
    }

    @Override
    public String deleteHospitalById(Long id) {
        List<Hospital> hospitals = database.getHospitals();
        hospitals.removeIf(hospital -> hospital.getId()==id);
        return "Deleted";
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        List<Hospital> hospitals = database.getHospitals();
        Map<String, Hospital> hospitalByAddress = new HashMap<>();

        for (Hospital hospital : hospitals) {
            if (hospital.getAddress()==address){
                hospitalByAddress.put(hospital.getHospitalName(), hospital);
            }
        }

        return hospitalByAddress;
    }
}
