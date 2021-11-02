/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerAuth;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Account;
import modal.Feature;

/**
 *
 * @author ADMIN
 */
public abstract class BaseRequireLogin extends HttpServlet {

    private boolean checklogin(HttpServletRequest request) {
        Account a = (Account) request.getSession().getAttribute("account");
        boolean isAuth = false;
        if (a == null) {
            return false;
        } else {
            String url = request.getServletPath();
            for (Feature f : a.getFeatures()) {
                if (f.getUrl().equals(url)) {
                    isAuth = true;
                    break;
                }
            }
        }
        return isAuth;
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
        if (checklogin(request)) {
            processGet(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/auth/login");
        }
    }

    protected abstract void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected abstract void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

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
        if (checklogin(request)) {
            processPost(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/auth/login");

        }
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
