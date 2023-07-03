package service.serviceImpl;

import db.Database;
import model.Department;
import model.Doctor;
import model.Hospital;
import service.DoctorService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DoctorServiceImpl implements DoctorService {

    private Database database;

    public DoctorServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        List<Hospital> hospitals = database.getHospitals();
        for (Hospital hospital : hospitals) {
            Optional<Doctor> doctor = hospital.getDoctors()
                    .stream()
                    .filter(d -> d.getId().equals(id))
                    .findFirst();

            if (doctor.isPresent()) {
                return doctor.get();
            }
        }

        return null;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        List<Hospital> hospitals = database.getHospitals();

        Optional<Department> optionalDepartment = hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(department -> department.getId()==departmentId)
                .findFirst();

        if (optionalDepartment.isPresent()){
            Department department = optionalDepartment.get();

            List<Doctor> assignedDoctors = department.getDoctors().stream()
                    .filter(doctor -> doctorsId.contains(doctor.getId())).toList();

            department.getDoctors().addAll(assignedDoctors);
            return "Doctors assigned to the department successfully.";
        } else {
            return "Department not found.";
        }

    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        List<Hospital> hospitals = database.getHospitals();
        return hospitals.stream()
                .filter(hospital -> hospital.getId()==id)
                .flatMap(hospital -> hospital.getDoctors().stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        List<Hospital> hospitals = database.getHospitals();
        return hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(department -> department.getId()==id)
                .flatMap(department -> department.getDoctors().stream())
                .collect(Collectors.toList());

    }
}
