package DAO;

import Conexion.Conexion;
import DTO.FeriadoDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeriadoDAO {
    
    PreparedStatement ps;
    
    private static final String SQL_READ = "SELECT FERIADO FROM FERIADOS";
    private static final String SQL_READALL = "SELECT * FROM FERIADOS";
    
    private static final Conexion con = Conexion.iniciarConexion();
    
    public List<Date> read() {
        List<Date> lista = null;
        try {
            lista = new ArrayList<Date>();
            ps = con.getCnn().prepareStatement(SQL_READ);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getDate(1));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FeriadoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(FeriadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    public ArrayList<FeriadoDTO> readAll() {
        ArrayList<FeriadoDTO> listado = null;
        try {
            listado = new ArrayList<>();            
            ps = con.getCnn().prepareStatement(SQL_READALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listado.add(new FeriadoDTO(rs.getInt(1), rs.getDate(2), rs.getString(3)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(FeriadoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            con.cerrarConexion();
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(FeriadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listado;
    }
    
}
