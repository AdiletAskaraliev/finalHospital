import db.Database;
import enums.Gender;
import model.Department;
import model.Doctor;
import model.Hospital;
import model.Patient;
import service.*;
import service.serviceImpl.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Hospital> hospitals = new ArrayList<>();
        Database database = new Database(hospitals);
        HospitalService hospitalService = new HospitalServiceImpl(database);
        GenericService<Department> genericServiceDep = new GenericDepartmentServiceImpl(database);
        GenericService<Doctor> genericServiceDoc = new GenericDoctorServiceImpl(database);
        GenericService<Patient> patientGenericService = new GenericPatientServiceImpl(database);
        DepartmentService departmentService = new DepartmentServiceImpl(database);
        DoctorService doctorService = new DoctorServiceImpl(database);
        PatientService patientService = new PatientServiceImpl(database);

        while (true){
            int san = new Scanner(System.in).nextInt();
            switch (san){
                case 1:
                    System.out.println(hospitalService.addHospital(new Hospital(1L, "Samarkandek Hospital", "Batken")));
                    System.out.println(hospitalService.addHospital(new Hospital(2L, "Lubeck Hospital", "Germany")));
                    System.out.println(hospitalService.addHospital(new Hospital(3L, "Archa-Beshik Hospital", "Bishkek")));
                    break;
                case 2:
                    System.out.println(hospitalService.getAllHospital());
                    break;
                case 3:
                    System.out.println(hospitalService.findHospitalById(2L));
                    break;
                case 4:
                    System.out.println(hospitalService.getAllHospitalByAddress("Batken"));
                    break;
                case 5:
                    System.out.println(hospitalService.getAllPatientFromHospital(3L));
                    break;
                case 6:
                    System.out.println(hospitalService.deleteHospitalById(2L));
                    break;
                case 7:
                    System.out.println(genericServiceDep.add(1L, new Department(11L, "Terapevt")));
                    System.out.println(genericServiceDep.add(1L, new Department(12L, "Hirurg")));
                    System.out.println(genericServiceDep.add(1L, new Department(13L, "Pediatr")));
                    System.out.println(genericServiceDep.add(2L, new Department(14L, "Stom")));
                    System.out.println(genericServiceDep.add(2L, new Department(15L, "Lor")));
                    System.out.println(genericServiceDep.add(2L, new Department(16L, "Tramvmatolog")));
                    System.out.println(genericServiceDep.add(3L, new Department(17L, "Okulist")));
                    System.out.println(genericServiceDep.add(3L, new Department(18L, "Psihiatr")));
                    System.out.println(genericServiceDep.add(3L, new Department(19L, "Endokrinolog")));
                    break;
                case 8:
                    System.out.println(genericServiceDep.updateById(17L, new Department("Oftolmolog")));
                    break;
                case 9:
                    genericServiceDep.removeById(15L);
                    break;
                case 10:
                    System.out.println(genericServiceDoc.add(1L, new Doctor(21L, "Asan", "Asanov", Gender.MALE, 7)));
                    System.out.println(genericServiceDoc.add(1L, new Doctor(22L, "Uson", "Usonov", Gender.MALE, 4)));
                    System.out.println(genericServiceDoc.add(1L, new Doctor(23L, "Asel", "Askat", Gender.FEMALE, 2)));
                    System.out.println(genericServiceDoc.add(2L, new Doctor(24L, "Ivan", "Ivanov", Gender.MALE, 5)));
                    System.out.println(genericServiceDoc.add(2L, new Doctor(25L, "Askat", "Bekberdinov", Gender.MALE, 7)));
                    System.out.println(genericServiceDoc.add(2L, new Doctor(26L, "Fatima", "Bekbolotova", Gender.FEMALE, 8)));
                    System.out.println(genericServiceDoc.add(3L, new Doctor(27L, "Zuhra", "Asanova", Gender.FEMALE, 3)));
                    System.out.println(genericServiceDoc.add(3L, new Doctor(28L, "Bek", "Asanov", Gender.MALE, 10)));
                    System.out.println(genericServiceDoc.add(3L, new Doctor(29L, "Ali", "Asanov", Gender.MALE, 9)));
                    break;
                case 11:
                    System.out.println(genericServiceDoc.updateById(25L, new Doctor("Alex", "Morgan", Gender.MALE, 17)));
                    break;
                case 12:
                    genericServiceDoc.removeById(29L);
                    break;
                case 13:
                    System.out.println(patientGenericService.add(1L, new Patient(31L, "Cristiano", "Ronaldo", 38, Gender.MALE)));
                    System.out.println(patientGenericService.add(1L, new Patient(32L, "Cristiano", "Ronaldo", 38, Gender.MALE)));
                    System.out.println(patientGenericService.add(1L, new Patient(33L, "Killian", "Mbappe", 24, Gender.MALE)));
                    System.out.println(patientGenericService.add(2L, new Patient(34L, "Cristiano", "Ronaldo", 38, Gender.MALE)));
                    System.out.println(patientGenericService.add(2L, new Patient(35L, "Neymar", "Junior", 31, Gender.MALE)));
                    System.out.println(patientGenericService.add(2L, new Patient(36L, "Cristiano", "Ronaldo", 38, Gender.MALE)));
                    System.out.println(patientGenericService.add(3L, new Patient(37L, "Cristiano", "Ronaldo", 38, Gender.MALE)));
                    System.out.println(patientGenericService.add(3L, new Patient(38L, "Cristiano", "Ronaldo", 38, Gender.MALE)));
                    System.out.println(patientGenericService.add(3L, new Patient(39L, "Cristiano", "Ronaldo", 38, Gender.MALE)));
                    break;
                case 14:
                    System.out.println(patientGenericService.updateById(37L, new Patient("Leo", "Messi", 36, Gender.MALE)));
                    break;
                case 15:
                    patientGenericService.removeById(35L);
                    break;
                case 16:
                    System.out.println(departmentService.findDepartmentByName("Pediatr"));
                    break;
                case 17:
                    System.out.println(departmentService.getAllDepartmentByHospital(2L));
                    break;
                case 18:
                    List<Long> docsId = new ArrayList<>(List.of(21L, 22L, 23L));
                    System.out.println(doctorService.assignDoctorToDepartment(17L, docsId));
                    break;
                case 19:
                    System.out.println(doctorService.getAllDoctorsByHospitalId(3L));
                    break;
                case 20:
                    System.out.println(doctorService.getAllDoctorsByDepartmentId(17L));
                    break;
                case 21:
                    System.out.println(doctorService.findDoctorById(26L));
                    break;
                case 22:
                    List<Patient> patients1 = new ArrayList<>(List.of(new Patient(31L, "Cristiano", "Ronaldo", 38, Gender.MALE),
                            new Patient(32L, "Cristiano", "Ronaldo", 38, Gender.MALE),
                            new Patient(33L, "Killian", "Mbappe", 24, Gender.MALE)));
                    List<Patient> patients2 = new ArrayList<>(List.of(new Patient(34L, "Cristiano", "Ronaldo", 38, Gender.MALE),
                            new Patient(35L, "Neymar", "Junior", 31, Gender.MALE),
                            new Patient(36L, "Cristiano", "Ronaldo", 38, Gender.MALE) ));
                    List<Patient> patients3 = new ArrayList<>(List.of(new Patient(37L, "Cristiano", "Ronaldo", 38, Gender.MALE),
                            new Patient(38L, "Cristiano", "Ronaldo", 38, Gender.MALE),
                            new Patient(39L, "Cristiano", "Ronaldo", 38, Gender.MALE) ));
                    System.out.println(patientService.addPatientsToHospital(1L,patients1));
                    System.out.println(patientService.addPatientsToHospital(1L,patients2));
                    System.out.println(patientService.addPatientsToHospital(1L,patients3));
                    break;
                case 23:
                    System.out.println(patientService.getPatientById(33L));
                    break;
                case 24:
                    System.out.println(patientService.getPatientByAge());
                    break;
                case 25:
                    System.out.println(patientService.sortPatientsByAge("desc"));
            }
        }

    }
}