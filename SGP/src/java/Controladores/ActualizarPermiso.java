package Controladores;

import DTO.*;
import DAO.*;
import Funciones.Clave;
import Funciones.Correo;
import java.io.IOException;
import java.util.Date;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ActualizarPermiso", urlPatterns = {"/ActualizarPermiso"})
public class ActualizarPermiso extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            if((UsuarioDTO)request.getSession().getAttribute("usuarioDTO") == null){
                String mensajeError = "Ingrese a su cuenta por favor.";
                request.getSession().setAttribute("mensajeError", mensajeError);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
            String observacion = request.getParameter("observacion");
            int boton = Integer.parseInt(request.getParameter("boton"));
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            PermisoDAO permisoDAO = new PermisoDAO();
            
            //UsuarioDTO usuarioDTO = (UsuarioDTO)request.getSession().getAttribute("usuarioDTO");
            
            PermisoDTO permiso = (PermisoDTO)request.getSession().getAttribute("permisoDTO");
            UsuarioDTO funcionario = usuarioDAO.read(permiso.getUsuario());

            switch(boton){
                case 1: // SE AUTORIZA EL PERMISO
                    // SE DEBE CREAR LA RESOLUCION
                    PermisoResolucionDAO permisoResolucionDAO = new PermisoResolucionDAO();
                    PermisoResolucionDTO permisoResolucionDTO = new PermisoResolucionDTO();

                    permisoResolucionDTO.setCodigo(Clave.createNewCode(5));
                    permisoResolucionDTO.setDetalle("");
                    permisoResolucionDTO.setRuta_documento("");
                    permisoResolucionDTO.setFecha_creacion(new Date());

                    if(permisoResolucionDAO.create(permisoResolucionDTO)){
                        // SE CREA CORRECTAMENTE LA RESOLUCION
                        permiso.setEstado(2); // ESTADO APROBADO
                        permiso.setObservacion(observacion);
                        permiso.setResolucion(permisoResolucionDAO.last().getId_resolucion());

                        if(permisoDAO.update(permiso)){
                            // SE APRUEBA EL PERMISO
                            Correo.permisoRespuesta(permiso);
                            String mensajeError = "Se aprobó el permiso.";
                            request.getSession().setAttribute("mensajeError", mensajeError);
                            request.getRequestDispatcher("MenuInterno").forward(request, response);
                        }else{
                            String mensajeError = "No se pudo aprobar el permiso.";
                            request.getSession().setAttribute("mensajeError", mensajeError);
                            request.getRequestDispatcher("MenuInterno").forward(request, response);
                        }
                    }else{
                        String mensajeError = "Error al crear resolución del permiso.";
                        request.getSession().setAttribute("mensajeError", mensajeError);
                        request.getRequestDispatcher("MenuInterno").forward(request, response);
                    }
                    break;
                case 2: // SE RECHAZA EL PERMISO
                    String mensajeError;
                    switch(permiso.getTipo()){
                        case 1: // PERMISO ADMINISTRATIVO
                            permiso.setEstado(3); // ESTADO RECHAZADO
                            permiso.setObservacion(observacion);
                            if(permisoDAO.update(permiso)){
                                funcionario.setDd_administrativos(permiso.getDias() + funcionario.getDd_administrativos());
                                if(usuarioDAO.update(funcionario)){
                                    Correo.permisoRespuesta(permiso);
                                    mensajeError = "Se rechazó el permiso administrativo.";
                                    request.getSession().setAttribute("mensajeError", mensajeError);
                                    request.getRequestDispatcher("MenuInterno").forward(request, response);
                                }else{
                                    mensajeError = "No se pudo actualizar el usuario al rechazar el permiso legal.";
                                    request.getSession().setAttribute("mensajeError", mensajeError);
                                    request.getRequestDispatcher("MenuInterno").forward(request, response);
                                }
                            }else{
                                mensajeError = "No se pudo rechazar el permiso.";
                                request.getSession().setAttribute("mensajeError", mensajeError);
                                request.getRequestDispatcher("MenuInterno").forward(request, response);
                            }
                            break;
                        case 2: // PERMISO POR FALLECIMIENTO
                            mensajeError = "No se puede rechazar un permiso por fallecimiento.";
                            request.getSession().setAttribute("mensajeError", mensajeError);
                            request.getRequestDispatcher("autorizarPermiso.jsp").forward(request, response);
                            break;
                        case 3: // PERMISO LEGAL
                            permiso.setEstado(3); // ESTADO RECHAZADO
                            permiso.setObservacion(observacion);
                            if(permisoDAO.update(permiso)){
                                funcionario.setDd_legales(permiso.getDias() + funcionario.getDd_legales());
                                if(usuarioDAO.update(funcionario)){
                                    Correo.permisoRespuesta(permiso);
                                    mensajeError = "Se rechazó el permiso legal.";
                                    request.getSession().setAttribute("mensajeError", mensajeError);
                                    request.getRequestDispatcher("MenuInterno").forward(request, response);
                                }else{
                                    mensajeError = "No se pudo actualizar el usuario al rechazar el permiso legal.";
                                    request.getSession().setAttribute("mensajeError", mensajeError);
                                    request.getRequestDispatcher("MenuInterno").forward(request, response);
                                }
                            }else{
                                mensajeError = "No se pudo rechazar el permiso.";
                                request.getSession().setAttribute("mensajeError", mensajeError);
                                request.getRequestDispatcher("MenuInterno").forward(request, response);
                            }
                            break;
                        case 4: // PERMISO PARENTAL
                            mensajeError = "No se puede rechazar un permiso parental.";
                            request.getSession().setAttribute("mensajeError", mensajeError);
                            request.getRequestDispatcher("autorizarPermiso.jsp").forward(request, response);
                            break;
                    }
                    break;
             }
        } catch(NullPointerException | MessagingException ex) {
            String mensajeError = "Error inesperado (ActualizarPermiso) | " + ex.getMessage();
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
