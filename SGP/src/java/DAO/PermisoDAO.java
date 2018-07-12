package DAO;

import Conexion.Conexion;
import DTO.PermisoDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PermisoDAO {
    
    PreparedStatement ps;
    
    private static final String SQL_INSERT = "INSERT INTO PERMISOS VALUES (SEQ_PERMISOS.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE PERMISOS SET fecha_creacion=?, fecha_desde=?, fecha_hasta=?, dias=?, observacion=?, usuario=?, resolucion=?, adjunto=?, estado=?, tipo=?, motivo=? WHERE id_permiso = ?";
    private static final String SQL_READ = "SELECT * FROM PERMISOS WHERE ID_PERMISO = ?";
    private static final String SQL_READALL = "SELECT * FROM PERMISOS ORDER BY FECHA_CREACION";
    
    private static final String SQL_READ_RESOLUCION = "SELECT * FROM PERMISOS WHERE RESOLUCION=? ORDER BY FECHA_CREACION";
    private static final String SQL_READALL_USUARIO = "SELECT * FROM PERMISOS WHERE USUARIO=?";
    private static final String SQL_READALL_ESTADO = "SELECT * FROM PERMISOS WHERE ESTADO=? ORDER BY FECHA_CREACION";
    private static final String SQL_READALL_DEPARTAMENTO = "SELECT P.ID_PERMISO, P.FECHA_CREACION, P.FECHA_DESDE, P.FECHA_HASTA, P.DIAS, P.OBSERVACION, P.USUARIO, P.RESOLUCION, P.ADJUNTO, P.ESTADO, P.TIPO, P.MOTIVO FROM PERMISOS P INNER JOIN USUARIOS U ON U.RUT = P.USUARIO WHERE U.DEPARTAMENTO = ? ORDER BY FECHA_CREACION";
    private static final String SQL_READALL_ESTADO_DEPARTAMENTO = "SELECT P.ID_PERMISO, P.FECHA_CREACION, P.FECHA_DESDE, P.FECHA_HASTA, P.DIAS, P.OBSERVACION, P.USUARIO, P.RESOLUCION, P.ADJUNTO, P.ESTADO, P.TIPO, P.MOTIVO FROM PERMISOS P INNER JOIN USUARIOS U ON U.RUT = P.USUARIO WHERE P.ESTADO = ? AND U.DEPARTAMENTO = ? ORDER BY FECHA_CREACION";
    private static final String SQL_SUM_USUARIO_ESTADO_TIPO = "SELECT SUM(DIAS) FROM PERMISOS WHERE USUARIO=? AND ESTADO=? AND TIPO=? ORDER BY FECHA_CREACION";
    
    private static final Conexion con = Conexion.iniciarConexion();
    
    public boolean create(PermisoDTO aux) { 
        try {          
            ps = con.getCnn().prepareStatement(SQL_INSERT);
            
            ps.setDate(1, new java.sql.Date(aux.getFecha_creacion().getTime()));
            ps.setDate(2, new java.sql.Date(aux.getFecha_desde().getTime()));
            ps.setDate(3, new java.sql.Date(aux.getFecha_hasta().getTime()));
            ps.setInt(4, aux.getDias());
            ps.setString(5, aux.getObservacion());
            ps.setInt(6, aux.getUsuario());
            ps.setInt(7, aux.getResolucion());
            ps.setInt(8, aux.getAdjunto());
            ps.setInt(9, aux.getEstado());
            ps.setInt(10, aux.getTipo());
            ps.setInt(11, aux.getMotivo());
            
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    public boolean update(PermisoDTO aux) {
        try {           
            ps = con.getCnn().prepareStatement(SQL_UPDATE);
            
            ps.setDate(1, new java.sql.Date(aux.getFecha_creacion().getTime()));
            ps.setDate(2, new java.sql.Date(aux.getFecha_desde().getTime()));
            ps.setDate(3, new java.sql.Date(aux.getFecha_hasta().getTime()));
            ps.setInt(4, aux.getDias());
            ps.setString(5, aux.getObservacion());
            ps.setInt(6, aux.getUsuario());
            ps.setInt(7, aux.getResolucion());
            ps.setInt(8, aux.getAdjunto());
            ps.setInt(9, aux.getEstado());
            ps.setInt(10, aux.getTipo());
            ps.setInt(11, aux.getMotivo());
            ps.setInt(12, aux.getId_permiso());
            
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    public PermisoDTO read(Object key) {
        PermisoDTO permiso = null;
        try {
            ps = con.getCnn().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                permiso = new PermisoDTO(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDate(4), rs.getInt(5) , rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return permiso;
    }
    public ArrayList<PermisoDTO> readAll() {
        ArrayList<PermisoDTO> listado = null;
        try {
            listado = new ArrayList<>();            
            ps = con.getCnn().prepareStatement(SQL_READALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listado.add(new PermisoDTO(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDate(4), rs.getInt(5) , rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PermisoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listado;
    }
    public ArrayList<PermisoDTO> readAll_Rut(Object key) {
        ArrayList<PermisoDTO> listado = null;
        try {
            listado = new ArrayList<>();            
            ps = con.getCnn().prepareStatement(SQL_READALL_USUARIO);
            ps.setString(1, key.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listado.add(new PermisoDTO(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDate(4), rs.getInt(5) , rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PermisoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listado;
    }
    public ArrayList<PermisoDTO> readAll_Estado(Object key) {
        ArrayList<PermisoDTO> listado = null;
        try {
            listado = new ArrayList<>();            
            ps = con.getCnn().prepareStatement(SQL_READALL_ESTADO);
            ps.setString(1, key.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listado.add(new PermisoDTO(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDate(4), rs.getInt(5) , rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PermisoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listado;
    }
    public ArrayList<PermisoDTO> readAll_Departamento(Object key) {
        ArrayList<PermisoDTO> listado = null;
        try {
            listado = new ArrayList<>();            
            ps = con.getCnn().prepareStatement(SQL_READALL_DEPARTAMENTO);
            ps.setString(1, key.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listado.add(new PermisoDTO(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDate(4), rs.getInt(5) , rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PermisoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listado;
    }
    public ArrayList<PermisoDTO> readAll_Estado_Departamento(Object key1, Object key2) {
        ArrayList<PermisoDTO> listado = null;
        try {
            listado = new ArrayList<>();            
            ps = con.getCnn().prepareStatement(SQL_READALL_ESTADO_DEPARTAMENTO);
            ps.setString(1, key1.toString());
            ps.setString(2, key2.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listado.add(new PermisoDTO(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDate(4), rs.getInt(5) , rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PermisoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listado;
    }
    public int sum_Usuario_Estado_Tipo(Object key1, Object key2, Object key3) {
        int entero = 0;
        try {
            ps = con.getCnn().prepareStatement(SQL_SUM_USUARIO_ESTADO_TIPO);
            ps.setString(1, key1.toString());
            ps.setString(2, key2.toString());
            ps.setString(3, key3.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                entero = rs.getInt(1);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PermisoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return entero;
    }
    public PermisoDTO read_Resolucion(Object key) {
        PermisoDTO permiso = null;
        try {
            ps = con.getCnn().prepareStatement(SQL_READ_RESOLUCION);
            ps.setString(1, key.toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                permiso = new PermisoDTO(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getDate(4), rs.getInt(5) , rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return permiso;
    }
}
