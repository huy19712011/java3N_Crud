package com.example.java3n_crud.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "StudentServlet", value = "/StudentServlet/*")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Hello Student Servlet</h1>");
        //out.println("Hi " + request.getParameter("name"));
        out.println("getServletPath(): " + request.getServletPath());
        out.println("<br>");
        out.println("getRequestURL(): " + request.getRequestURL());
        out.println("<br>");
        out.println("getQueryString(): " + request.getQueryString());
        out.println("<br>");
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            out.println(entry.getKey() + " : " + entry.getValue()[0]);
            out.println("<br>");
        }
        out.println("getQueryString(): " + parameterMap);
        out.println("<br>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
