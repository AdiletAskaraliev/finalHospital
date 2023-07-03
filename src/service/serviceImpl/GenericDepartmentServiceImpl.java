package service.serviceImpl;

import db.Database;
import model.Department;
import model.Hospital;
import service.GenericService;

import javax.swing.*;
import java.util.List;

public class GenericDepartmentServiceImpl implements GenericService<Department> {

    private Database database;

    public GenericDepartmentServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public String add(Long hospitalId, Department department) {
        List<Hospital> hospitals = database.getHospitals();

        hospitals.stream()
                .filter(hospital -> hospital.getId() == hospitalId)
                .forEach(hospital -> hospital.setDepartment(department));
        return "Added" + department.toString();
    }

    @Override
    public void removeById(Long id) {
        List<Hospital> hospitals = database.getHospitals();

        hospitals.forEach(hospital -> {
            List<Department> departments = hospital.getDepartments();
            departments.removeIf(department -> department.getId() == id);
        });
        System.out.println("Deleted");
    }

    @Override
    public String updateById(Long id, Department department) {
        List<Hospital> hospitals = database.getHospitals();

        hospitals.forEach(hospital -> {
            List<Department> departments = hospital.getDepartments();
            for (Department d : departments) {
                if (d.getId() == id){
                    d.setDepartmentName(department.getDepartmentName());
                    d.setDoctors(department.getDoctors());
                    break;
                }
            }
        });
        return "Updated!!! " + department.toString();
    }
}
