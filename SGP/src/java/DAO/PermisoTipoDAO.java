package DAO;

import Conexion.Conexion;
import DTO.PermisoTipoDTO;
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
public class PermisoTipoDAO {
    
    PreparedStatement ps;
    
    private static final String SQL_READ = "SELECT * FROM PERMISO_TIPOS WHERE ID_TIPO = ?";
    private static final String SQL_READALL = "SELECT * FROM PERMISO_TIPOS";
    
    private static final Conexion con = Conexion.iniciarConexion();
    
    public PermisoTipoDTO read(Object key) {
        ResultSet rs;
        PermisoTipoDTO permisoTipo = null;
        try {
            ps = con.getCnn().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                permisoTipo = new PermisoTipoDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PermisoTipoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoTipoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return permisoTipo;
    }
    
    public ArrayList<PermisoTipoDTO> readAll() {
        ArrayList<PermisoTipoDTO> listado = null;
        try {
            listado = new ArrayList<>();            
            ps = con.getCnn().prepareStatement(SQL_READALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listado.add(new PermisoTipoDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PermisoTipoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(PermisoTipoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listado;
    }
}
