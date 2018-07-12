package Controladores;

import DTO.UsuarioDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RedirigirIngreso", urlPatterns = {"/RedirigirIngreso"})
public class RedirigirIngreso extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            if((UsuarioDTO)request.getSession().getAttribute("usuarioDTO") == null){
                String mensajeError = "Ingrese a su cuenta por favor.";
                request.getSession().setAttribute("mensajeError", mensajeError);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
            UsuarioDTO usuarioDTO = (UsuarioDTO)request.getSession().getAttribute("usuarioDTO");
            if(usuarioDTO != null){
                switch (usuarioDTO.getPerfil()) {
                    case 1: // ADMINISTRADOR
                        String mensajeError = "El administrador debe conectarse mediante la aplicación de escritorio";
                        request.getSession().setAttribute("mensajeError", mensajeError);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                        break;
                    case 2: // FUNCIONARIO
                        //request.getSession().setAttribute("mensajeError", null);
                        request.getRequestDispatcher("MenuFuncionario").forward(request, response);
                        break;
                    case 3: // JEDE INTERNO
                        //request.getSession().setAttribute("mensajeError", null);
                        request.getRequestDispatcher("MenuInterno").forward(request, response);
                        break;
                    case 4: // JEFE SUPERIOR
                        //request.getSession().setAttribute("mensajeError", null);
                        request.getRequestDispatcher("MenuSuperior").forward(request, response);
                        break;
                    case 5: // ALCALDE
                        //request.getSession().setAttribute("mensajeError", null);
                        request.getRequestDispatcher("MenuAlcalde").forward(request, response);
                        break;
                }
            }else{
                String mensajeError = "Error de autentificación. Vuelva a ingresar.";
                request.getSession().setAttribute("mensajeError", mensajeError);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }catch(NullPointerException ex){
        //String mensajeError = "Error inesperado. (ValidarIngreso) | " + ex.getMessage();
        //request.getSession().setAttribute("mensajeError", mensajeError);
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
