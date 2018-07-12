package DAO;

import Conexion.Conexion;
import DTO.PermisoEstadoDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando Chata
 */
public class PermisoEstadoDAO {
    
    PreparedStatement ps;
    
    private static final String SQL_READ = "SELECT * FROM PERMISO_ESTADOS WHERE ID_ESTADO = ?";
    private static final String SQL_READALL = "SELECT * FROM PERMISO_ESTADOS";
    
    private static final Conexion con = Conexion.iniciarConexion();
    
    
    public PermisoEstadoDTO read(Object key) {
        ResultSet resultado;
        PermisoEstadoDTO permisoEstado = null;
        try {
            ps = con.getCnn().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            resultado = ps.executeQuery();
            while (resultado.next()) {
                permisoEstado = new PermisoEstadoDTO(resultado.getInt(1), resultado.getString(2), resultado.getString(3));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(PermisoEstadoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoEstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return permisoEstado;
    }
    
    public ArrayList<PermisoEstadoDTO> readAll() {
        ArrayList<PermisoEstadoDTO> listado = null;
        try {
            listado = new ArrayList<>();            
            ps = con.getCnn().prepareStatement(SQL_READALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listado.add(new PermisoEstadoDTO(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PermisoEstadoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoEstadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listado;
    }
    
}
