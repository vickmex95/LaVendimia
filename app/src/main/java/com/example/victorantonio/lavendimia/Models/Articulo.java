package com.example.victorantonio.lavendimia.Models;

import java.io.Serializable;

public class Articulo implements Serializable {

    private Integer clave;
    private String descripcion;
    private String modelo;
    private Float precio;
    private Integer existencia;
    private Articulo articulo;

    public Articulo(Integer clave, String descripcion, String modelo, Float precio, Integer existencia) {
        this.clave = clave;
        this.descripcion = descripcion;
        this.modelo = modelo;
        this.precio = precio;
        this.existencia = existencia;
    }

    public Articulo(){

    }

    public Articulo(Articulo articulo){
        this.articulo = articulo;
    }

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }
}
