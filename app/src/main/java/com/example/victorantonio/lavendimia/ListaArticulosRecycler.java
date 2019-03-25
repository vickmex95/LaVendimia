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

import com.example.victorantonio.lavendimia.Adapters.ListaArticulosAdapter;
import com.example.victorantonio.lavendimia.Adapters.ListaPersonasAdapter;
import com.example.victorantonio.lavendimia.Models.Articulo;
import com.example.victorantonio.lavendimia.Models.Cliente;
import com.example.victorantonio.lavendimia.Utils.Utils;

import java.util.ArrayList;

public class ListaArticulosRecycler extends AppCompatActivity {
    ArrayList<Articulo> listaArticulos;
    ConexionSQLiteHelper conn;

    RecyclerView recyclerViewArticulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_articulos_recycler);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_la_vendimia",null,1);

        listaArticulos=new ArrayList<>();

        recyclerViewArticulos= (RecyclerView) findViewById(R.id.recyclerArticulos);
        recyclerViewArticulos.setLayoutManager(new LinearLayoutManager(this));

        consultarListaArticulos();

        ListaArticulosAdapter adapter=new ListaArticulosAdapter(listaArticulos);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Seleccion: " +
//                listaClientes.get(recyclerViewClientes.getChildAdapterPosition(view)).getNombre(),Toast.LENGTH_SHORT).show();
                Integer articuloSeleccionado;
                articuloSeleccionado= listaArticulos.get(recyclerViewArticulos.getChildAdapterPosition(view)).getClave();
                Intent intent=new Intent(ListaArticulosRecycler.this,ConsultarArticulosActivity.class);

                Bundle bundle=new Bundle();
                bundle.putSerializable("articulo_id",articuloSeleccionado);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        recyclerViewArticulos.setAdapter(adapter);
    }

    private void consultarListaArticulos() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Articulo articulo=null;
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utils.TABLA_ARTICULO,null);

        while (cursor.moveToNext()){
            articulo=new Articulo();
            articulo.setClave(cursor.getInt(0));
            articulo.setDescripcion(cursor.getString(1));
            articulo.setModelo(cursor.getString(2));
            articulo.setPrecio(cursor.getFloat(3));
            articulo.setExistencia(cursor.getInt(4));

            listaArticulos.add(articulo);
        }
    }

}