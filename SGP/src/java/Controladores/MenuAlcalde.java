package Controladores;

import DAO.PermisoDAO;
import DTO.PermisoDTO;
import DTO.UsuarioDTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MenuAlcalde", urlPatterns = {"/MenuAlcalde"})
public class MenuAlcalde extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            if((UsuarioDTO)request.getSession().getAttribute("usuarioDTO") == null){
                String mensajeError = "Ingrese a su cuenta por favor.";
                request.getSession().setAttribute("mensajeError", mensajeError);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
            UsuarioDTO usuarioDTO = (UsuarioDTO)request.getSession().getAttribute("usuarioDTO");
            
            ArrayList<PermisoDTO> permisos = new PermisoDAO().readAll();
            request.getSession().setAttribute("permisos", permisos);
            
            ArrayList<PermisoDTO> permisosEmitidos = new PermisoDAO().readAll_Estado(1);
            ArrayList<PermisoDTO> permisosAprobados = new PermisoDAO().readAll_Estado(2);
            ArrayList<PermisoDTO> permisosRechazados = new PermisoDAO().readAll_Estado(3);
            ArrayList<PermisoDTO> permisosDesistidos = new PermisoDAO().readAll_Estado(4);
            
            request.getSession().setAttribute("permisosEmitidos", permisosEmitidos);
            request.getSession().setAttribute("permisosAprobados", permisosAprobados);
            request.getSession().setAttribute("permisosRechazados", permisosRechazados);
            request.getSession().setAttribute("permisosDesistidos", permisosDesistidos);
            request.getSession().setAttribute("usuarioDTO", usuarioDTO);
            request.getRequestDispatcher("menuAlcalde.jsp").forward(request, response);
            
        } catch(NullPointerException ex) {
            String mensajeError = "Error inesperado (MenuAlcalde)" + ex.getMessage();
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
