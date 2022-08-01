package com.gaspar.web.controller;

import com.gaspar.web.entity.Student;
import com.gaspar.web.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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
    public String saveStudent(@Valid Student student, Errors error){
        if(error.hasErrors()){
            System.out.println("hay error" + error.toString());
            return "create_student";
        }
        studentService.saveStudent(student);
        return "redirect:/students";
    }


    @GetMapping("/students/edit/{id}")
    public String editStudenForm(@PathVariable Integer id, Model model){
        Student toUpdate = studentService.getById(id);
        model.addAttribute("student",toUpdate);
//        studentService.updateStudent(toUpdate);
        return "edit_student";
    }

    @PostMapping("/students/update")
    public String updateStudent(Student student){
        studentService.updateStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(Student student){

        studentService.deleteStudent(student.getId());
        return "redirect:/students";
    }
}
