package model;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private Long id;
    private String hospitalName;
    private String address;
    private List<Department> departments;
    private List<Doctor> doctors;
    private List<Patient> patients;

    public Hospital(Long id, String hospitalName, String address) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.address = address;
    }

    public Hospital(Long id, String hospitalName, String address, List<Department> departments, List<Doctor> doctors, List<Patient> patients) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.address = address;
        this.departments = departments;
        this.doctors = doctors;
        this.patients = patients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
    public void setDoctor(Doctor doctor){
        if (this.doctors == null){
            this.doctors = new ArrayList<>();
        }
        doctors.add(doctor);
    }

    public void  setDepartment(Department department){
        if (this.departments == null){
            this.departments = new ArrayList<>();
        }
        departments.add(department);
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void setPatient(Patient patient){
        if (this.patients == null){
            this.patients = new ArrayList<>();
        }
        patients.add(patient);
    }


    @Override
    public String toString() {
        return "Hospital " +
                "id= " + id +
                "\n hospitalName= " + hospitalName +
                "\n address= " + address +
                "\n departments= " + departments +
                "\n doctors= " + doctors +
                "\n patients= " + patients ;
    }
}
