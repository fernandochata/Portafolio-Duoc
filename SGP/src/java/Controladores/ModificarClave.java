/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ModificarClave", urlPatterns = {"/ModificarClave"})
public class ModificarClave extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
            if((UsuarioDTO)request.getSession().getAttribute("usuarioDTO") == null){
                String mensajeError = "Ingrese a su cuenta por favor.";
                request.getSession().setAttribute("mensajeError", mensajeError);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
            UsuarioDTO usuarioDTO = (UsuarioDTO)request.getSession().getAttribute("usuarioDTO");
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            String claveActual = request.getParameter("claveActual");
            String claveNueva1 = request.getParameter("claveNueva1");
            String claveNueva2 = request.getParameter("claveNueva2");

            if(!claveActual.equals(usuarioDTO.getClave())){
                // CLAVE ACTUAL ES INCORRECTA
                String mensajeError = "Clave ingresada no corresponde a la clave almacenada, intente de nuevo.";
                request.getSession().setAttribute("mensajeError", mensajeError);
                request.getRequestDispatcher("cambiarClave.jsp").forward(request, response);
            }else{
                // CLAVE ACTUAL ES CORRECTA
                if(claveNueva1.equals(claveNueva2)){
                    // CLAVES INGRESADAS SON IGUALES
                    usuarioDTO.setClave(claveNueva1);
                    
                    if(usuarioDAO.update(usuarioDTO)){
                        // SE MODIFICÓ CORRECTAMENTE
                        String mensajeError = "Se modificó correctamente la clave.";
                        request.getSession().setAttribute("mensajeError", mensajeError);
                        request.getRequestDispatcher("cambiarClave.jsp").forward(request, response);  
                    }else{
                        // ERROR AL MODIFICAR CLAVE DE USUARIO
                        String mensajeError = "Se produjo un error al actualizar la clave.";
                        request.getSession().setAttribute("mensajeError", mensajeError);
                        request.getRequestDispatcher("cambiarClave.jsp").forward(request, response);  
                    }
                }else{
                    // CLAVES INGRESADAS NO SON IGUALES
                    String mensajeError = "Las claves no coinciden. Inténtelo nuevamente.";
                    request.getSession().setAttribute("mensajeError", mensajeError);
                    request.getRequestDispatcher("cambiarClave.jsp").forward(request, response);     
                }
            }
        } catch(NullPointerException ex) {
            String mensajeError = "Error inesperado (ModificarClave) | " + ex.getMessage();
            request.getSession().setAttribute("mensajeError", mensajeError);
            request.getRequestDispatcher("CerrarSesion").forward(request, response);
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
