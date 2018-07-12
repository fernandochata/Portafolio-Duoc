package DTO;

/**
 *
 * @author Fernando Chata
 */
public class UsuarioPerfilDTO {
    
    private int id_perfil;
    private String perfil;
    private String descripcion;

    public UsuarioPerfilDTO() {
    }

    public UsuarioPerfilDTO(int id_perfil, String perfil, String descripcion) {
        this.id_perfil = id_perfil;
        this.perfil = perfil;
        this.descripcion = descripcion;
    }

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Usuario_Perfil{" + "id_perfil=" + id_perfil + ", perfil=" + perfil + ", descripcion=" + descripcion + '}';
    }
}
