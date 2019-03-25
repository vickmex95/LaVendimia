package com.example.victorantonio.lavendimia.Models;

public class Venta {
    private Integer clave;
    private Integer cliente_id;
    private Integer nombre_cliente;
    private Float total;
    private Integer fecha;
    private Venta venta;

    public Venta(Integer clave, Integer cliente_id, Integer nombre_cliente, Float total, Integer fecha) {
        this.clave = clave;
        this.cliente_id = cliente_id;
        this.nombre_cliente = nombre_cliente;
        this.total = total;
        this.fecha = fecha;
    }

    public Venta(){

    }

    public Venta(Venta venta){
        this.venta = venta;
    }

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public Integer getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Integer cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Integer getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(Integer nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Integer getFecha() {
        return fecha;
    }

    public void setFecha(Integer fecha) {
        this.fecha = fecha;
    }
}
