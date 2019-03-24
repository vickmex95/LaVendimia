package com.example.victorantonio.lavendimia.Utils;

public class Utils {

    //Constantes campos tabla clientes:
    public static final String TABLA_CLIENTE = "clientes";
    public static final String clave_cliente = "clave";
    public static final String nombre_cliente = "nombre";
    public static final String apellido_pat_cliente = "apellido_pat";
    public static final String apellido_mat_cliente = "apellido_mat";
    public static final String rfc_cliente = "rfc";


    public static final String CREAR_TABLA_CLIENTE = "CREATE TABLE " +
             TABLA_CLIENTE +" ("+clave_cliente +
            " INTEGER, "+nombre_cliente+" TEXT,"+apellido_pat_cliente+" TEXT,"
            +apellido_mat_cliente+" TEXT,"+rfc_cliente+" TEXT)";

}
