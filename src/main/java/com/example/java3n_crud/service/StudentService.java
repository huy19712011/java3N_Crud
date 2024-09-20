package com.example.java3n_crud.service;

import com.example.java3n_crud.entity.Student;
import com.example.java3n_crud.repository.StudentDao;

import java.util.ArrayList;

public class StudentService {

    private StudentDao studentDao = new StudentDao();

    public ArrayList<Student> getStudents() {

        return studentDao.getStudents();
    }
}
