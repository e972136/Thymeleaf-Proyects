package com.gaspar.web.service;

import com.gaspar.web.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getById(Integer id);

    Student updateStudent(Student toUpdate);

    void deleteStudent(Integer id);
}
