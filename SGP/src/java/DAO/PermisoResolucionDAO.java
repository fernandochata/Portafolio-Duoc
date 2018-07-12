package DAO;

import Conexion.Conexion;
import DTO.PermisoResolucionDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PermisoResolucionDAO {
    
    PreparedStatement ps;
    
    private static final String SQL_INSERT = "INSERT INTO PERMISO_RESOLUCIONES (ID_RESOLUCION, CODIGO, DETALLE, RUTA_DOCUMENTO, FECHA_CREACION) VALUES (SEQ_PERMISO_RESOLUCIONES.NEXTVAL, ?, ?, ?, ?)";
    private static final String SQL_READ = "SELECT *  FROM PERMISO_RESOLUCIONES WHERE ID_RESOLUCION = ?";
    private static final String SQL_READALL = "SELECT * FROM PERMISO_RESOLUCIONES";
    
    private static final String SQL_LAST = "SELECT * FROM PERMISO_RESOLUCIONES WHERE ID_RESOLUCION = (SELECT MAX(ID_RESOLUCION) FROM PERMISO_RESOLUCIONES)";
    
    private static final Conexion con = Conexion.iniciarConexion();
    
    public boolean create(PermisoResolucionDTO aux) {
        try {
            ps = con.getCnn().prepareStatement(SQL_INSERT);
            
            ps.setString(1, aux.getCodigo());
            ps.setString(2, aux.getDetalle());
            ps.setString(3, aux.getRuta_documento());
            ps.setDate(4, new java.sql.Date(aux.getFecha_creacion().getTime()));
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermisoResolucionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoResolucionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public PermisoResolucionDTO read(Object key) {
        ResultSet rs;
        PermisoResolucionDTO permisoResolucion = null;
        try {
            ps = con.getCnn().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                permisoResolucion = new PermisoResolucionDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PermisoResolucionDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoResolucionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return permisoResolucion;
    }
    
    public ArrayList<PermisoResolucionDTO> readAll() {
        ArrayList<PermisoResolucionDTO> listado = null;
        try {
            listado = new ArrayList<>();            
            ps = con.getCnn().prepareStatement(SQL_READALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listado.add(new PermisoResolucionDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PermisoResolucionDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoResolucionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listado;
    }
    
    public PermisoResolucionDTO last() {
        ResultSet rs;
        PermisoResolucionDTO permisoResolucion = null;
        try {
            ps = con.getCnn().prepareStatement(SQL_LAST);
            rs = ps.executeQuery();
            while (rs.next()) {
                permisoResolucion = new PermisoResolucionDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PermisoResolucionDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoResolucionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return permisoResolucion;
    }
}
