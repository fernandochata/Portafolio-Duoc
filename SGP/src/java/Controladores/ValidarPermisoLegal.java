package Controladores;

import DAO.*;
import DTO.*;
import Funciones.Fechas;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fernando Chata
 */
@WebServlet(name = "ValidarPermisoLegal", urlPatterns = {"/ValidarPermisoLegal"})
public class ValidarPermisoLegal extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if((UsuarioDTO)request.getSession().getAttribute("usuarioDTO") == null){
                String mensajeError = "Ingrese a su cuenta por favor.";
                request.getSession().setAttribute("mensajeError", mensajeError);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
            // RECUPERAR INFORMACION DE SESION Y DE FORMULARIO DE INGRESO
            UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute("usuarioDTO");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaSolicitud = new Date();
            Date fechaInicio = sdf.parse(request.getParameter("fechaInicio"));
            Date fechaFin = sdf.parse(request.getParameter("fechaFin"));
            String motivo = request.getParameter("motivo");

            int diasDisponibles = usuario.getDd_legales();
            int diasSolicitados = Fechas.workingDays(fechaInicio, fechaFin);
            Date fechaMin = Fechas.addWorkingDays(fechaSolicitud, 5);
            Date fechaMax = Fechas.addMonths(fechaSolicitud, 3);
            
            // FECHA DE INICIO NO PUEDE SER INFERIOR A 5 DIAS HABILES
            // FECHA DE INICIO NO PUEDE SER SUPERIOR A 3 MESES
            if(fechaInicio.before(fechaMin) || fechaInicio.after(fechaMax)){
                String mensajeError = "La fecha de solicitud del permiso legal no puedo ser inferior a 5 dias hábiles, ni superior a 3 meses.";
                request.getSession().setAttribute("mensajeError", mensajeError);
                request.getRequestDispatcher("menuFuncionario.jsp").forward(request, response);
            }else{
                // FECHA DE INICIO NO PUEDE SER INFERIOR A LA FECHA DE FIN
                if(fechaFin.before(fechaInicio)){
                    String mensajeError = "La fecha final del permiso no puede ser anterior a la fecha de inicio.";
                    request.getSession().setAttribute("mensajeError", mensajeError);
                    request.getRequestDispatcher("menuFuncionario.jsp").forward(request, response);
                }else{
                    // TOTAL DE DIAS HABILES DE PERMISO SOLICITADOS NO PUEDE SER MAYOR A LOS DIAS DIAS DISPONIBLES + DIAS DE PERMISOS PENDIENTES (EMITIDOS)
                    if(diasDisponibles < diasSolicitados){
                        String mensajeError = "No se cuentan con dias de permisos legales suficientes";
                        request.getSession().setAttribute("mensajeError", mensajeError);
                        request.getRequestDispatcher("menuFuncionario.jsp").forward(request, response);
                    }else{
                        // DIAS SOLICITADOS DEBE SER MAYOR A 1
                        if(diasSolicitados >= 1){
                            // MOTIVO ES UN CAMPO OBLIGATORIO
                            if(motivo == null || motivo.equals("")){
                                String mensajeError = "Se debe ingresar un motivo";
                                request.getSession().setAttribute("mensajeError", mensajeError);
                                request.getRequestDispatcher("menuFuncionario.jsp").forward(request, response);
                            }else{
                                // CREACION DE MOTIVO
                                PermisoMotivoDAO permisoMotivoDAO = new PermisoMotivoDAO();
                                PermisoMotivoDTO permisoMotivoDTO = new PermisoMotivoDTO();
                                permisoMotivoDTO.setMotivo(motivo);
                                permisoMotivoDAO.create(permisoMotivoDTO);

                                PermisoDTO permiso = new PermisoDTO();

                                permiso.setFecha_creacion(fechaSolicitud);
                                permiso.setFecha_desde(fechaInicio);
                                permiso.setFecha_hasta(fechaFin);
                                permiso.setDias(diasSolicitados);
                                permiso.setUsuario(usuario.getRut());
                                permiso.setObservacion("");
                                permiso.setAdjunto(0); // SIN ARCHIVO ADJUNTO
                                permiso.setResolucion(0); // SIN RESOLUCION
                                permiso.setEstado(1); // ESTADO EMITIDO
                                permiso.setTipo(3); //TIPO LEGAL
                                permiso.setMotivo(permisoMotivoDAO.last().getId_motivo()); // ULTIMO NOTIVO INGRESADO

                                PermisoDAO permisoDAO = new PermisoDAO();

                                //if(Fechas.permiso(permiso)){ // VERIFICAR SI POSEE UN PERMISO EN ESAS FECHAS
                                    if(permisoDAO.create(permiso)){
                                    usuario.setDd_legales(usuario.getDd_legales() - diasSolicitados);
                                    UsuarioDAO usuarioDAO = new UsuarioDAO();

                                    if(usuarioDAO.update(usuario)){
                                        String mensajeError = "Se ingresó permiso Legal.";
                                        request.getSession().setAttribute("mensajeError", mensajeError);
                                        request.getRequestDispatcher("MenuFuncionario").forward(request, response);
                                    }else{
                                        String mensajeError = "Error al modificar usuario." + permiso.toString();
                                        request.getSession().setAttribute("mensajeError", mensajeError);
                                        request.getRequestDispatcher("MenuFuncionario").forward(request, response);
                                    }
                                }else{
                                    String mensajeError = "Error al modificar permiso." + permiso.toString();
                                    request.getSession().setAttribute("mensajeError", mensajeError);
                                    request.getRequestDispatcher("MenuFuncionario").forward(request, response);
                                }
                                //}else{
                                //    String mensajeError = "El número de dias hábiles del permiso solicitado debe ser superior a 1 día.";
                                //    request.getSession().setAttribute("mensajeError", mensajeError);
                                //    request.getRequestDispatcher("menuFuncionario.jsp").forward(request, response);
                                //}
                            }
                        }else{
                            String mensajeError = "El número de dias hábiles del permiso solicitado debe ser superior a 1 día.";
                            request.getSession().setAttribute("mensajeError", mensajeError);
                            request.getRequestDispatcher("menuFuncionario.jsp").forward(request, response);
                        }
                    }
                }
            }
        } catch (NullPointerException | IOException | ServletException | ParseException ex) {
            String mensajeError = "Se produjo un error inesperado. (ValidarPermisoLegal)" + ex.getMessage();
            request.getSession().setAttribute("mensajeError", mensajeError);
            request.getRequestDispatcher("MenuFuncionario").forward(request, response);
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
