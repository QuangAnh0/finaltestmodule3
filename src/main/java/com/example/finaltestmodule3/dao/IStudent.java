package com.example.finaltestmodule3.dao;

import com.example.finaltestmodule3.model.Student;

import java.util.List;

public interface IStudent {
     List<Student> findAll();

     Student findByName(String name);

     void add(Student student);

     void edit(int id, Student student);

     void delete(Student student);
}
