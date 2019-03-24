package com.example.victorantonio.lavendimia;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.victorantonio.lavendimia.Adapters.ListaPersonasAdapter;
import com.example.victorantonio.lavendimia.Models.Cliente;
import com.example.victorantonio.lavendimia.Utils.Utils;

import java.util.ArrayList;

public class ListaPersonasRecycler extends AppCompatActivity {
    ArrayList<Cliente> listaClientes;
    ConexionSQLiteHelper conn;

    RecyclerView recyclerViewClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personas_recycler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_la_vendimia",null,1);

        listaClientes=new ArrayList<>();

        recyclerViewClientes= (RecyclerView) findViewById(R.id.recyclerPersonas);
        recyclerViewClientes.setLayoutManager(new LinearLayoutManager(this));

        consultarListaPersonas();

        ListaPersonasAdapter adapter=new ListaPersonasAdapter(listaClientes);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Seleccion: " +
//                listaClientes.get(recyclerViewClientes.getChildAdapterPosition(view)).getNombre(),Toast.LENGTH_SHORT).show();
                Integer clienteSeleccionado;
                clienteSeleccionado= listaClientes.get(recyclerViewClientes.getChildAdapterPosition(view)).getClave();
                Intent intent=new Intent(ListaPersonasRecycler.this,ConsultarClientesActivity.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("cliente_id",clienteSeleccionado);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        recyclerViewClientes.setAdapter(adapter);
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Cliente cliente=null;
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utils.TABLA_CLIENTE,null);

        while (cursor.moveToNext()){
            cliente=new Cliente();
            cliente.setClave(cursor.getInt(0));
            cliente.setNombre(cursor.getString(1));
            cliente.setApellido_pat(cursor.getString(2));
            cliente.setApellido_mat(cursor.getString(3));
            cliente.setRfc(cursor.getString(4));

            listaClientes.add(cliente);
        }
    }

}
