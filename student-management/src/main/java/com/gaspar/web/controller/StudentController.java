package com.gaspar.web.controller;

import com.gaspar.web.entity.Student;
import com.gaspar.web.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    private final StudentService studentService;


    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String ListStudents(Model model){
        model.addAttribute("students",studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/new")
    public String createStudentForm(Student student){
        return "create_student";
    }

    @PostMapping("/students")
    public String saveStudent(Student student){
        studentService.saveStudent(student);
        return "redirect:/students";
    }
}
