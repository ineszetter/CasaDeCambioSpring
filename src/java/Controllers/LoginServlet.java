/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Beans.Tblusuario;
import Constantes.RutasConstantes;
import Helpers.SecurityHelper;
import Persistencia.DAO.tblUsuarioDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Proyectos
 */
public class LoginServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(RutasConstantes.RUTA_LOGIN);
        dispatcher.forward(request, response);
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
        try {
            tblUsuarioDAO loginDao = new tblUsuarioDAO();
            //Here username and password are the names which I have given in the input box in Login.jsp page. Here I am retrieving the values entered by the user and keeping in instance variables for further use.

            Tblusuario usuario = new Tblusuario();
            usuario.setUsuario(request.getParameter("usuario"));
            usuario.setEmail(request.getParameter("email"));

            Tblusuario usuarioKey = loginDao.obtenerKeyUsuario(usuario);
            usuario.setLlave(usuarioKey.getLlave());

            usuario.setContrasena(SecurityHelper.encrypt(request.getParameter("password").getBytes(), usuario.getLlave()));

            if (loginDao.autenticarUsuario(usuario)) {
                request.setAttribute("userName", usuario.getUsuario()); //with setAttribute() you can define a "key" and value pair so that you can get it in future using getAttribute("key")
                request.getRequestDispatcher(RutasConstantes.RUTA_HOME).forward(request, response);//RequestDispatcher is used to send the control to the invoked page.
            }

            request.setAttribute("errMessage", "Cuenta no validada"); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Login page again. Here the error message returned from function has been stored in a errMessage key.
            request.getRequestDispatcher(RutasConstantes.RUTA_LOGIN).forward(request, response);//forwarding the request
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// </editor-fold>
}
