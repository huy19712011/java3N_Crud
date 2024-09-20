package com.example.java3n_crud.repository;

import com.example.java3n_crud.entity.Student;

import java.util.ArrayList;

public class StudentDao {

    private static ArrayList<Student> students = new ArrayList<>();
    static {
        students.add(new Student(1001L, "student 1","email 1", "phone 1"));
        students.add(new Student(1002L, "student 2","email 2", "phone 2"));
        students.add(new Student(1003L, "student 3","email 3", "phone 3"));
    }


    public ArrayList<Student> getStudents() {

        //ArrayList<Student> students = new ArrayList<>();
        //students.add(new Student(1001L, "student 1","email 1", "phone 1"));
        //students.add(new Student(1002L, "student 2","email 2", "phone 2"));
        //students.add(new Student(1003L, "student 3","email 3", "phone 3"));

        return students;
    }

    public void deleteStudent(int id) {

        students.removeIf(student -> student.getId() == id);
    }
}
