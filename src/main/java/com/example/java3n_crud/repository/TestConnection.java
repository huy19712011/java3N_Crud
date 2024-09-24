package com.example.java3n_crud.repository;

public class TestConnection {

    public static void main(String[] args) {
        DatabaseConnectionManager dcm =
                new DatabaseConnectionManager("test1", "sa", "123456");
        try {
            dcm.getConnection();
            System.out.println("Connection successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
