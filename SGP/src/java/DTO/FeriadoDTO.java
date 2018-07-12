package DTO;

import java.util.Date;

/**
 *
 * @author Fernando Chata
 */
public class FeriadoDTO {
    
    private int id_feriado;
    private Date feriado;
    private String descripcion;

    public FeriadoDTO() {
    }

    public FeriadoDTO(Date feriado) {
        this.feriado = feriado;
    }

    public FeriadoDTO(int id_feriado, Date feriado, String descripcion) {
        this.id_feriado = id_feriado;
        this.feriado = feriado;
        this.descripcion = descripcion;
    }

    public int getId_feriado() {
        return id_feriado;
    }

    public void setId_feriado(int id_feriado) {
        this.id_feriado = id_feriado;
    }

    public Date getFeriado() {
        return feriado;
    }

    public void setFeriado(Date feriado) {
        this.feriado = feriado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "FeriadoDTO{" + "id_feriado=" + id_feriado + ", feriado=" + feriado + ", descripcion=" + descripcion + '}';
    }
}
