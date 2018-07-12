package DTO;

/**
 *
 * @author Fernando Chata
 */
public class PermisoEstadoDTO {
    
    private int id_estado;
    private String estado;
    private String descripcion;

    public PermisoEstadoDTO() {
    }

    public PermisoEstadoDTO(int id_estado, String estado, String descripcion) {
        this.id_estado = id_estado;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Permiso_Estado{" + "id_estado=" + id_estado + ", estado=" + estado + ", descripcion=" + descripcion + '}';
    }
}
