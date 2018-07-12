/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paqueteServicio;

import java.sql.CallableStatement;
import java.sql.Connection;
//import java.sql.Date;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author Angelo
 */
@WebService(serviceName = "Antiguedad")
public class Antiguedad {

    /**
     * Web service operation
     */
    
    private Statement instru; //VARIABLE PARA EJECUTAR INSTRUCCIONES
    Conexion c;
    
    public Antiguedad()  throws Exception
    {
        c = new Conexion(); //LLAMAMOS LA CLASE CONEXION
        instru = c.crearInstruccion(); //EJECUTAMOS INSTRUCCION CREAR INSTRUCCION DE LA CLASE CONEXION
    }
    
    @WebMethod(operationName = "operation")
    public Integer operation(@WebParam(name = "numero1") int numero1, @WebParam(name = "numero2") int numero2) {
        //TODO write your implementation code here:
        return numero1 + numero2;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Antiguedad")
    public Integer Antiguedad(@WebParam(name = "fechaContrato") String fechaContrato) {
        int diasLegales = 0;
        try
        {
            CallableStatement proc = c.getConexion().prepareCall("CALL PR_OBTENER_DIAS_LEGALESV2(?,?)");
            proc.setString("p_fecha_contrato",fechaContrato);
            proc.registerOutParameter("p_dias_legales",Types.NUMERIC);
            proc.execute();
            diasLegales = proc.getInt("p_dias_legales");
        }catch(Exception ex)
        {
            //throw new Exception(ex.getMessage());
        }
        return diasLegales;
    }

}
