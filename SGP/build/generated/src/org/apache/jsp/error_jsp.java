package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import DTO.*;
import DAO.*;
import Funciones.*;
import java.util.*;

public final class error_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"shortcut icon\" type=\"image/x-icon\" href=\"imagenes/favicon.ico\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n");
      out.write("        \n");
      out.write("        <script type=\"text/javascript\" src=\"https://code.jquery.com/jquery-3.3.1.js\" ></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/jquery.dataTables.js\" ></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js\" ></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js\" ></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js\" ></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"https://cdn.datatables.net/buttons/1.5.2/js/buttons.html5.min.js\" ></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"https://editor.datatables.net/extensions/Editor/js/dataTables.editor.min.js\" ></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"https://cdn.datatables.net/select/1.2.6/js/dataTables.select.min.js\" ></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js\" ></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js\" ></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"https://cdn.datatables.net/buttons/1.5.2/js/buttons.print.min.js\" ></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/popper.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"js/bootstrap.min.js\"></script>\n");
      out.write("        \n");
      out.write("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"https://cdn.datatables.net/1.10.18/css/jquery.dataTables.min.css\"/>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css\"/>\n");
      out.write("\n");
      out.write("        <title>Sistema de Gestión de Permisos</title>\n");
      out.write("\n");
      out.write("        <style>\n");
      out.write("            html, body{height:100%; width:100%;}\n");
      out.write("            #tabs .nav-tabs .nav-item.show .nav-link, .nav-tabs .nav-link.active { border-bottom: 4px solid !important; }\n");
      out.write("            \n");
      out.write("            .body-block{\n");
      out.write("                background:rgb(20, 122, 75);\n");
      out.write("                background:-webkit-linear-gradient(to bottom,rgb(255, 255, 255),rgb(8, 100, 20));\n");
      out.write("                background:linear-gradient(to bottom,rgb(255, 255, 255),rgb(8, 100, 20));\n");
      out.write("                width:100%;height:100%;\n");
      out.write("               }\n");
      out.write("            .container{background:#fff; border-radius: 10px; box-shadow:15px 20px 0px rgba(0,0,0,0.1);}\n");
      out.write("        </style>\n");
      out.write("    </head> <!-- HEAD -->\n");
      out.write("    <body>\n");
      out.write("        <section>\n");
      out.write("            ");

                String mensajeError = (String)request.getSession().getAttribute("mensajeError");
            
      out.write("\n");
      out.write("        </section> <!-- CARGAR DATOS -->\n");
      out.write("        <section>\n");
      out.write("            ");
 if(request.getSession().getAttribute("mensajeError") != null){ 
      out.write("\n");
      out.write("            <div class=\"modal\" id=\"modalMensaje\" tabindex=\"-1\"  role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"false\">\n");
      out.write("                <div class=\"modal-dialog\" role=\"document\">\n");
      out.write("                  <div class=\"modal-content\">\n");
      out.write("                    <div class=\"modal-header\">\n");
      out.write("                      <h5 class=\"modal-title\" id=\"exampleModalLabel\">SGP</h5>\n");
      out.write("                      <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n");
      out.write("                        <span aria-hidden=\"true\">&times;</span>\n");
      out.write("                      </button>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"modal-body\">\n");
      out.write("                      <p>");
      out.print(request.getSession().getAttribute("mensajeError") );
      out.write("</p>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"modal-footer\">\n");
      out.write("                      <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n");
      out.write("                    </div>\n");
      out.write("                  </div>\n");
      out.write("                </div>\n");
      out.write("              </div>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("        </section> <!-- MODAL -->\n");
      out.write("        <div class=\"text-center\">\n");
      out.write("            <img class=\"img-fluid rounded\" src=\"imagenes/error.png\" height=\"500\" width=\"500\">\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        <script type=\"text/javascript\">$('#modalMensaje').modal('show');</script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
