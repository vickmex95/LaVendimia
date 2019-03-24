package com.example.victorantonio.lavendimia.Models;

public class Cliente {

    private Integer clave;
    private String nombre;
    private String apellido_pat;
    private String apellido_mat;
    private String rfc;

    public Cliente(Integer clave, String nombre, String apellido_pat, String apellido_mat, String rfc) {
        this.clave = clave;
        this.nombre = nombre;
        this.apellido_pat = apellido_pat;
        this.apellido_mat = apellido_mat;
        this.rfc = rfc;
    }

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_pat() {
        return apellido_pat;
    }

    public void setApellido_pat(String apellido_pat) {
        this.apellido_pat = apellido_pat;
    }

    public String getApellido_mat() {
        return apellido_mat;
    }

    public void setApellido_mat(String apellido_mat) {
        this.apellido_mat = apellido_mat;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
}
