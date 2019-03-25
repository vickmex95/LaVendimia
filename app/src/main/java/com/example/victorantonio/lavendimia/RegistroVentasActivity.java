package com.example.victorantonio.lavendimia;

import android.accounts.AccountManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.victorantonio.lavendimia.Models.Articulo;
import com.example.victorantonio.lavendimia.Models.Cliente;
import com.example.victorantonio.lavendimia.Utils.Utils;

import java.util.ArrayList;

public class RegistroVentasActivity extends AppCompatActivity {
//    private static final String[] CLIENTES = new String[]{
//            "victor", "eduardo", "erik", "fer"
//    };

    ListView listViewArticulosSeleccionados;
    ArrayList<String> listaInformacion;

    AutoCompleteTextView CLIENTES;
    ArrayList<String> listaClientes;
    ArrayList<Cliente> clientesList;

    AutoCompleteTextView ARTICULOS;
    ArrayList<String> listaArticulos;
    ArrayList<Articulo> articulosList;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ventas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_la_vendimia", null, 1);

        AutoCompleteTextView autocomplete_clientes = findViewById(R.id.autocomplete_clientes);
        AutoCompleteTextView autocomplete_articulos = findViewById(R.id.autocomplete_articulos);

        consultarListaClientes();
        consultarListaArticulos();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaClientes);
        autocomplete_clientes.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaArticulos);
        autocomplete_articulos.setAdapter(adapter2);

        listViewArticulosSeleccionados= (ListView) findViewById(R.id.listview_articulos_seleccionados);
        ArrayAdapter adaptador3=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaArticulos);
        listViewArticulosSeleccionados.setAdapter(adaptador3);
    }

    public void agregarAlCarro(View view){
        EditText clienteSeleccionado = (EditText) findViewById(R.id.autocomplete_clientes);
        EditText articuloSeleccionado = (EditText) findViewById(R.id.autocomplete_articulos);



    }

    public void siguiente(View view){


    }

    private void consultarListaClientes(){
        SQLiteDatabase db = conn.getReadableDatabase();

        Cliente cliente = null;
        clientesList = new ArrayList<Cliente>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLA_CLIENTE, null);

        while(cursor.moveToNext()){
            cliente = new Cliente();
            cliente.setClave(cursor.getInt(0));
            cliente.setNombre(cursor.getString(1));
            cliente.setApellido_pat(cursor.getString(2));
            cliente.setApellido_mat(cursor.getString(3));
            cliente.setRfc(cursor.getString(4));
            clientesList.add(cliente);
        }

        obtenerListaClientes();

    }

    private void obtenerListaClientes(){
        listaClientes = new ArrayList<String>();

        for(int i = 0; i<clientesList.size(); i++){
            listaClientes.add(clientesList.get(i).getNombre() + " " + clientesList.get(i).getApellido_pat() + " " + clientesList.get(i).getApellido_mat());
        }
    }

    private void consultarListaArticulos(){
        SQLiteDatabase db = conn.getReadableDatabase();

        Articulo articulo = null;
        articulosList = new ArrayList<Articulo>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utils.TABLA_ARTICULO, null);

        while(cursor.moveToNext()){
            articulo = new Articulo();
            articulo.setClave(cursor.getInt(0));
            articulo.setDescripcion(cursor.getString(1));
            articulo.setModelo(cursor.getString(2));
            articulo.setPrecio(cursor.getFloat(3));
            articulo.setExistencia(cursor.getInt(4));
            articulosList.add(articulo);
        }

        obtenerListaArticulos();

    }

    private void obtenerListaArticulos(){
        listaArticulos = new ArrayList<String>();

        for(int i = 0; i<articulosList.size(); i++){
            listaArticulos.add(articulosList.get(i).getDescripcion());
        }
    }

}
