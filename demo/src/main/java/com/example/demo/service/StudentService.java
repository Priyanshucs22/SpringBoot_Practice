package com.example.demo.service;

import com.example.demo.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public Student addStudent(Student student) {
        students.add(student);
        return student;
    }

    public Student updateStudent(int id, Student newStudent) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.setName(newStudent.getName());
                s.setAge(newStudent.getAge());
                return s;
            }
        }
        return null;
    }

    public String deleteStudent(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                students.remove(s);
                return "Student deleted";
            }
        }
        return "Student not found";
    }
}
