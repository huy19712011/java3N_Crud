package com.example.java3n_crud.jdbc.preparedStatement;

import com.example.java3n_crud.entity.Student;
import com.example.java3n_crud.repository.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectingDemo {
    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<>();
        DatabaseConnectionManager dcm =
                new DatabaseConnectionManager("test1", "sa", "123456");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dcm.getConnection();
            String sql = """
                       SELECT * FROM students;
                    """;

            preparedStatement = connection.prepareStatement(sql);

            // set values
            //preparedStatement.setLong(1, 1003L);
            //preparedStatement.setString(2, "student 3");
            //preparedStatement.setString(3, "email 3");
            //preparedStatement.setString(4, "phone 3");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setEmail(resultSet.getString("email"));
                student.setPhone(resultSet.getString("phone"));

                students.add(student);

            }

            System.out.println("done...");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

        }

        students.forEach(student -> System.out.println(student.getId()));
    }
}
