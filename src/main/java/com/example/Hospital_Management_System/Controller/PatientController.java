package com.example.Hospital_Management_System.Controller;

import com.example.Hospital_Management_System.Patient;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;import java.util.Map;
@RestController
@RequestMapping("/patient")
public class PatientController
{

  HashMap<Integer, Patient> patientDb = new HashMap<>();

    @PostMapping("/addPatientViaParamaters")
    public String addPatient(@RequestParam("patientId")Integer patientId,@RequestParam("name")String name,
                             @RequestParam("age")Integer age,@RequestParam("disease")String disease){
        Patient obj=new Patient(patientId,name,disease,age);

        patientDb.put(patientId,obj); // adding patient to the local db

        return "Patient added Successfully";

    }

    @PostMapping("/add")
    public String addPatient(@RequestBody Patient obj)
    {
        int key=obj.getPatientId();

        patientDb.put(key,obj);

        return "Patient Added Succesfully by Request Body";
    }
//    @GetMapping("/getPatientInfo")
//    public Patient getPatientInfo(@RequestParam("patientId")Integer patientId)
//    {
//        Patient obj=patientDb.get(patientId);
//
//        return obj;
//    }
    @GetMapping("/getInfoViaPathVariable/{patiendId}")
    public Patient getPatientInfo(@PathVariable("patientId")Integer patientId)
    {
        Patient patient=patientDb.get(patientId);
        return patient;
    }
    @GetMapping("/getPatients/{age}/{disease}")
    public List<Patient> getData(@PathVariable("age")Integer age,@PathVariable("disease")String disease)
    {
        List<Patient> ans=new ArrayList<>();
        for(Map.Entry<Integer,Patient> map : patientDb.entrySet())
        {
            Patient p=map.getValue();
            if(p.getAge()>age && p.getDisease().equals(disease))
            {
                ans.add(p);
            }
        }
        return ans;
    }
    @PutMapping("/update")
    public String updatePatientDetails(@RequestBody Patient patient)
    {
        int key=patient.getPatientId();
        if(patientDb.containsKey(key))
        {
        patientDb.put(key,patient);
        return "patient added successfully";

        }
        return "patient does not exist";

    }
    @DeleteMapping("/delete")
    public String deletePatient(@RequestParam("patientId")Integer patientId)
    {
        patientDb.remove(patientId);

        return "patient deleted successfully";
    }
    @GetMapping("/getAll")
    public List<Patient> getAllPatients()
    {
        List<Patient> ls=new ArrayList<Patient>();

        for(Patient p : patientDb.values())
        {
            ls.add(p);
        }
        return ls;
    }

    @GetMapping("/getByName")
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
    @PutMapping("/updateDisease")
    public String updateDisease(@RequestParam("patientId")Integer patientId,@RequestParam("disease") String disease)
    {
        if(patientDb.containsKey(patientId))
        {
            Patient patient=patientDb.get(patientId);
            patient.setDisease(disease);
            patientDb.put(patientId,patient);
            return "patient added SUccessfully";
        }
        else
        {
            return "Patient Does Not Exist";
        }
    }




}
