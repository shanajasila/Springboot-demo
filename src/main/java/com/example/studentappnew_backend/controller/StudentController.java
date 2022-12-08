package com.example.studentappnew_backend.controller;

import com.example.studentappnew_backend.dao.StudentDao;
import com.example.studentappnew_backend.model.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentDao dao;
    @CrossOrigin(origins = "*")
    @GetMapping("/view")
    public List<Students> viewpage(){
        return(List<Students>)dao.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/add",consumes = "application/json",produces = "application/json")
    public String studentadd(@RequestBody Students s){
        System.out.println(s.getName().toString());
        System.out.println(s.getAdmno());
        System.out.println(s.getRollno());
        System.out.println(s.getCollege());;
        dao.save(s);
        return "student add successfully";
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/search",consumes = "application/json",produces = "application/json")
    public List<Students> studentsearch(@RequestBody Students s){
        String rollno=String.valueOf(s.getRollno());
        System.out.println(rollno);
        dao.searchStudents(s.getRollno());
        return(List<Students>) dao.searchStudents(s.getRollno());
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/delete",consumes = "application/json",produces = "application/json")
    public HashMap<String,String> delete(@RequestBody Students d){
        String id=String.valueOf(d.getId());
        System.out.println(id);
        dao.deleteStudents(d.getId());
        HashMap<String,String>map=new HashMap<>();
        map.put("status","success");
        return map;
    }

}
