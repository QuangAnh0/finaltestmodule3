package com.example.finaltestmodule3.dao;

import com.example.finaltestmodule3.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudent {
    public static final String URL = "jdbc:mysql://localhost:3306/finalmodule3";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";
    private final String SELECT_ALL_STUDENT = "select * from student;";
    private final String INSERT_STUDENT = "insert into student (id, name, dateofbirth, address, phonenumber, email, classroom) values (?,?,?,?,?,?,?);";
    private final String EDIT_STUDENT = "update student set id = ?, name = ?, dateofbirth = ?, address = ?, phonenumber=?, email=?, classroom = ? where id = ?;";
    private final String DELETE_STUDENT = "delete from student where name =?;";
    private final String SELECT_STUDENT_BY_NAME = "select * from student where name like ?;";
    public Connection getConnection() {
        Connection connection = null;
        try {Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Oke");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();System.out.println("Error");
        }
        return connection;
    }
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
     studentDAO.getConnection()  ; }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL_STUDENT);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Date dateOfBirth = resultSet.getDate("dateofbirth");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phonenumber");
                String email = resultSet.getString("email");
                String classroom = resultSet.getString("classroom");
                students.add(new Student(id, name, dateOfBirth, address, phoneNumber, email, classroom));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Student findByName(String name) {
        return null;
    }


    @Override
    public void add(Student student) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT);
            statement.setString(1, student.getName());
            statement.setDate(2, student.getDateOfBirth());
            statement.setString(3, student.getAddress());
            statement.setString(4, student.getPhoneNumber());
            statement.setString(5, student.getEmail());
            statement.setString(6, student.getClassroom());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void edit(int id, Student student) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(EDIT_STUDENT);
            statement.setString(1, student.getName());
            statement.setDate(2, student.getDateOfBirth());
            statement.setString(3, student.getAddress());
            statement.setString(4, student.getPhoneNumber());
            statement.setString(5, student.getEmail());
            statement.setString(6, student.getClassroom());
            statement.setInt(7, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Student student) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT);
            statement.setString(1, String.valueOf(student));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}