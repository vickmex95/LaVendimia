package com.example.victorantonio.lavendimia;

import android.accounts.AccountManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.victorantonio.lavendimia.Models.Cliente;

public class RegistroVentasActivity extends AppCompatActivity {
    private static final String[] CLIENTES = new String[]{
            "victor", "eduardo", "erik", "fer"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ventas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        AutoCompleteTextView autocomplete_clientes = findViewById(R.id.autocomplete_clientes);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CLIENTES);
        autocomplete_clientes.setAdapter(adapter);

        AutoCompleteTextView autocomplete_articulos = findViewById(R.id.autocomplete_articulos);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CLIENTES);
        autocomplete_articulos.setAdapter(adapter);
    }

    public void agregarAlCarro(View view){

    }

}
