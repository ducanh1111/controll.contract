/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerDorm;

import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Student;

/**
 *
 * @author ADMIN
 */
public class StudentUpdate extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("sid");

        StudentDBContext sdb = new StudentDBContext();
        Student student = sdb.getStudent(Integer.parseInt(sid));

        request.setAttribute("student", student);

        request.getRequestDispatcher("../view/student/update.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("sid");
        String sname = request.getParameter("sname");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String phonenumber = request.getParameter("phonenumber");
        String cardnumber = request.getParameter("cardnumber");
        String address = request.getParameter("address");
        Student s = new Student();
        s.setId(Integer.parseInt(sid));
        s.setName(sname);
        s.setGender((gender.equals("male")));
        s.setDob(Date.valueOf(dob));
        s.setPhonenumber(phonenumber);
        s.setCardnumber(cardnumber);
        s.setAddress(address);
        
        StudentDBContext sdb = new StudentDBContext();
        sdb.update(s);
        
        response.sendRedirect("list");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
