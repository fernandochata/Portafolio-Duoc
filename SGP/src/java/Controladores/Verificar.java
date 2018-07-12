package Controladores;

import DAO.*;
import DTO.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VerificarResoluciones", urlPatterns = {"/VerificarResoluciones"})
public class Verificar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            if((UsuarioDTO)request.getSession().getAttribute("usuarioDTO") == null){
                String mensajeError = "Ingrese a su cuenta por favor.";
                request.getSession().setAttribute("mensajeError", mensajeError);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            int id_resolucion = Integer.parseInt(request.getParameter("codigoDocumento"));
            PermisoResolucionDAO permisoResolucionDAO = new PermisoResolucionDAO();
            PermisoResolucionDTO permisoResolucionDTO = permisoResolucionDAO.read(id_resolucion);
            
            if(permisoResolucionDTO != null){ // RESOLUCION EXISTE
                String mensajeError = "Se encontró el decreto.";
                request.getSession().setAttribute("mensajeError", mensajeError);
                request.getSession().setAttribute("resolucion", permisoResolucionDTO);
                request.getRequestDispatcher("verResolucion.jsp").forward(request, response);
            }else{ // RESOLUCION NO EXISTE
                String mensajeError = "No se encontró el decreto.";
                request.getSession().setAttribute("mensajeError", mensajeError);
                request.getSession().setAttribute("resolucion", permisoResolucionDTO);
                request.getRequestDispatcher("RedirigirIngreso").forward(request, response);
            }
            
            } catch(NullPointerException ex) {
            String mensajeError = "Error inesperado. (Verificar) | " + ex.getMessage();
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
