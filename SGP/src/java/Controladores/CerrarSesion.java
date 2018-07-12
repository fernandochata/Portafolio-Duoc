package Controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CerrarSesion", urlPatterns = {"/CerrarSesion"})
public class CerrarSesion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getSession().setAttribute("mensajeError", null);
        request.getSession().setAttribute("usuarioDTO", null);
        request.getSession().setAttribute("listaPermisos", null);
        
        request.getSession().setAttribute("permisosEmitidos", null);
        request.getSession().setAttribute("permisosAprobados", null);
        request.getSession().setAttribute("permisosRechazados", null);
        request.getSession().setAttribute("permisosDesistidos", null);
        
        request.getSession().setAttribute("permisosDepartamento", null);
        request.getSession().setAttribute("permisosEstadoDepartamento", null);
        
        request.getSession().setAttribute("id_permiso", null);
        request.getSession().setAttribute("permisoDTO", null);
        request.getSession().setAttribute("observacion", null);
        request.getSession().setAttribute("boton", null);
        
        
        //String mensajeError = "Ocurrio un error inesperado, por favor vuelva a ingresar.";
        //request.getSession().setAttribute("mensajeError", mensajeError);
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
