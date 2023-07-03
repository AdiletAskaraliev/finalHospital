package service.serviceImpl;

import db.Database;
import model.Doctor;
import model.Hospital;
import service.GenericService;

import java.util.List;

public class GenericDoctorServiceImpl implements GenericService<Doctor> {

    private Database database;

    public GenericDoctorServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        List<Hospital> hospitals = database.getHospitals();
        hospitals.stream()
                .filter(hospital -> hospital.getId() == hospitalId)
                .forEach(hospital -> hospital.setDoctor(doctor));
        return "Added" + doctor.toString();
    }

    @Override
    public void removeById(Long id) {
        List<Hospital> hospitals = database.getHospitals();

        hospitals.forEach(hospital -> {
            List<Doctor> doctors = hospital.getDoctors();
            doctors.removeIf(doctor -> doctor.getId() == id);
        });
        System.out.println("Deleted!!! ");
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        List<Hospital> hospitals = database.getHospitals();

        hospitals.forEach(hospital -> {
            List<Doctor> doctors = hospital.getDoctors();
                for (Doctor doc : doctors) {
                    if (doc.getId() == id){
                        doc.setFirstName(doctor.getFirstName());
                        doc.setLastName(doctor.getLastName());
                        doc.setGender(doctor.getGender());
                        doc.setExperienceYear(doctor.getExperienceYear());
                        break;
                    }
                }
        });

        return "Updated!!! " + doctor.toString();
    }
}
