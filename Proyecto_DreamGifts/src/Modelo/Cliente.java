package Modelo;

import java.util.Date;

public class Cliente {
    private int id;
    private String nombre;
    private String Rut;
    private Date nacimiento;
    private boolean estado;
    private String celular;
    private String direccion;
    private String RedSocial;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return Rut;
    }

    public void setRut(String Rut) {
        this.Rut = Rut;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRedSocial() {
        return RedSocial;
    }

    public void setRedSocial(String RedSocial) {
        this.RedSocial = RedSocial;
    }
    
    
    
    
}
