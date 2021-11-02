/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerDorm;

import dal.ContractDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Contract;

/**
 *
 * @author ADMIN
 */
public class ContractList extends HttpServlet {

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
        ContractDBContext cdb = new ContractDBContext();

        //List Contract
        String rawfrom = request.getParameter("from");
        String rawto = request.getParameter("to");
        String rawsname = request.getParameter("sname");

        String sname = rawsname;
        Date from = (rawfrom != null && rawfrom.length() > 0) ? Date.valueOf(rawfrom) : null;
        Date to = (rawto != null && rawto.length() > 0) ? Date.valueOf(rawto) : null;
        //Paging
        String rawpageindex = request.getParameter("page");
        if(rawpageindex == null) rawpageindex = "1";
        int pageindex = Integer.parseInt(rawpageindex);
        int pagesize = 5;
        int totalpage = cdb.count(sname, from, to);
        int end = (totalpage % pagesize == 0) ? (totalpage / pagesize) : (totalpage / pagesize) + 1;

        ArrayList<Contract> contracts = cdb.search(sname, from, to, pagesize, pageindex);
        //set list contract
        request.setAttribute("contracts", contracts);
        //set totalpage
        request.setAttribute("end", end);
        request.setAttribute("pageindex", pageindex);
        //set 
        request.getRequestDispatcher("../view/contract/contract.jsp").forward(request, response);

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
