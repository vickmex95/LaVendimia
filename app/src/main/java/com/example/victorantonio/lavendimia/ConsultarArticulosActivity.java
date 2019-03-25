package com.example.victorantonio.lavendimia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.victorantonio.lavendimia.Utils.Utils;

public class ConsultarArticulosActivity extends AppCompatActivity {

    EditText edt_clave,edt_nombre,edt_modelo,edt_precio,edt_existencia;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_articulos);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_la_vendimia",null,1);

        edt_clave= (EditText) findViewById(R.id.edt_clave);
        edt_nombre= (EditText) findViewById(R.id.edt_nombre);
        edt_modelo= (EditText) findViewById(R.id.edt_modelo);
        edt_precio= (EditText) findViewById(R.id.edt_precio);
        edt_existencia= (EditText) findViewById(R.id.edt_existencia);

        Bundle articulo_enviado = getIntent().getExtras();
        Integer articulo_id;
        if(articulo_enviado != null){
            articulo_id = (Integer) articulo_enviado.getSerializable("articulo_id");
            consultar(articulo_id);
        }


    }

    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnConsultar:
                consultar();
                //consultarSql();
                break;
            case R.id.btnActualizar: actualizarArticulo();
                break;
            case R.id.btnEliminar: eliminarArticulo();
                break;
        }

    }

    private void eliminarArticulo() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={edt_clave.getText().toString()};

        db.delete(Utils.TABLA_ARTICULO,Utils.clave_articulo+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se Eliminó el articulo",Toast.LENGTH_LONG).show();
        edt_clave.setText("");
        limpiar();
        db.close();
    }

    private void actualizarArticulo() {
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={edt_clave.getText().toString()};
        ContentValues values=new ContentValues();
        values.put(Utils.nombre_articulo,edt_nombre.getText().toString());
        values.put(Utils.modelo_articulo,edt_modelo.getText().toString());
        values.put(Utils.precio_articulo,edt_precio.getText().toString());
        values.put(Utils.existencia_articulo,edt_existencia.getText().toString());

        db.update(Utils.TABLA_ARTICULO,values,Utils.clave_articulo+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se actualizó el articulo",Toast.LENGTH_LONG).show();
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
        String[] campos={Utils.nombre_articulo,Utils.modelo_articulo, Utils.precio_articulo, Utils.existencia_articulo};

        try {
            Cursor cursor =db.query(Utils.TABLA_ARTICULO,campos,Utils.clave_articulo+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            edt_nombre.setText(cursor.getString(0));
            edt_modelo.setText(cursor.getString(1));
            edt_precio.setText(cursor.getString(2));
            edt_existencia.setText(cursor.getString(3));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El articulo no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }


    }

    private void consultar(Integer articulo_id) {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={articulo_id.toString()};
        String[] campos={Utils.clave_articulo,Utils.nombre_articulo,Utils.modelo_articulo, Utils.precio_articulo, Utils.existencia_articulo};

        try {
            Cursor cursor =db.query(Utils.TABLA_ARTICULO,campos,Utils.clave_articulo+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            edt_clave.setText(cursor.getString(0));
            edt_nombre.setText(cursor.getString(1));
            edt_modelo.setText(cursor.getString(2));
            edt_precio.setText(cursor.getString(3));
            edt_existencia.setText(cursor.getString(4));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El articulo no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }


    }

    private void limpiar() {
        edt_nombre.setText("");
        edt_modelo.setText("");
        edt_precio.setText("");
        edt_existencia.setText("");
    }

}
