/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerDorm;

import dal.RoomDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Room;

/**
 *
 * @author ADMIN
 */
public class RoomList extends HttpServlet {

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
        RoomDBContext rdb = new RoomDBContext();
        //pageindex
        String pageindexSTR = request.getParameter("pageindex");
        if(pageindexSTR == null || pageindexSTR.length() == 0){
            pageindexSTR = "1";
        }
        int pageindex = Integer.parseInt(pageindexSTR);
        //search and list
        String rawcondition = request.getParameter("condition");
        String rawtype = request.getParameter("type");
        String rawprice = request.getParameter("price");
        if (rawtype == null || rawtype.length() == 0) {
            rawtype = "all";
        }
        if (rawcondition == null || rawcondition.length() == 0) {
            rawcondition = "all";
        }
        if (rawprice == null || rawprice.length() == 0) {
            rawprice = "-1";
        }

        String type = rawtype;
        int price = Integer.parseInt(rawprice);
        Boolean condition = (!rawcondition.equals("all")) ? (rawcondition.equals("availiable")) : null;
        
        request.setAttribute("type", type);
        request.setAttribute("price", rawprice);
        request.setAttribute("condition", rawcondition);
        // paging
        int pagesize = 5;
        int count = rdb.count(condition, type, price);
        int end = (count % pagesize == 0) ? (count / pagesize) : ((count / pagesize) + 1);
        request.setAttribute("end", end);
        //
        ArrayList<Room> rooms = rdb.search(condition, type, price, pagesize, pageindex);
        request.setAttribute("rooms", rooms);

        request.getRequestDispatcher("../view/room/room.jsp").forward(request, response);
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
