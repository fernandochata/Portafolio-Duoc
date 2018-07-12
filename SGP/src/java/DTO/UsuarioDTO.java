package DTO;

import java.util.Date;

public class UsuarioDTO {
    
    private int rut;
    private String dv;
    private String clave;
    private String nombres;
    private String apellido_paterno;
    private String apellido_materno;
    private String direccion;
    private String comuna;
    private int telefono;
    private String email;
    private int dd_legales;
    private int dd_administrativos;
    private Date fecha_contrato;
    private int perfil;
    private int cargo;
    private int departamento;

    public UsuarioDTO() {
    }

    public UsuarioDTO(int rut, String dv, String clave, String nombres, String apellido_paterno, String apellido_materno, String direccion, String comuna, int telefono, String email, int dd_legales, int dd_administrativos, Date fecha_contrato, int perfil, int cargo, int departamento) {
        this.rut = rut;
        this.dv = dv;
        this.clave = clave;
        this.nombres = nombres;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.direccion = direccion;
        this.comuna = comuna;
        this.telefono = telefono;
        this.email = email;
        this.dd_legales = dd_legales;
        this.dd_administrativos = dd_administrativos;
        this.fecha_contrato = fecha_contrato;
        this.perfil = perfil;
        this.cargo = cargo;
        this.departamento = departamento;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDd_legales() {
        return dd_legales;
    }

    public void setDd_legales(int dd_legales) {
        this.dd_legales = dd_legales;
    }

    public int getDd_administrativos() {
        return dd_administrativos;
    }

    public void setDd_administrativos(int dd_administrativos) {
        this.dd_administrativos = dd_administrativos;
    }

    public Date getFecha_contrato() {
        return fecha_contrato;
    }

    public void setFecha_contrato(Date fecha_contrato) {
        this.fecha_contrato = fecha_contrato;
    }
    /**
     * Devuelve el id_perfil de la tabla USUARIO_PERFIL
     * @param perfil  
     */
    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" + "rut=" + rut + ", dv=" + dv + ", clave=" + clave + ", nombres=" + nombres + ", apellido_paterno=" + apellido_paterno + ", apellido_materno=" + apellido_materno + ", direccion=" + direccion + ", comuna=" + comuna + ", telefono=" + telefono + ", email=" + email + ", dd_legales=" + dd_legales + ", dd_administrativos=" + dd_administrativos + ", fecha_contrato=" + fecha_contrato + ", perfil=" + perfil + ", cargo=" + cargo + ", departamento=" + departamento + '}';
    }
    
    
}
