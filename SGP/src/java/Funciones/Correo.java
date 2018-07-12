package Funciones;

import DAO.*;
import DTO.*;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Correo {
    
    public static void permisoParental(int rutFuncionario) throws AddressException, MessagingException {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage generateMailMessage;
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        UsuarioDTO funcionario = usuarioDAO.read(rutFuncionario);
        //PermisoDAO permisoDAO = new PermisoDAO();
        //PermisoDTO permiso = permisoDAO.read(idPermiso);
        //PermisoTipoDAO permisoTiposDAO = new PermisoTipoDAO();
        
        UsuarioDTO jefeInterno = usuarioDAO.read_Perfil_Departamento(3, funcionario.getDepartamento());

        String emailTO = jefeInterno.getEmail();
        String emailSubject = "Permiso Parental";
        String emailBody = "<p>Se ingresó nuevo permiso parental de " + funcionario.getNombres() + " " + funcionario.getApellido_paterno() + ".</p>";
        emailBody = emailBody + "<p>Recuerde que puede revisar el detalle del permiso ingresado a través de la aplicación web.</p>";
        
        String emailGmail = "sistemagestionpermisos@gmail.com";
        String claveGmail = "sgpadmin";
        
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTO));
        generateMailMessage.setSubject(emailSubject);
        generateMailMessage.setContent(emailBody, "text/html");
        
        Transport transport = getMailSession.getTransport("smtp");

        transport.connect("smtp.gmail.com", emailGmail, claveGmail);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
    public static void permisoFallecimiento(int rutFuncionario) throws AddressException, MessagingException {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage generateMailMessage;
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        UsuarioDTO funcionario = usuarioDAO.read(rutFuncionario);
        
        UsuarioDTO jefeInterno = usuarioDAO.read_Perfil_Departamento(3, funcionario.getDepartamento());

        String emailTO = jefeInterno.getEmail();
        String emailSubject = "Permiso Parental";
        String emailBody = "<p>Se ingresó nuevo permiso por fallecimiento de " + funcionario.getNombres() + " " + funcionario.getApellido_paterno() + ".</p>";
        emailBody = emailBody + "<p>Recuerde que puede revisar el detalle del permiso ingresado a través de la aplicación web.</p>";
        
        String emailGmail = "sistemagestionpermisos@gmail.com";
        String claveGmail = "sgpadmin";
        
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTO));
        generateMailMessage.setSubject(emailSubject);
        generateMailMessage.setContent(emailBody, "text/html");
        
        Transport transport = getMailSession.getTransport("smtp");

        transport.connect("smtp.gmail.com", emailGmail, claveGmail);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
    public static void sinAdjunto(String emailTO, String emailSubject, String emailBody) throws AddressException, MessagingException{
        
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage generateMailMessage;
        
        String emailGmail = "sistemagestionpermisos@gmail.com";
        String claveGmail = "sgpadmin";
        
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTO));
        generateMailMessage.setSubject(emailSubject);
        generateMailMessage.setContent(emailBody, "text/html");
        
        Transport transport = getMailSession.getTransport("smtp");

        transport.connect("smtp.gmail.com", emailGmail, claveGmail);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    
    }
    public static void permisoRespuesta(PermisoDTO permiso) throws AddressException, MessagingException {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage generateMailMessage;
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        UsuarioDTO funcionario = usuarioDAO.read(permiso.getUsuario());
        PermisoEstadoDAO permisoEstadoDAO = new PermisoEstadoDAO();
        //PermisoDTO permiso = permisoDAO.read(idPermiso);
        PermisoTipoDAO permisoTiposDAO = new PermisoTipoDAO();
        
        //UsuarioDTO jefeInterno = usuarioDAO.read_Perfil_Departamento(3, funcionario.getDepartamento());

        String emailTO = funcionario.getEmail();
        String emailSubject = "Permiso " + permisoEstadoDAO.read(permiso.getEstado()).getEstado();
        String emailBody = "<p>Se realizó una modificación a su permiso "+ permisoTiposDAO.read(permiso.getTipo()).getTipo() + " número " + permiso.getId_permiso() + "</p>";
        emailBody = emailBody + "<p>Recuerde que puede revisar el detalle del permiso a través de la aplicación web.</p>";
        
        String emailGmail = "sistemagestionpermisos@gmail.com";
        String claveGmail = "sgpadmin";
        
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTO));
        generateMailMessage.setSubject(emailSubject);
        generateMailMessage.setContent(emailBody, "text/html");
        
        Transport transport = getMailSession.getTransport("smtp");

        transport.connect("smtp.gmail.com", emailGmail, claveGmail);
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }
}
