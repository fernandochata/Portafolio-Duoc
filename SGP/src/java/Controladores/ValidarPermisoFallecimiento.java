package Controladores;

import DAO.*;
import DTO.*;
import Funciones.Correo;
import Funciones.Fechas;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fernando Chata
 */
@WebServlet(name = "ValidarPermisoFallecimiento", urlPatterns = {"/ValidarPermisoFallecimiento"})
public class ValidarPermisoFallecimiento extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            if((UsuarioDTO)request.getSession().getAttribute("usuarioDTO") == null){
                String mensajeError = "Ingrese a su cuenta por favor.";
                request.getSession().setAttribute("mensajeError", mensajeError);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            
            // RECUPERAR INFORMACION DE SESION Y DE FORMULARIO DE INGRESO
            UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute("usuarioDTO");
            
            Date fechaSolicitud = new Date();
            Date fechaInicio = fechaSolicitud;
            Date fechaFin = null;
            String motivo = request.getParameter("motivo");
            int parentesco = Integer.parseInt(request.getParameter("parentesco"));
            int diasPermiso = 0;
            switch (parentesco) {
            case 1: // PADRES -> 3 dias habiles
                fechaFin = Fechas.addWorkingDays(fechaInicio, 3);
                diasPermiso = 3;
                break;
            case 2: //  HIJO O HIJA -> 7 dias de corrido
                fechaFin = Fechas.addDays(fechaInicio, 7);
                diasPermiso = Fechas.workingDays(fechaInicio, fechaFin);
                break;
            case 3: // ESPOSO O ESPOSA -> 7 dias de corrido
                fechaFin = Fechas.addDays(fechaInicio, 7);
                diasPermiso = Fechas.workingDays(fechaInicio, fechaFin);
                break;
            }
            
            // CREACION DE MOTIVO
            PermisoMotivoDAO permisoMotivoDAO = new PermisoMotivoDAO();
            PermisoMotivoDTO permisoMotivoDTO = new PermisoMotivoDTO();
            permisoMotivoDTO.setMotivo(motivo);
            permisoMotivoDAO.create(permisoMotivoDTO);
            
            PermisoDAO permisoDAO = new PermisoDAO();
            PermisoDTO permisoDTO = new PermisoDTO();
            
            permisoDTO.setFecha_creacion(fechaSolicitud);
            permisoDTO.setFecha_desde(fechaInicio);
            permisoDTO.setFecha_hasta(fechaFin);
            permisoDTO.setDias(diasPermiso);
            permisoDTO.setObservacion(""); // SIN OBSERVACION
            permisoDTO.setUsuario(usuario.getRut());
            permisoDTO.setResolucion(0); // SIN RESOLUCION
            permisoDTO.setAdjunto(0); // SIN ARCHIVO ADJUNTO
            permisoDTO.setEstado(1); // ESTADO EMITIDO
            permisoDTO.setTipo(2); //TIPO FALLECIMIENTO
            permisoDTO.setMotivo(permisoMotivoDAO.last().getId_motivo());
            
            if(permisoDAO.create(permisoDTO)){
                //SE ENVIA UN CORREO A JEFE INTERNO
                Correo.permisoFallecimiento(usuario.getRut());
                String mensajeError = "Se ingresó permiso por Fallecimiento. Se envió correo electrónico a su jefe interno con detalles del permiso.";
                request.getSession().setAttribute("mensajeError", mensajeError);
                request.getRequestDispatcher("MenuFuncionario").forward(request, response);
                
            }else{
                String mensajeError = "Error al ingresar permiso por fallecimiento.";
                request.getSession().setAttribute("mensajeError", mensajeError);
                request.getRequestDispatcher("MenuFuncionario").forward(request, response);
            }
            
        } catch (NullPointerException | IOException | ServletException  | MessagingException ex) {
            //ERROR AL PASEAR ALGUNA FECHA
            String mensajeError = "Se produjo un error inesperado. (ValidarPermisoFallecimiento)" + ex.getMessage();
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
