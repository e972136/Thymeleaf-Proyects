package com.gaspar.web.service;

import com.gaspar.web.entity.Student;
import com.gaspar.web.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService{

    private final StudentRepository studentRepository;

    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student updateStudent(Student toUpdate) {
        return studentRepository.save(toUpdate);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.deleteById(id);
    }
}
