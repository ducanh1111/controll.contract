/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerDorm;

import dal.RoomDBContext;
import dal.TypeOfRoomDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Room;
import modal.TypeOfRoom;

/**
 *
 * @author ADMIN
 */
public class RoomUpdate extends HttpServlet {

    
    

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
        TypeOfRoomDBContext t = new TypeOfRoomDBContext();
        ArrayList<TypeOfRoom> types = t.getTypes();
        request.setAttribute("types", types);
        //get Room
        int rid = Integer.parseInt(request.getParameter("rid"));
        RoomDBContext rdb = new RoomDBContext();
        Room r = rdb.getRoom(rid);
        
        request.setAttribute("r", r);
        request.getRequestDispatcher("../view/room/update.jsp").forward(request, response);
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
        int typeid = Integer.parseInt(request.getParameter("type"));
        int rid = Integer.parseInt(request.getParameter("rid"));
        String rname = request.getParameter("rname");
        //String condition = request.getParameter("condition");
        
        TypeOfRoom tor = new TypeOfRoom();
        tor.setId(typeid);
        Room room = new Room();
        room.setRid(rid);
        room.setRname(rname);
        //room.setCondition(condition.equals("1"));
        room.setTypeofroom(tor);
        
        RoomDBContext rdb = new RoomDBContext();
        rdb.update(room);
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
