package com.example.java3n_crud.controller;

import com.example.java3n_crud.entity.Student;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

//@WebServlet(name = "StudentServlet", value = "/StudentServlet/*")
@WebServlet(name = "StudentServlet", value = {
        "/students",
        "/students/new",
        "/students/delete",
        "/students/edit",
        "/students/update"
})
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        System.out.println(path);
        switch (path) {
            case "/students":
                listStudents(request, response);
                break;
            case "/students/new":
                insertStudent(request, response);
                break;
            case "/students/delete":
                deleteStudent(request, response);
                break;
            case "/students/edit":
                editStudent(request, response);
                break;
            case "/students/update":
                updateStudent(request, response);
                break;
        }
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) {
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response) {
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) {

    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response) {

    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //data: students
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(1001L, "student 1","email 1", "phone 1"));
        students.add(new Student(1002L, "student 2","email 2", "phone 2"));
        students.add(new Student(1003L, "student 3","email 3", "phone 3"));
        // => view: studentList.jsp
        request.setAttribute("students", students);
        request.getRequestDispatcher("/view/studentList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
