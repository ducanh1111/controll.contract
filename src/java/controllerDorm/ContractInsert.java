/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerDorm;

import dal.ContractDBContext;
import dal.RoomDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Contract;
import modal.Room;
import modal.Student;
import modal.TypeOfRoom;

/**
 *
 * @author ADMIN
 */
public class ContractInsert extends HttpServlet {

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
        int rid = Integer.parseInt(request.getParameter("rid"));
        RoomDBContext rdb = new RoomDBContext();
        Room room = rdb.getRoom(rid);
        request.setAttribute("room", room);
        request.getRequestDispatcher("../view/contract/insert.jsp").forward(request, response);
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
        int rid = Integer.parseInt(request.getParameter("rid"));
        String rname = request.getParameter("rname");
        String type = request.getParameter("type");
        String price = request.getParameter("price");
        String deposit = request.getParameter("deposit");
        String from = request.getParameter("date");
        String sname = request.getParameter("sname");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phonenumber");
        String cardnumber = request.getParameter("cardnumber");
        String address = request.getParameter("address");
        
        Contract c = new Contract();
        TypeOfRoom tor = new TypeOfRoom();
        Room r = new Room();
        Student s = new Student();
        
        tor.setName(type);
        tor.setPrice(Double.parseDouble(price));
        r.setRid(rid);
        r.setRname(rname);
        r.setTypeofroom(tor);
        s.setName(sname);
        s.setGender((gender.equals("male")));
        s.setDob(Date.valueOf(dob));
        s.setPhonenumber(phone);
        s.setCardnumber(cardnumber);
        s.setAddress(address);
        c.setRoom(r);
        c.setStudent(s);
        c.setDeposit(Double.parseDouble(deposit));
        c.setFrom(Date.valueOf(from));
        
        ContractDBContext cdb = new ContractDBContext();
        cdb.insert(c);
        
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
