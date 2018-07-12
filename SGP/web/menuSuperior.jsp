<%@page import="DTO.*"%>
<%@page import="DAO.*"%>
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
            
            .nav.nav-tabs.nav-fill > a {border-radius: 0;border-bottom: none!important;}
            .nav.nav-tabs.nav-fill > a:first-child {border-radius: 5px 0 0 5px;}
            .nav.nav-tabs.nav-fill > a:last-child {border-radius: 0 5px 5px 0;}
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
                ArrayList<PermisoDTO> decretos = (ArrayList<PermisoDTO>)request.getSession().getAttribute("decretos");
                ArrayList<PermisoDTO> permisos = (ArrayList<PermisoDTO>)request.getSession().getAttribute("permisos");


                UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute("usuarioDTO");

                UsuarioDAO             usuarioDAO               = new UsuarioDAO();
                UsuarioPerfilDAO       usuarioPerfilDAO         = new UsuarioPerfilDAO();
                UsuarioDepartamentoDAO usuarioDepartamentoDAO   = new UsuarioDepartamentoDAO();
                UsuarioCargoDAO        usuarioCargoDAO          = new UsuarioCargoDAO();

                PermisoEstadoDAO       permisoEstadoDAO     = new PermisoEstadoDAO();
                PermisoMotivoDAO       permisoMotivoDAO     = new PermisoMotivoDAO();
                PermisoResolucionDAO   permisoResolucionDAO = new PermisoResolucionDAO();
                PermisoTipoDAO         permisoTipoDAO       = new PermisoTipoDAO();
            %>
        </section><!-- CARGAR DATOS -->
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
                            <a class="dropdown-item" href="verPerfil.jsp">Ver Perfil</a>
                            <a class="dropdown-item" href="cambiarClave.jsp">Cambiar Clave</a>
                        </div>
                    </li>
                </ul>
                <a class="btn btn-secondary" href="CerrarSesion"><i class="fas fa-sign-out-alt"></i> Cerrar Sesión</a>
            </div>
        </nav> <!-- HEADER -->
        <section class="body-block" id="tabs">
            <br>
            <div class="container">
                <div class="row-fluid">
                    <div class="col-xs-12 "><br>
                    <nav>
                        <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                            <a class="btn btn-success nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Consultar Permisos</a>
                            <a class="btn btn-success nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">Consultar Decretos</a>
                            <a class="btn btn-success nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">Verificar Decreto</a>
                        </div>
                    </nav>
                        <div class="tab-content py-3 px-3 px-sm-0" id="nav-tabContent">
                            <div class="tab-pane table-responsive fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <div>
                                    <table id="tabla-permisos" class="table table-hover dataTable no-footer">
                                        <thead>
                                            <tr>
                                                <th>FUNCIONARIO</th>
                                                <th>DEPARTAMENTO</th>
                                                <th>TIPO</th>
                                                <th>ESTADO</th>
                                                <th>SOLICITUD</th>
                                                <th>DESDE</th>
                                                <th>DÍAS</th>
                                                <th>PERMISO</th>
                                                <th>DECRETO</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (PermisoDTO permiso: permisos)
                                            {%>
                                            <tr>
                                                <td><%=usuarioDAO.read(permiso.getUsuario()).getNombres() %> <%=usuarioDAO.read(permiso.getUsuario()).getApellido_paterno() %></td>
                                                <td><%=usuarioDepartamentoDAO.read(usuarioDAO.read(permiso.getUsuario()).getDepartamento()).getDepartamento() %></td>
                                                <td><%=permisoTipoDAO.read(permiso.getTipo()).getTipo() %></td>
                                                <td><%=permisoEstadoDAO.read(permiso.getEstado()).getEstado() %></td>
                                                <td><%=permiso.getFecha_creacion() %></td>
                                                <td><%=permiso.getFecha_desde() %></td>
                                                <td><%=permiso.getDias() %></td>
                                                <td>
                                                <form action="VerPermiso" method="POST">
                                                    <% if(permiso.getId_permiso() != 0){%>
                                                        <button class="btn btn-link form-control" type="submit" name="id_permiso" value="<%=permiso.getId_permiso() %>"><i class="fas fa-external-link-alt"></i> <%=permiso.getId_permiso() %></button>
                                                    <%}%>
                                                </form>
                                                </td>
                                                <td>    
                                                <form action="VerResolucion" method="POST">
                                                    <% if(permiso.getResolucion() != 0){%>
                                                    <button class="btn btn-link form-control" type="submit" name="id_resolucion" value="<%=permiso.getResolucion()%>"><i class="fas fa-external-link-alt"></i> <%=permiso.getResolucion()%></button>
                                                    <%}%>
                                                </form>
                                                </td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table><br>
                                </div>
                            </div>     
                            <div class="tab-pane table-responsive fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                                <div >
                                    <table id="tabla-resoluciones" class="table table-hover dataTable no-footer">
                                        <thead>
                                            <tr>
                                                <th>FUNCIONARIO</th>
                                                <th>DEPARTAMENTO</th>
                                                <th>TIPO</th>
                                                <th>SOLICITUD</th>
                                                <th>INICIO</th>
                                                <th>DIAS</th>
                                                <th>DECRETO</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (PermisoDTO permiso: decretos)
                                            {%>
                                            <tr>
                                                <td><%=usuarioDAO.read(permiso.getUsuario()).getNombres() %> <%=usuarioDAO.read(permiso.getUsuario()).getApellido_paterno() %></td>
                                                <td><%=usuarioDepartamentoDAO.read(usuarioDAO.read(permiso.getUsuario()).getDepartamento()).getDepartamento() %></td>
                                                <td><%=permisoTipoDAO.read(permiso.getTipo()).getTipo() %></td>
                                                <td><%=permiso.getFecha_creacion() %></td>
                                                <td><%=permiso.getFecha_desde() %></td>
                                                <td><%=permiso.getDias() %></td>
                                                <td>
                                                <form action="VerResolucion" method="POST">
                                                        <!--<button value="<%=permiso.getResolucion()%>" type="submit" name="id_resolucion" class="btn btn-link btn-sm">Ver detalle</button>-->
                                                        <button class="btn btn-link form-control" type="submit" name="id_resolucion" value="<%=permiso.getResolucion()%>"><i class="fas fa-external-link-alt"></i> Decreto <%=permiso.getResolucion()%></button>
                                                </form>
                                                </td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table><br>
                                </div>
                            </div>
                                    
                            <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
                                <div>
                                    <div class="tab-pane " id="nav-verificar">
                                        <!--   INICIO VERIFICAR DE PERMISOS    -->
                                        <form name="form" id="form" method="POST" action="VerResolucion">
                                            <table class="table">
                                                <tr>
                                                    <td>Ingrese el código del decreto</td>
                                                    <td><input class="form-control" required pattern="[0-9]+" title="Código es númerico." id="id_resolucion" type="text"  name="id_resolucion"></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td><button class="btn btn-success" type="submit"><i class="fas fa-share-square"></i> Verificar</button></td>
                                                </tr>
                                            </table>
                                        </form>
                                        <!--    FIN VERIFICAR DE PERMISOS      -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        
        <div id="chart"></div>
            
        <script type="text/javascript">
            $(document).ready(function() {
                $('#tabla-permisos').DataTable( {
                    dom: 'Bfrtip',
                    buttons: [
                        { extend: 'pdf', text: 'Descargar en PDF' },
                        { extend: 'print', text: 'Imprimir' }
                    ]
                } );
            } );
            
            $(document).ready(function() {
                $('#tabla-resoluciones').DataTable( {
                    dom: 'Bfrtip',
                    buttons: [
                        { extend: 'pdf', text: 'Descargar en PDF' },
                        { extend: 'excel', text: 'Descargar en Excel' },
                        { extend: 'print', text: 'Imprimir Decreto' }
                    ]
                } );
            } );
        </script>
        <script type="text/javascript">$('#modalMensaje').modal('show');</script>
    </body>
</html>