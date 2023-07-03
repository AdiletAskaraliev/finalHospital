package service.serviceImpl;

import db.Database;
import model.Department;
import model.Hospital;
import service.DepartmentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DepartmentServiceImpl implements DepartmentService {

    private Database database;

    public DepartmentServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        List<Hospital> hospitals = database.getHospitals();
        return hospitals.stream()
                .filter(hospital -> hospital.getId()==id)
                .flatMap(hospital -> hospital.getDepartments().stream())
                .collect(Collectors.toList());
    }

    @Override
    public Department findDepartmentByName(String name) {
        List<Hospital> hospitals = database.getHospitals();
        for (Hospital hospital : hospitals) {
            Optional<Department> department = hospital.getDepartments()
                    .stream()
                    .filter(d -> d.getDepartmentName().equals(name))
                    .findFirst();

            if (department.isPresent()) {
                return department.get();
            }
        }

        return null;

    }
}
