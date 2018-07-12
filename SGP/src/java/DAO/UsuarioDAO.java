package DAO;

import Conexion.Conexion;
import DTO.UsuarioDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {
    
    PreparedStatement ps;
    
    private static final String SQL_UPDATE = "UPDATE USUARIOS SET dv=?, clave=?, nombres=?, apellido_paterno=?, apellido_materno=?, direccion=?, comuna=?, telefono=?, email=?, dd_legales=?, dd_administrativos=?, fecha_contrato=?, perfil=?, cargo=?, departamento=? WHERE rut=?";
    
    private static final String SQL_UPDATE_CLAVE = "UPDATE USUARIOS SET CLAVE=? WHERE RUT=?";
    private static final String SQL_UPDATE_ADMINISTRATIVO = "UPDATE USUARIOS SET DD_ADMINISTRATIVOS=? WHERE RUT=?";
    private static final String SQL_UPDATE_LEGAL = "UPDATE USUARIOS SET DD_LEGALES=? WHERE RUT=?";

    
    private static final String SQL_READ = "SELECT * FROM Usuarios WHERE rut = ?";
    private static final String SQL_READALL = "SELECT * FROM Usuarios";
    private static final String SQL_READ_PERFIL_DEPARTAMENTO = "SELECT * FROM USUARIOS WHERE PERFIL=? AND DEPARTAMENTO=?";
    
    private static final Conexion con = Conexion.iniciarConexion();
    
    public boolean update(UsuarioDTO aux) {
        try {           
            ps = con.getCnn().prepareStatement(SQL_UPDATE);
            
            ps.setString(1, aux.getDv());
            ps.setString(2, aux.getClave());
            ps.setString(3, aux.getNombres());
            ps.setString(4, aux.getApellido_paterno());
            ps.setString(5, aux.getApellido_materno());
            ps.setString(6, aux.getDireccion());
            ps.setString(7, aux.getComuna());
            ps.setInt(8, aux.getTelefono());
            ps.setString(9, aux.getEmail());
            ps.setInt(10, aux.getDd_legales());
            ps.setInt(11, aux.getDd_administrativos());
            ps.setDate(12, new java.sql.Date(aux.getFecha_contrato().getTime()));
            ps.setInt(13, aux.getPerfil());
            ps.setInt(14, aux.getCargo());
            ps.setInt(15, aux.getDepartamento());
            ps.setInt(16, aux.getRut());
            
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception  ex) {
            Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            con.cerrarConexion();
        }
        return false;
    }
    public boolean update_Clave(String clave, int rut) {
        try {           
            ps = con.getCnn().prepareStatement(SQL_UPDATE_CLAVE);
            ps.setString(1, clave);
            ps.setInt(2, rut);
            if (ps.executeUpdate() > 0) { return true; }
        } catch (Exception  ex) {
            Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            con.cerrarConexion();
        }
        return false;
    }
    public boolean update_Administrativo(int administrativo, int rut) {
        try {           
            ps = con.getCnn().prepareStatement(SQL_UPDATE_ADMINISTRATIVO);
            ps.setInt(1, administrativo);
            ps.setInt(2, rut);
            if (ps.executeUpdate() > 0) { return true; }
        } catch (Exception  ex) {
            Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            con.cerrarConexion();
        }
        return false;
    }
    public boolean update_Legal(int legal, int rut) {
        try {           
            ps = con.getCnn().prepareStatement(SQL_UPDATE_LEGAL);
            ps.setInt(1, legal);
            ps.setInt(2, rut);
            if (ps.executeUpdate() > 0) { return true; }
        } catch (Exception  ex) {
            Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            con.cerrarConexion();
        }
        return false;
    }
    public UsuarioDTO read(Object key) {
        ResultSet rs;
        UsuarioDTO usuario = null;
        try {
            ps = con.getCnn().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario = new UsuarioDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getInt(11), rs.getInt(12), rs.getDate(13) , rs.getInt(14), rs.getInt(15), rs.getInt(16));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuario;
    }
    public ArrayList<UsuarioDTO> readAll() {
        ArrayList<UsuarioDTO> usuarios = null;
        try {
            usuarios = new ArrayList<>();            
            ps = con.getCnn().prepareStatement(SQL_READALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                usuarios.add(new UsuarioDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getInt(11), rs.getInt(12), rs.getDate(13) , rs.getInt(14), rs.getInt(15), rs.getInt(16)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuarios;
    }
    public UsuarioDTO read_Perfil_Departamento(Object key1, Object key2) {
        ResultSet rs;
        UsuarioDTO usuario = null;
        try {
            ps = con.getCnn().prepareStatement(SQL_READ_PERFIL_DEPARTAMENTO);
            ps.setString(1, key1.toString());
            ps.setString(2, key2.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario = new UsuarioDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10), rs.getInt(11), rs.getInt(12), rs.getDate(13) , rs.getInt(14), rs.getInt(15), rs.getInt(16));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuario;
    }
}
