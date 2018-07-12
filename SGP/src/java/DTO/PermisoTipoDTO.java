package DTO;

/**
 *
 * @author Fernando Chata
 */
public class PermisoTipoDTO {
    private int id_tipo;
    private String tipo;
    private int dias;
    private String descripcion;
    private int estado_tipo;

    public PermisoTipoDTO() {
    }

    public PermisoTipoDTO(int id_tipo, String tipo, int dias, String descripcion, int estado_tipo) {
        this.id_tipo = id_tipo;
        this.tipo = tipo;
        this.dias = dias;
        this.descripcion = descripcion;
        this.estado_tipo = estado_tipo;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado_tipo() {
        return estado_tipo;
    }

    public void setEstado_tipo(int estado_tipo) {
        this.estado_tipo = estado_tipo;
    }

    @Override
    public String toString() {
        return "Permiso_Tipo{" + "id_tipo=" + id_tipo + ", tipo=" + tipo + ", dias=" + dias + ", descripcion=" + descripcion + ", estado_tipo=" + estado_tipo + '}';
    }
}
