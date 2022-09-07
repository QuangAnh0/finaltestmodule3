package com.example.finaltestmodule3.controller;

import com.example.finaltestmodule3.dao.StudentDAO;
import com.example.finaltestmodule3.model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
@WebServlet(name = "StudentServlet", value = "/Student")
public class StudentServlet extends HttpServlet {

        private StudentDAO studentDAO = new StudentDAO();
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");
            if(action == null){
                action = "";
            }
            switch (action) {
                case "add":
                    showFormAdd(request,response);
                    break;
                case "edit":
                    showFromEdit(request,response);
                    break;
                case "delete":
                    break;
                default:
                    listStudent(request,response);
                    break;
            }
        }

    private void showFromEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Student studentEdit = studentDAO.findByName(name);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/edit.jsp");

        request.setAttribute("student",studentEdit );
        dispatcher.forward(request, response);
    }

    private void showFormAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/add.jsp");
        dispatcher.forward(request, response);
    }

    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");
            if(action == null){
                action = "";
            }
            switch (action) {
                case "add":
                    addStudent(request,response);
                    break;
                case "edit":
                    editStudent(request,response);
                    break;
                case "delete":
                    deleteStudent(request,response);
                    break;
                default:
                    listStudent(request,response);
                    break;
            }
        }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        studentDAO.delete(;
        List<Student> listUser = studentDAO.electAllMaterial();
        request.setAttribute("liststudent", listStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/list.jsp");
        dispatcher.forward(request, response);
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response) {
            
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Date DateOfBirth = Date.valueOf(request.getParameter("DateOfBirth"));
        String address=request.getParameter("address");
        String phoneNumber =request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String classroom = request.getParameter("classroom");
        Student newStudent = new Student(id, name, DateOfBirth, address,phoneNumber,email,classroom);
        studentDAO.add(newStudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/add.jsp");
        dispatcher.forward(request, response);
    }

    private void listStudent(HttpServletRequest request, HttpServletResponse response) {
            List<Student> students = studentDAO.findAll();
            request.setAttribute("students", students);

            RequestDispatcher dispatcher = request.getRequestDispatcher("view/list.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
