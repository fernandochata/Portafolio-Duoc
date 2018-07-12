<%@page import="DTO.*"%>
<%@page import="DAO.*"%>
<%@page import="Funciones.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="imagenes/favicon.ico">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.js" ></script>
        <script type="text/javascript" src="js/jquery.dataTables.js" ></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js" ></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js" ></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js" ></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.html5.min.js" ></script>
        <script type="text/javascript" src="https://editor.datatables.net/extensions/Editor/js/dataTables.editor.min.js" ></script>
        <script type="text/javascript" src="https://cdn.datatables.net/select/1.2.6/js/dataTables.select.min.js" ></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js" ></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js" ></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.print.min.js" ></script>
        <script type="text/javascript" src="js/popper.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/jspdf.js"></script>
        <script type="text/javascript" src="js/HTMLtoPDF.js"></script>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
        
        <title>Sistema de Gestión de Permisos</title>

        <style>
            html, body{height:100%; width:100%;}
            #tabs .nav-tabs .nav-item.show .nav-link, .nav-tabs .nav-link.active { border-bottom: 4px solid !important; }
            
            .body-block{
                background:rgb(20, 122, 75);
                background:-webkit-linear-gradient(to bottom,rgb(8, 100, 20),rgb(255, 255, 255));
                background:linear-gradient(to bottom,rgb(8, 100, 20),rgb(255, 255, 255));
                width:100%;height:100%;
               }
            .container{background:#fff; border-radius: 10px; box-shadow:15px 20px 0px rgba(0,0,0,0.1);}
        </style>
    </head> <!-- HEAD -->
    <body style="padding-top: 50px;">
        <section>
            <%
                String mensajeError = (String)request.getSession().getAttribute("mensajeError");
                request.getSession().setAttribute("mensajeError", null);
                
                if((UsuarioDTO)request.getSession().getAttribute("usuarioDTO") == null){
                    mensajeError = "Ingrese a su cuenta por favor.";
                    request.getSession().setAttribute("mensajeError", mensajeError);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                
                UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute("usuarioDTO");
                PermisoResolucionDTO resolucion = (PermisoResolucionDTO)request.getSession().getAttribute("permisoResolucionDTO");
                
                PermisoDAO permisoDAO = new PermisoDAO();
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                
                PermisoDTO permiso = permisoDAO.read_Resolucion(resolucion.getId_resolucion());
                
                UsuarioDTO funcionario = usuarioDAO.read(permiso.getUsuario());
                UsuarioDTO jefeInterno = usuarioDAO.read_Perfil_Departamento(3, funcionario.getDepartamento());
                
                UsuarioDepartamentoDAO usuarioDepartamentoDAO = new UsuarioDepartamentoDAO();
                UsuarioPerfilDAO usuarioPerfilDAO = new UsuarioPerfilDAO();
                UsuarioCargoDAO usuarioCargoDAO = new UsuarioCargoDAO();
                PermisoTipoDAO permisoTipoDAO = new PermisoTipoDAO();
            %>
        </section> <!-- CARGAR DATOS -->
        <section>
            <% if(mensajeError != null){ %>
            <div class="modal" id="modalMensaje" tabindex="-1"  role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="false">
                <div class="modal-dialog" role="document">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="exampleModalLabel">SGP</h5>
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                      </button>
                    </div>
                    <div class="modal-body">
                      <p><%=mensajeError %></p>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    </div>
                  </div>
                </div>
              </div>
            <% } %>
        </section> <!-- MODAL -->
        <nav style="position: fixed; width: 100%; z-index: 1; top: 0;" class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#"><img class="d-block img-fluid" src="imagenes/LogoMunicipalidad-small.png" height="40" width="40" alt="logo municipalidad"></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link"><strong>Bienvenido(a) <%=usuario.getNombres() %> <%= usuario.getApellido_paterno() %> <%=usuario.getApellido_materno()%> - <%=usuarioPerfilDAO.read(usuario.getPerfil()).getPerfil() %></strong></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Opciones
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="Regresar">Regresar</a>
                        </div>
                    </li>
                </ul>
                <a class="btn btn-secondary" href="CerrarSesion"><i class="fas fa-sign-out-alt"></i> Cerrar Sesión</a>
            </div>
        </nav> <!-- HEADER -->
        
        <section class="body-block">
            <br>
            <div class="container">
                <div class="row-fluid">
                    <div id="HTMLtoPDF">
                        <img src="imagenes/LogoMunicipalidad.png" align="middle" class="center" height="150" width="150">
                        <div style="font-size:40px; text-align: center">Decreto #<%=resolucion.getId_resolucion()%></div>
                        <div style="font-size:25px;">
                            <br>
                            <p>El permiso número <strong><%=permiso.getId_permiso()%></strong> del tipo <strong>Permiso <%=permisoTipoDAO.read(permiso.getTipo()).getTipo()%></strong>
                            perteneciente a <strong><%=funcionario.getNombres()%> <%=funcionario.getApellido_paterno()%> <%=funcionario.getApellido_materno()%></strong>
                            , quien desempeña el cargo de <strong><%=usuarioCargoDAO.read(funcionario.getCargo()).getCargo()%></strong>
                            , del departamento de <strong><%=usuarioDepartamentoDAO.read(funcionario.getDepartamento()).getDepartamento() %></strong>
                            , se autorizó el dia <strong><%=Fechas.toStringFecha(resolucion.getFecha_creacion())%> </strong>
                            a las <strong><%=Fechas.toStringHora(resolucion.getFecha_creacion())%></strong> horas, por <strong><%=jefeInterno.getNombres()%></strong>
                            <strong><%=jefeInterno.getApellido_paterno()%> <%=jefeInterno.getApellido_materno()%></strong> jefe del departamento de 
                            <strong><%=usuarioDepartamentoDAO.read(funcionario.getDepartamento()).getDepartamento()%></strong>.</p>
                            
                            <p>Por un total de <strong><%=permiso.getDias()%></strong> días hábiles, a contar del <strong><%=Fechas.toStringFecha(permiso.getFecha_desde())%></strong> hasta el
                            <strong><%=Fechas.toStringFecha(permiso.getFecha_hasta())%></strong>.</p>
                            </p>
                            
                            
                            <br>
                        </div>
                    </div>
                    <table>
                        <tr>
                            <td><a class="btn btn-success" href="#" onclick="HTMLtoPDF()">Exportar a PDF</a></td>
                            <td><a class="btn btn-primary" href="Regresar">Regresar</a></td>
                        </tr>
                    </table><br>
                </div>
            </div>
        </section>

        <script type="text/javascript">$('#modalMensaje').modal('show');</script>
    </body>
</html>
