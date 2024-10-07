package com.example.java3n_crud.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet(name = "UploadServlet", value = "/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // fetch form data
        Part part = request.getPart("file");
        String submittedFileName = part.getSubmittedFileName();

        String originPath = getServletContext().getRealPath(".");
        String path = originPath.substring(0, originPath.indexOf("target")) + "src\\main\\webapp\\images\\" + submittedFileName;


        InputStream inputStream = part.getInputStream();
        boolean isUploaded = uploadFile(inputStream, path);
        if (isUploaded) {
            out.println("File uploaded to directory: " + path);
            out.println(getServletContext().getRealPath(".")); // originPath
        } else {
            out.println("Error...");
        }

    }

    private boolean uploadFile(InputStream inputStream, String path) {

        boolean isDone = false;
        try {

            byte[] bytes = new byte[inputStream.available()];
            inputStream.read();
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();

            isDone = true;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return isDone;
    }
}
