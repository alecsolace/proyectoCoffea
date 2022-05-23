/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pages;

import com.sanvalero.coffea.dao.CartDAO;
import com.sanvalero.coffea.dao.CartLineDAO;
import com.sanvalero.coffea.domain.Cart;
import com.sanvalero.coffea.domain.CartLine;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aaguirrem
 */
@WebServlet(name = "/addCart", urlPatterns = {"/addCart"})
public class addCart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Cart cart = (Cart) request.getAttribute("finalCart");
        ArrayList<CartLine> cartLines = (ArrayList<CartLine>) request.getAttribute("cartLines");

        CartDAO cartDAO = new CartDAO();
        CartLineDAO cartLineDAO = new CartLineDAO();
        int insertedLines = 0;
        try {
            if (cartLines != null && !cartLines.isEmpty() && cart != null) {
                insertedLines = cartDAO.addCart(cart);
                if (insertedLines > 0) {

                    for (CartLine cartLine : cartLines) {
                        cartLineDAO.addCartLine(cartLine);
                    }
                }
            }

            if (insertedLines > 0) {
                response.sendRedirect("index.jsp?status=ok");

            } else {
                response.sendRedirect("cart.jsp?status=error");
            }

        } catch (IOException e) {
            response.sendRedirect("cart.jsp?status=error");
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(addCart.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(addCart.class.getName()).log(Level.SEVERE, null, ex);
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
