package com.example.victorantonio.lavendimia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.victorantonio.lavendimia.Utils.Utils;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utils.CREAR_TABLA_CLIENTE);
        db.execSQL(Utils.CREAR_TABLA_ARTICULO);
        db.execSQL(Utils.CREAR_TABLA_CONFIGURACION);
        db.execSQL(Utils.CREAR_TABLA_ABONO_MENSUAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLA_CLIENTE);
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLA_ARTICULO);
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLA_CONFIGURACION);
        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLA_ABONOS_MENSUAL);
        onCreate(db);
    }
}
