package DAO;

import Conexion.Conexion;
import DTO.UsuarioCargoDTO;
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
public class UsuarioCargoDAO {
    
    PreparedStatement ps;
    
    private static final String SQL_READ = "SELECT * FROM USUARIO_CARGOS WHERE ID_CARGO = ?";
    private static final String SQL_READALL = "SELECT * FROM USUARIO_CARGOS";
    
    private static final Conexion con = Conexion.iniciarConexion();
    
    public UsuarioCargoDTO read(Object key) {
        ResultSet rs;
        UsuarioCargoDTO usuarioCargo = null;
        try {
            ps = con.getCnn().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                usuarioCargo = new UsuarioCargoDTO(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCargoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioCargoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuarioCargo;
    }
    
    public ArrayList<UsuarioCargoDTO> readAll() {
        ArrayList<UsuarioCargoDTO> listado = null;
        try {
            listado = new ArrayList<>();            
            ps = con.getCnn().prepareStatement(SQL_READALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listado.add(new UsuarioCargoDTO(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioCargoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioCargoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listado;
    }
    
}
