package com.example.victorantonio.lavendimia;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.victorantonio.lavendimia.Utils.Utils;


public class RegistroClientesActivity extends AppCompatActivity {

    EditText edt_clave,edt_nombre,edt_apellido_pat, edt_apellido_mat, edt_rfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_clientes);

        edt_clave= (EditText) findViewById(R.id.edt_clave);
        edt_nombre= (EditText) findViewById(R.id.edt_nombre);
        edt_apellido_pat= (EditText) findViewById(R.id.edt_apellido_pat);
        edt_apellido_mat= (EditText) findViewById(R.id.edt_apellido_mat);
        edt_rfc= (EditText) findViewById(R.id.edt_rfc);

    }

    public void onClick(View view) {
        registrarUsuarios();
        //registrarUsuariosSql();
    }

//    private void registrarUsuariosSql() {
//        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
//
//        SQLiteDatabase db=conn.getWritableDatabase();
//
//        //insert into usuario (id,nombre,telefono) values (123,'Cristian','85665223')
//
//        String insert="INSERT INTO "+Utilidades.TABLA_USUARIO
//                +" ( " +Utilidades.CAMPO_ID+","+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+")" +
//                " VALUES ("+campoId.getText().toString()+", '"+campoNombre.getText().toString()+"','"
//                +campoTelefono.getText().toString()+"')";
//
//        db.execSQL(insert);
//
//
//        db.close();
//    }


    private void registrarUsuarios() {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_la_vendimia",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utils.clave_cliente,edt_clave.getText().toString());
        values.put(Utils.nombre_cliente,edt_nombre.getText().toString());
        values.put(Utils.apellido_pat_cliente,edt_apellido_pat.getText().toString());
        values.put(Utils.apellido_mat_cliente,edt_apellido_mat.getText().toString());
        values.put(Utils.rfc_cliente,edt_rfc.getText().toString());

        Long idResultante=db.insert(Utils.TABLA_CLIENTE,Utils.clave_cliente,values);

        Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
    }
}