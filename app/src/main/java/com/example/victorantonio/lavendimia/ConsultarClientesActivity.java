package com.example.victorantonio.lavendimia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.victorantonio.lavendimia.Utils.Utils;


public class ConsultarClientesActivity extends AppCompatActivity {

    EditText edt_clave,edt_nombre,edt_apellido_pat,edt_apellido_mat,edt_rfc;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_clientes);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_la_vendimia",null,1);

        edt_clave= (EditText) findViewById(R.id.edt_clave);
        edt_nombre= (EditText) findViewById(R.id.edt_nombre);
        edt_apellido_pat= (EditText) findViewById(R.id.edt_apellido_pat);
        edt_apellido_mat= (EditText) findViewById(R.id.edt_apellido_mat);
        edt_rfc= (EditText) findViewById(R.id.edt_rfc);

        Bundle cliente_enviado = getIntent().getExtras();
        Integer cliente_id;
        if(cliente_enviado != null){
            cliente_id = (Integer) cliente_enviado.getSerializable("cliente_id");
            consultar(cliente_id);
        }
    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnConsultar:
                consultar();
                //consultarSql();
                break;
            case R.id.btnActualizar: actualizarCliente();
                break;
            case R.id.btnEliminar: eliminarCliente();
                break;
        }

    }

    private void eliminarCliente() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={edt_clave.getText().toString()};

        db.delete(Utils.TABLA_CLIENTE,Utils.clave_cliente+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se Eliminó el cliente",Toast.LENGTH_LONG).show();
        edt_clave.setText("");
        limpiar();
        db.close();
    }

    private void actualizarCliente() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={edt_clave.getText().toString()};
        ContentValues values=new ContentValues();
        values.put(Utils.nombre_cliente,edt_nombre.getText().toString());
        values.put(Utils.apellido_pat_cliente,edt_apellido_pat.getText().toString());
        values.put(Utils.apellido_mat_cliente,edt_apellido_mat.getText().toString());
        values.put(Utils.rfc_cliente,edt_rfc.getText().toString());

        db.update(Utils.TABLA_CLIENTE,values,Utils.clave_cliente+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se actualizó el cliente",Toast.LENGTH_LONG).show();
        db.close();

    }

//    private void consultarSql() {
//        SQLiteDatabase db=conn.getReadableDatabase();
//        String[] parametros={campoId.getText().toString()};
//
//        try {
//            //select nombre,telefono from usuario where codigo=?
//            Cursor cursor=db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+
//                    " FROM "+Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.CAMPO_ID+"=? ",parametros);
//
//            cursor.moveToFirst();
//            campoNombre.setText(cursor.getString(0));
//            campoTelefono.setText(cursor.getString(1));
//
//        }catch (Exception e){
//            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
//            limpiar();
//        }
//
//    }

    private void consultar() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={edt_clave.getText().toString()};
        String[] campos={Utils.nombre_cliente,Utils.apellido_pat_cliente, Utils.apellido_mat_cliente, Utils.rfc_cliente};

        try {
            Cursor cursor =db.query(Utils.TABLA_CLIENTE,campos,Utils.clave_cliente+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            edt_nombre.setText(cursor.getString(0));
            edt_apellido_pat.setText(cursor.getString(1));
            edt_apellido_mat.setText(cursor.getString(2));
            edt_rfc.setText(cursor.getString(3));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El cliente no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }


    }

    private void consultar(Integer cliente_id) {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={cliente_id.toString()};
        String[] campos={Utils.clave_cliente,Utils.nombre_cliente,Utils.apellido_pat_cliente, Utils.apellido_mat_cliente, Utils.rfc_cliente};

        try {
            Cursor cursor =db.query(Utils.TABLA_CLIENTE,campos,Utils.clave_cliente+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            edt_clave.setText(cursor.getString(0));
            edt_nombre.setText(cursor.getString(1));
            edt_apellido_pat.setText(cursor.getString(2));
            edt_apellido_mat.setText(cursor.getString(3));
            edt_rfc.setText(cursor.getString(4));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El cliente no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }


    }

    private void limpiar() {
        edt_nombre.setText("");
        edt_apellido_pat.setText("");
        edt_apellido_mat.setText("");
        edt_rfc.setText("");
    }

}
