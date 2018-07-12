package Controladores;

import DAO.*;
import DTO.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ValidarIngreso", urlPatterns = {"/ValidarIngreso"})
public class ValidarIngreso extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            if((UsuarioDTO)request.getSession().getAttribute("usuarioDTO") != null){
                request.getSession().setAttribute("mensajeError", null);
                request.getRequestDispatcher("RedirigirIngreso").forward(request, response);
            }else{
            
                String rut = request.getParameter("rutUsuario");
                String clave = request.getParameter("claveUsuario");

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                UsuarioDTO usuarioDTO = usuarioDAO.read(rut);

                if(usuarioDTO == null){ // USUARIO NO SE ENCUENTRA EN LA BBDD
                    String mensajeError = "Usuario no existe en la base de datos, por favor vuelva a intentarlo.";
                    request.getSession().setAttribute("mensajeError", mensajeError);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }else{ // USUARIO SI SE ENCONTRÓ EN LA BBDD
                    if(!clave.equals(usuarioDTO.getClave())){ // CLAVE INGRESADA NO ES LA MISMA ALMACENADA EN LA BBDD
                        String mensajeError = "Clave incorrecta, inténtelo nuevamente.";
                        request.getSession().setAttribute("mensajeError", mensajeError);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }else{ // CLAVE CORRECTA
                        
                        switch (usuarioDTO.getPerfil()) {
                            case 1: // ADMINISTRADOR
                                String mensajeError = "El administrador debe conectarse mediante la aplicación de escritorio.";
                                request.getSession().setAttribute("mensajeError", mensajeError);
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                                break;
                            case 2: // FUNCIONARIO
                                request.getSession().setAttribute("usuarioDTO", usuarioDTO);
                                request.getSession().setAttribute("mensajeError", null);
                                request.getRequestDispatcher("MenuFuncionario").forward(request, response);
                                break;
                            case 3: // JEDE INTERNO
                                request.getSession().setAttribute("usuarioDTO", usuarioDTO);
                                request.getSession().setAttribute("mensajeError", null);
                                request.getRequestDispatcher("MenuInterno").forward(request, response);
                                break;
                            case 4: // JEFE SUPERIOR
                                request.getSession().setAttribute("usuarioDTO", usuarioDTO);
                                request.getSession().setAttribute("mensajeError", null);
                                request.getRequestDispatcher("MenuSuperior").forward(request, response);
                                break;
                            case 5: // ALCALDE
                                request.getSession().setAttribute("usuarioDTO", usuarioDTO);
                                request.getSession().setAttribute("mensajeError", null);
                                request.getRequestDispatcher("MenuAlcalde").forward(request, response);
                                break;
                        }
                    }
                }
            
            }
        }catch(NullPointerException ex){
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
