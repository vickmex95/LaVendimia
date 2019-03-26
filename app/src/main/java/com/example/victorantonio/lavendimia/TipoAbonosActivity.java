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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.victorantonio.lavendimia.Models.AbonosMensuales;
import com.example.victorantonio.lavendimia.Utils.Utils;

import java.util.ArrayList;

public class TipoAbonosActivity extends AppCompatActivity {
    Spinner comboTipoFinanciamiento;

    ArrayList<String> listaAbonos;
    ArrayList<AbonosMensuales> abonosList;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_abonos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        comboTipoFinanciamiento = (Spinner) findViewById(R.id.spinner_tipo_financiamiento);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_la_vendimia",null,1);

        consultarListaTipoFinanciamiento();

        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaAbonos);

        comboTipoFinanciamiento.setAdapter(adaptador);



        comboTipoFinanciamiento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void guardar(View v){

    }



    private void consultarListaTipoFinanciamiento() {
        SQLiteDatabase db=conn.getReadableDatabase();

        AbonosMensuales persona=null;
        abonosList =new ArrayList<AbonosMensuales>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utils.TABLA_ABONOS_MENSUAL,null);

        while (cursor.moveToNext()){
            persona=new AbonosMensuales();
            persona.setId_abono(cursor.getInt(0));
            persona.setDescripcion(cursor.getString(1));

            abonosList.add(persona);

        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaAbonos=new ArrayList<String>();
        listaAbonos.add("Seleccione");

        for(int i=0;i<abonosList.size();i++){
            listaAbonos.add(abonosList.get(i).getDescripcion());
        }

    }

}
