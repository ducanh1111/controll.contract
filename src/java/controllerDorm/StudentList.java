/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerDorm;

import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Student;

/**
 *
 * @author ADMIN
 */
public class StudentList extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get index page
        String index_str = request.getParameter("index");
        if (index_str == null) {
            index_str = "1";
        }
        //for search
        String rawgender = request.getParameter("gender");
        String rawsname = request.getParameter("sname");
        if (rawgender == null || rawgender.length() == 0) {
            rawgender = "all";
        }
        Boolean gender = (!rawgender.equals("all")) ? rawgender.equals("male") : null;
        String sname = rawsname;
        int index = Integer.parseInt(index_str);

        StudentDBContext sdb = new StudentDBContext();

        ArrayList<Student> students = sdb.search(gender, sname, index);
        request.setAttribute("students", students);

        //get pagenumber
        int count = sdb.count(gender, sname);
        int pagesize = 5;
        int end = count / pagesize;
        if (count % pagesize != 0) {
            end++;
        }
        request.setAttribute("end", end);

        request.setAttribute("rawgender", rawgender);
        request.setAttribute("sname", sname);

        request.getRequestDispatcher("../view/student/student.jsp").forward(request, response);
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
