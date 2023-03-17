package com.example.Hospital_Management_System;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@RestController
public class PatientController
{

    HashMap<Integer,Patient> patientDb =new HashMap<>();

    @PostMapping("/addPatientViaParamaters")
    public String addPatient(@RequestParam("patientId")Integer patientId,@RequestParam("name")String name,
                             @RequestParam("age")Integer age,@RequestParam("disease")String disease){
        Patient obj=new Patient(patientId,name,disease,age);

        patientDb.put(patientId,obj); // adding patient to the local db

        return "Patient added Successfully";

    }

    @PostMapping("/addPatientViaRequestBody")
    public String addPatient(@RequestBody Patient obj)
    {
        int key=obj.getPatientId();

        patientDb.put(key,obj);

        return "Patient Added Succesfully by Request Body";
    }
    @GetMapping("/getPatientInfo")
    public Patient getPatientInfo(@RequestParam("patientId")Integer patientId)
    {
        Patient obj=patientDb.get(patientId);

        return obj;
    }
    @GetMapping("/getAllPatients")
    public List<Patient> getAllPatients()
    {
        List<Patient> ls=new ArrayList<Patient>();

        for(Patient p : patientDb.values())
        {
            ls.add(p);
        }
        return ls;
    }

    @GetMapping("/getPatientByName")
    public Patient getPatientByName(@RequestParam("name")String name){

        for(Patient p: patientDb.values()){

            if(p.getName().equals(name)){
                return p;
            }
        }

        return null;
    }

    @GetMapping("/getPatientsListGreaterThanAge")
    public List<Patient> getPatientsGreaterThanAge(@RequestParam("age")Integer age){

        List<Patient> patients = new ArrayList<>();

        for(Patient p:patientDb.values()){

            if(p.getAge()>age){
                patients.add(p);
            }
        }
        return patients;
    }

}
