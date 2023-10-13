package com.sivakg2000.learning.springhelloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    @GetMapping("/student")
    public List<Student> getAllStudent(){
        return Arrays.asList(new Student(1,"Siva","3"),new Student(2,"Pragatheeswaran","2"),new Student(3,"Pranav","PlayGroup"));
    }
}
