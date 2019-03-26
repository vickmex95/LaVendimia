package com.example.victorantonio.lavendimia.Models;

public class AbonosMensuales {

    private Integer id_abono;
    private String descripcion;
    private AbonosMensuales abonos_mensuales;

    public AbonosMensuales(Integer id_abono, String descripcion) {
        this.id_abono = id_abono;
        this.descripcion = descripcion;
    }

    public AbonosMensuales(){

    }

    public AbonosMensuales(AbonosMensuales abonos_mensuales){
        this.abonos_mensuales = abonos_mensuales;
    }

    public Integer getId_abono() {
        return id_abono;
    }

    public void setId_abono(Integer id_abono) {
        this.id_abono = id_abono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
