package com.example.victorantonio.lavendimia;

import android.content.ContentValues;
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

public class RegistroArticulosActivity extends AppCompatActivity {

    EditText edt_nombre,edt_modelo, edt_precio, edt_existencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_articulos);

        edt_nombre= (EditText) findViewById(R.id.edt_nombre);
        edt_modelo= (EditText) findViewById(R.id.edt_modelo);
        edt_precio= (EditText) findViewById(R.id.edt_precio);
        edt_existencia= (EditText) findViewById(R.id.edt_existencia);

    }

    public void onClick(View view) {
        registroArticulos();
        //registrarUsuariosSql();
    }




    private void registroArticulos() {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_la_vendimia",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utils.nombre_articulo,edt_nombre.getText().toString());
        values.put(Utils.modelo_articulo,edt_modelo.getText().toString());
        values.put(Utils.precio_articulo,edt_precio.getText().toString());
        values.put(Utils.existencia_articulo,edt_existencia.getText().toString());

        Long idResultante=db.insert(Utils.TABLA_ARTICULO,Utils.clave_articulo,values);

        Toast.makeText(getApplicationContext(),"Se registro correctamente!" + "\n" + "Clave Registro: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
    }


}
