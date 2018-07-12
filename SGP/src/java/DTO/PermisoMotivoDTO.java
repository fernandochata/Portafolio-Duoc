package DTO;

/**
 *
 * @author Fernando Chata
 */
public class PermisoMotivoDTO {
    
    private int id_motivo;
    private String motivo;

    public PermisoMotivoDTO() {
    }

    public PermisoMotivoDTO(int id_motivo, String motivo) {
        this.id_motivo = id_motivo;
        this.motivo = motivo;
    }

    public int getId_motivo() {
        return id_motivo;
    }

    public void setId_motivo(int id_motivo) {
        this.id_motivo = id_motivo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "Permiso_Motivo{" + "id_motivo=" + id_motivo + ", motivo=" + motivo + '}';
    }
    
    
}
