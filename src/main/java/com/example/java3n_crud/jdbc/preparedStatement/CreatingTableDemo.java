package com.example.java3n_crud.jdbc.preparedStatement;

import com.example.java3n_crud.repository.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreatingTableDemo {

    public static void main(String[] args) {

        DatabaseConnectionManager dcm =
                new DatabaseConnectionManager("test1", "sa", "123456");


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dcm.getConnection();
            String sql = """
                            CREATE TABLE students(
                               id INT NOT NULL,
                               name NVARCHAR(30) NOT NULL,
                               email NVARCHAR(50) NOT NULL,
                               phone NVARCHAR(15) NOT NULL,
                               PRIMARY KEY (ID)
                            );
                         """;

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.execute();

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
    }
}
