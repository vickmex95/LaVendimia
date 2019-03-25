package com.example.victorantonio.lavendimia.Utils;

public class Utils {

    //Constantes campos tabla clientes:
    public static final String TABLA_CLIENTE = "clientes";
    public static final String clave_cliente = "clave";
    public static final String nombre_cliente = "nombre";
    public static final String apellido_pat_cliente = "apellido_pat";
    public static final String apellido_mat_cliente = "apellido_mat";
    public static final String rfc_cliente = "rfc";
    public static final String venta_id_cte = "venta_id";


    public static final String CREAR_TABLA_CLIENTE = "CREATE TABLE " +
             TABLA_CLIENTE +" ("+clave_cliente +
            " INTEGER PRIMARY KEY AUTOINCREMENT, "+nombre_cliente+" TEXT,"+apellido_pat_cliente+" TEXT,"
            +apellido_mat_cliente+" TEXT,"+rfc_cliente+" TEXT " + venta_id_cte + " INTEGER)";


    public static final String TABLA_ARTICULO = "articulos";
    public static final String clave_articulo = "clave";
    public static final String nombre_articulo = "nombre";
    public static final String modelo_articulo = "modelo";
    public static final String precio_articulo = "precio";
    public static final String existencia_articulo = "existencia";

    public static final String CREAR_TABLA_ARTICULO = "CREATE TABLE " +
            TABLA_ARTICULO +" ("+clave_articulo +
            " INTEGER PRIMARY KEY AUTOINCREMENT, "+nombre_articulo+" TEXT,"+modelo_articulo+" TEXT,"
            +precio_articulo+" FLOAT,"+existencia_articulo+" INTEGER)";

}
