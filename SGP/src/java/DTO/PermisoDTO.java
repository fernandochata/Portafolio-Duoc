package DTO;

import java.util.Date;

/**
 *
 * @author Fernando Chata
 */
public class PermisoDTO {
    
    private int id_permiso;
    private Date fecha_creacion;
    private Date fecha_desde;
    private Date fecha_hasta;
    private int dias;
    private String observacion;
    private int usuario;
    private int resolucion;
    private int adjunto;
    private int estado;
    private int tipo;
    private int motivo;

    public PermisoDTO() {
    }

    public PermisoDTO(int id_permiso, Date fecha_creacion, Date fecha_desde, Date fecha_hasta, int dias, String observacion, int usuario, int resolucion, int adjunto, int estado, int tipo, int motivo) {
        this.id_permiso = id_permiso;
        this.fecha_creacion = fecha_creacion;
        this.fecha_desde = fecha_desde;
        this.fecha_hasta = fecha_hasta;
        this.dias = dias;
        this.observacion = observacion;
        this.usuario = usuario;
        this.resolucion = resolucion;
        this.adjunto = adjunto;
        this.estado = estado;
        this.tipo = tipo;
        this.motivo = motivo;
    }

    public int getId_permiso() {
        return id_permiso;
    }

    public void setId_permiso(int id_permiso) {
        this.id_permiso = id_permiso;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Date getFecha_desde() {
        return fecha_desde;
    }

    public void setFecha_desde(Date fecha_desde) {
        this.fecha_desde = fecha_desde;
    }

    public Date getFecha_hasta() {
        return fecha_hasta;
    }

    public void setFecha_hasta(Date fecha_hasta) {
        this.fecha_hasta = fecha_hasta;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getResolucion() {
        return resolucion;
    }

    public void setResolucion(int resolucion) {
        this.resolucion = resolucion;
    }

    public int getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(int adjunto) {
        this.adjunto = adjunto;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getMotivo() {
        return motivo;
    }

    public void setMotivo(int motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "PermisoDTO{" + "id_permiso=" + id_permiso + ", fecha_creacion=" + fecha_creacion + ", fecha_desde=" + fecha_desde + ", fecha_hasta=" + fecha_hasta + ", dias=" + dias + ", observacion=" + observacion + ", usuario=" + usuario + ", resolucion=" + resolucion + ", adjunto=" + adjunto + ", estado=" + estado + ", tipo=" + tipo + ", motivo=" + motivo + '}';
    }

   
    
}
