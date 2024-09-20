package com.example.java3n_crud.controller;

import com.example.java3n_crud.entity.Student;
import com.example.java3n_crud.service.StudentService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

//@WebServlet(name = "StudentServlet", value = "/StudentServlet/*")
@WebServlet(name = "StudentServlet", value = {
        "/students",
        "/students/new",
        "/students/insert",
        "/students/delete",
        "/students/edit",
        "/students/update"
})
public class StudentServlet extends HttpServlet {

    private StudentService service = new StudentService();

    //private  void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //    String path = request.getServletPath();
    //    System.out.println(path);
    //    switch (path) {
    //        case "/students":
    //            listStudents(request, response);
    //            break;
    //        case "/students/new":
    //            showNewForm(request, response);
    //            break;
    //        case "/students/insert":
    //            insertStudent(request, response);
    //            break;
    //        case "/students/delete":
    //            deleteStudent(request, response);
    //            break;
    //        case "/students/edit":
    //            editStudent(request, response);
    //            break;
    //        case "/students/update":
    //            updateStudent(request, response);
    //            break;
    //    }
    //}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //processRequest(request, response);
        String path = request.getServletPath();
        System.out.println(path);
        switch (path) {
            case "/students":
                listStudents(request, response);
                break;
            case "/students/new":
                showNewForm(request, response);
                break;
            case "/students/insert":
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        //processRequest(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext()
                .getRequestDispatcher("/view/addStudentForm.jsp")
                .forward(request, response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) {
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response) {
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        service.deleteStudent(id);

        response.sendRedirect("/students");
    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // read student info from form
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        // create student object
        Student student = new Student(id, name, email, phone);

        // save student to list
        service.addStudent(student);

        // redirect to list students
        response.sendRedirect("/students");
        //listStudents(request, response);
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //data: students
        ArrayList<Student> students = new ArrayList<>();
        //students.add(new Student(1001L, "student 1","email 1", "phone 1"));
        //students.add(new Student(1002L, "student 2","email 2", "phone 2"));
        //students.add(new Student(1003L, "student 3","email 3", "phone 3"));

        students = service.getStudents();
        // => view: studentList.jsp
        request.setAttribute("students", students);
        request.getRequestDispatcher("/view/studentList.jsp").forward(request, response);
    }

}
