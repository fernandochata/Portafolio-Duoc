package Controladores;

import DAO.*;
import DTO.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "VerResolucion", urlPatterns = {"/VerResolucion"})
public class VerResolucion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            
            String id_resolucion = request.getParameter("id_resolucion");
            UsuarioDTO usuarioDTO = (UsuarioDTO)request.getSession().getAttribute("usuarioDTO");

            if(usuarioDTO != null){ // VALIDAR SI EXISTE USUARIO Y RESOLUCION

                PermisoResolucionDAO permisoResolucionDAO = new PermisoResolucionDAO();
                PermisoResolucionDTO permisoResolucionDTO = permisoResolucionDAO.read(id_resolucion);

                if(permisoResolucionDTO!=null){ // SE ENCUENTRA CODIGO DE DECERETO
                    request.getSession().setAttribute("mensajeError", null);
                    request.getSession().setAttribute("permisoResolucionDTO", permisoResolucionDTO);
                    request.getRequestDispatcher("resolucion.jsp").forward(request, response);
                }else{
                    String mensajeError = "No se encontró Decreto";
                    request.getSession().setAttribute("mensajeError", mensajeError);
                    request.getRequestDispatcher("RedirigirIngreso").forward(request, response);
                }
            }else{
                String mensajeError = "Error de autentificación. Vuelva a ingresar.";
                request.getSession().setAttribute("mensajeError", mensajeError);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }  

        } catch(NullPointerException ex) {
            String mensajeError = "Error inesperado. (VerResolucion) | " + ex.getMessage();
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
