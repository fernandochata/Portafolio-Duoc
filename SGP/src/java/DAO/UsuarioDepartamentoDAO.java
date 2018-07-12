package DAO;

import Conexion.Conexion;
import DTO.UsuarioDepartamentoDTO;
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
public class UsuarioDepartamentoDAO {
    
    PreparedStatement ps;
    
    private static final String SQL_READ = "SELECT * FROM USUARIO_DEPARTAMENTOS WHERE ID_DEPARTAMENTO = ?";
    private static final String SQL_READALL = "SELECT * FROM USUARIO_DEPARTAMENTOS";
    //private static final String SQL_READALL = "SELECT * FROM USUARIO_DEPARTAMENTOS WHERE ";
    
    private static final Conexion con = Conexion.iniciarConexion();
    
    public UsuarioDepartamentoDTO read(Object key) {
        ResultSet rs;
        UsuarioDepartamentoDTO usuarioDepartamento = null;
        try {
            ps = con.getCnn().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                usuarioDepartamento = new UsuarioDepartamentoDTO(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDepartamentoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuarioDepartamento;
    }
    
    public ArrayList<UsuarioDepartamentoDTO> readAll() {
        ArrayList<UsuarioDepartamentoDTO> listado = null;
        try {
            listado = new ArrayList<>();            
            ps = con.getCnn().prepareStatement(SQL_READALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listado.add(new UsuarioDepartamentoDTO(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDepartamentoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listado;
    }
}
