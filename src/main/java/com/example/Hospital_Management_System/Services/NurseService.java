package com.example.Hospital_Management_System.Services;

import com.example.Hospital_Management_System.Nurse;
import com.example.Hospital_Management_System.Repository.NurseRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;import java.util.List;



@Service
public class NurseService
{

    NurseRepository nurseRepository=new NurseRepository();

  public String addNurse(Nurse nurse) {
        if(nurse.getNurseId()<0)
        {
            return "enter a valid nurse Id";
        }
        if(nurse.getName().equals(null))
        {
            return "Name Should not be equal to null";

        }
        String ans=nurseRepository.addNurse(nurse);
        return ans;

    }

    public List<Nurse> getList(int age)
    {
        List<Nurse> nurseList = nurseRepository.getAllNurses();

        List<Nurse> res=new ArrayList<>();

        for(Nurse obj: nurseList)
        {
            if(obj.getAge()>age)
            {
                res.add(obj);
            }
        }
        return res;
    }
    public List<Nurse> getNursesWithQualificaiton(String qualification){

        List<Nurse> nurseList = nurseRepository.getAllNurses();

        //Logic to filter based on qualification

        List<Nurse> nurses = new ArrayList<>();
        for(Nurse nurse : nurseList){

            if (nurse.getQualification().equals(qualification)) {
                nurses.add(nurse);
            }
        }
        return nurses;
    }

}
