package com.example.java3n_crud;

import com.example.java3n_crud.utils.EmailUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "EmailSendingServlet", value = "/EmailSendingServlet")
public class EmailSendingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("emailForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String host = "smtp.gmail.com";
        String port = "587";
        String user = "huynq1971@gmail.com";
        String pass = "ijad faab nloe qouu";

        String recipient = request.getParameter("recipient");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        String resultMessage = "";

        try {
            EmailUtils.sendEmail(host, port, user, pass, recipient, subject,
                    content);
            resultMessage = "The e-mail was sent successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/emailResult.jsp").forward(request, response);
        }
    }
}
