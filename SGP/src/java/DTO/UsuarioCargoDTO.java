package DTO;

/**
 *
 * @author Fernando Chata
 */
public class UsuarioCargoDTO {
    
    private int id_cargo;
    private String cargo;
    private String descripcion;

    public UsuarioCargoDTO() {
    }

    public UsuarioCargoDTO(int id_cargo, String cargo, String descripcion) {
        this.id_cargo = id_cargo;
        this.cargo = cargo;
        this.descripcion = descripcion;
    }

    public int getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Usuario_Cargo{" + "id_cargo=" + id_cargo + ", cargo=" + cargo + ", descripcion=" + descripcion + '}';
    }
}
