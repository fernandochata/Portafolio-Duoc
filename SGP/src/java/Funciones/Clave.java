package Funciones;

import DAO.PermisoResolucionDAO;
import DTO.PermisoResolucionDTO;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * 
 * @author Fernando Chata
 * 
 */
public class Clave {
    
    public static String createNewCode(int length){
        String cadenaReferencia = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";        
        
        Random rnd = new Random();
        boolean nuevo = false;
        String newCode = "";
        do{
            StringBuilder sb = new StringBuilder();
            while (sb.length() < length) {
                int index = (int) (rnd.nextFloat() * cadenaReferencia.length());
                sb.append(cadenaReferencia.charAt(index));
            }
            newCode = sb.toString();
            
            ArrayList<PermisoResolucionDTO> listadoResoluciones = new PermisoResolucionDAO().readAll();

            for (PermisoResolucionDTO resolucion : listadoResoluciones) {
                if(resolucion.getCodigo() == newCode){nuevo = true;}
            }
        }while(nuevo);
        return newCode;
    }
    
    public static String createPassword(int length){
        String cadenaReferencia = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";        
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        while (sb.length() < length) {
            int index = (int) (rnd.nextFloat() * cadenaReferencia.length());
            sb.append(cadenaReferencia.charAt(index));
        }
        return sb.toString();
    }
    
}
