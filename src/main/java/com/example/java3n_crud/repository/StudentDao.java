package com.example.java3n_crud.repository;

import com.example.java3n_crud.entity.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentDao {

    private static DatabaseConnectionManager dcm =
            new DatabaseConnectionManager("test1", "sa", "123456");


    private static ArrayList<Student> students = new ArrayList<>();
    //static {
    //    students.add(new Student(1001L, "student 1","email 1", "phone 1"));
    //    students.add(new Student(1002L, "student 2","email 2", "phone 2"));
    //    students.add(new Student(1003L, "student 3","email 3", "phone 3"));
    //}


    public ArrayList<Student> getStudents() {

        //ArrayList<Student> students = new ArrayList<>();
        //students.add(new Student(1001L, "student 1","email 1", "phone 1"));
        //students.add(new Student(1002L, "student 2","email 2", "phone 2"));
        //students.add(new Student(1003L, "student 3","email 3", "phone 3"));

        ArrayList<Student> students1 = new ArrayList<>();
        //DatabaseConnectionManager dcm =
        //        new DatabaseConnectionManager("test1", "sa", "123456");

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

                students1.add(student);

            }

            System.out.println("done...");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //close(resultSet, preparedStatement, connection);
            dcm.close(resultSet, preparedStatement, connection);

        }

        return students1;

        //return DbUtils.getStudents();
    }

    private static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
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

    public void deleteStudent(long id) {

        //students.removeIf(student -> student.getId() == id);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dcm.getConnection();
            String sql = """
                        DELETE FROM students WHERE id = ?;
                    """;

            preparedStatement = connection.prepareStatement(sql);

            // set values
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            System.out.println("done...");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //close(resultSet, preparedStatement, connection);
            dcm.close(null, preparedStatement, connection);
        }


    }

    public void addStudent(Student student) {

        //students.add(student);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dcm.getConnection();
            String sql = """
                        INSERT INTO students (id, name, email, phone)
                        VALUES (?, ?, ?, ?);
                    """;

            preparedStatement = connection.prepareStatement(sql);

            // set values
            preparedStatement.setLong(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4, student.getPhone());

            preparedStatement.executeUpdate();

            System.out.println("done...");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //close(resultSet, preparedStatement, connection);
            dcm.close(null, preparedStatement, connection);
        }

    }

    public Student getStudentById(Long id) {

        //return students.stream()
        //        .filter(student -> student.getId().equals(id))
        //        .findFirst()
        //        .orElse(null);

        return getStudents()
                .stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void updateStudent(Student student) {

        //students.set(students.indexOf(getStudentById(student.getId())), student);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dcm.getConnection();
            String sql = """
                        UPDATE students
                        SET name = ?, email = ?, phone = ?
                        WHERE id = ?;
                    """;

            preparedStatement = connection.prepareStatement(sql);

            // set values
            preparedStatement.setLong(  4, student.getId());
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPhone());

            preparedStatement.executeUpdate();

            System.out.println("done...");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            //close(resultSet, preparedStatement, connection);
            dcm.close(null, preparedStatement, connection);
        }


    }
}
