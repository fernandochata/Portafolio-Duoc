package DTO;

/**
 *
 * @author Fernando Chata
 */
public class UsuarioDepartamentoDTO {
    
    private int id_departamento;
    private String departamento;
    private String descripcion;

    public UsuarioDepartamentoDTO() {
    }

    public UsuarioDepartamentoDTO(int id_departamento, String departamento, String descripcion) {
        this.id_departamento = id_departamento;
        this.departamento = departamento;
        this.descripcion = descripcion;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Usuario_Departamento{" + "id_departamento=" + id_departamento + ", departamento=" + departamento + ", descripcion=" + descripcion + '}';
    }
}
