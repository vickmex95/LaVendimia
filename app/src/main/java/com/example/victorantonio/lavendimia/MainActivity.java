package com.example.victorantonio.lavendimia;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victorantonio.lavendimia.Models.Cliente;
import com.example.victorantonio.lavendimia.Utils.Utils;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        HomeFragment.OnFragmentInteractionListener,VentasFragment.OnFragmentInteractionListener,ClientesFragment.OnFragmentInteractionListener, ArticulosFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener {

    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Cliente> listaClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_la_vendimia",null,1);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        Fragment fragment=new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content_main,fragment).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        Date d = new Date();
        CharSequence fecha = DateFormat.format("MMMM d, yyyy ", d.getTime());
        Bundle bundle = new Bundle();
        bundle.putString("fecha", fecha.toString());
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
//        //consulta clientes fragment clientes:
//        listViewPersonas=  findViewById(R.id.listViewPersonas);
//
//        consultarListaPersonas();
//
//        ArrayAdapter adaptador=new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
//        listViewPersonas.setAdapter(adaptador);
//        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
//                String informacion="Clave: "+listaClientes.get(pos).getClave()+"\n";
//                informacion+="Nombre: "+listaClientes.get(pos).getNombre()+"\n";
//                informacion+="Apellido Paterno: "+listaClientes.get(pos).getApellido_pat()+"\n";
//                informacion+="Apellido Materno: "+listaClientes.get(pos).getApellido_mat()+"\n";
//                informacion+="RFC: "+listaClientes.get(pos).getRfc()+"\n";
//
//                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();
//
//            }
//        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment miFragment=null;
        boolean fragmentSeleccionado=false;


        if (id == R.id.nav_camera) {
            miFragment=new HomeFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_gallery) {
            miFragment=new VentasFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_slideshow) {
            miFragment=new ClientesFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_share) {
            miFragment=new ArticulosFragment();
            fragmentSeleccionado=true;
        } else if (id == R.id.nav_send) {
            miFragment=new SettingsFragment();
            fragmentSeleccionado=true;
        }

        if (fragmentSeleccionado==true){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick(View v)
    {
        Intent miIntent=null;
        switch (v.getId()){
            case R.id.btnOpcionRegistro:
                miIntent=new Intent(MainActivity.this,RegistroClientesActivity.class);
                break;
            case R.id.btnConsultaIndividual:
                miIntent=new Intent(MainActivity.this,ConsultarClientesActivity.class);
                break;
            case R.id.btnConsultaListaPersonasRecycler:
                miIntent=new Intent(MainActivity.this,ListaPersonasRecycler.class);
                break;
            case R.id.btnAgregarArticulo:
                miIntent=new Intent(MainActivity.this,RegistroArticulosActivity.class);
                break;
            case R.id.btnConsultaArticulos:
                miIntent=new Intent(MainActivity.this,ConsultarArticulosActivity.class);
                break;
            case R.id.btnConsultaListaArticulos:
                miIntent=new Intent(MainActivity.this,ListaArticulosRecycler.class);
                break;
            case R.id.btnRegistrarVenta:
                miIntent=new Intent(MainActivity.this,RegistroVentasActivity.class);
                break;
        }
        if (miIntent!=null){
            startActivity(miIntent);
        }
    }

//    private void consultarListaPersonas() {
//        SQLiteDatabase db=conn.getReadableDatabase();
//
//        Cliente cliente=null;
//        listaClientes=new ArrayList<Cliente>();
//        //select * from usuarios
//        Cursor cursor=db.rawQuery("SELECT * FROM "+Utils.TABLA_CLIENTE,null);
//
//        while (cursor.moveToNext()){
//            cliente=new Cliente();
//            cliente.setClave(cursor.getInt(0));
//            cliente.setNombre(cursor.getString(1));
//            cliente.setApellido_pat(cursor.getString(2));
//            cliente.setApellido_mat(cursor.getString(3));
//            cliente.setRfc(cursor.getString(4));
//
//            listaClientes.add(cliente);
//        }
//        obtenerLista();
//    }
//
//    private void obtenerLista() {
//        listaInformacion=new ArrayList<String>();
//
//        for (int i=0; i<listaClientes.size();i++){
//            listaInformacion.add(listaClientes.get(i).getClave()+" - "
//                    +listaClientes.get(i).getNombre());
//        }
//
//    }

    public void guardarConfiguracion(View v){
        Boolean tiene_configuracion;
        tiene_configuracion = verConfiguracionAnterior();

        TextView edt_financiamiento = (TextView) findViewById(R.id.edt_financiamiento);
        TextView edt_enganche = (TextView) findViewById(R.id.edt_enganche);
        TextView edt_plazo = (TextView) findViewById(R.id.edt_plazo);

        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_la_vendimia",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();

        if(tiene_configuracion == false) {

            ContentValues values = new ContentValues();
            values.put(Utils.taza_financiamiento, edt_financiamiento.getText().toString());
            values.put(Utils.porcentaje_enganche, edt_enganche.getText().toString());
            values.put(Utils.plazo_maximo, edt_plazo.getText().toString());
            String uno = "1";
            Long idResultante = db.insert(Utils.TABLA_CONFIGURACION, uno, values);

            Toast.makeText(getApplicationContext(), "Se guardo la configuracion!", Toast.LENGTH_SHORT).show();
            db.close();
        }else{
            //String[] parametros={edt_clave.getText().toString()};
            ContentValues values=new ContentValues();
            values.put(Utils.taza_financiamiento,edt_financiamiento.getText().toString());
            values.put(Utils.porcentaje_enganche,edt_enganche.getText().toString());
            values.put(Utils.plazo_maximo,edt_plazo.getText().toString());

            db.update(Utils.TABLA_CONFIGURACION,values,null,null);
            Toast.makeText(getApplicationContext(),"Se actualizo la configuracion",Toast.LENGTH_LONG).show();
            db.close();
        }
    }

    public void consultaConfiguracion(View view){
        String uno = "1";
        ConexionSQLiteHelper conn;
        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_la_vendimia",null,1);

        TextView edt_financiamiento = (TextView) findViewById(R.id.edt_financiamiento);
        TextView edt_enganche = (TextView) findViewById(R.id.edt_enganche);
        TextView edt_plazo = (TextView) findViewById(R.id.edt_plazo);

        SQLiteDatabase db= conn.getReadableDatabase();
        String[] parametros={uno};
        String[] campos={Utils.taza_financiamiento,Utils.porcentaje_enganche,Utils.plazo_maximo};


        Cursor cursor =db.query(Utils.TABLA_CONFIGURACION,campos,null,null,null,null,null);
        cursor.moveToFirst();
        String taza_financiamiento = cursor.getString(0);
        String porcentaje_enganche = cursor.getString(1);
        String plazo = cursor.getString(2);
        cursor.close();

        Toast.makeText(getApplicationContext(),"Taza financiamiento: " + taza_financiamiento + "\n"
                + "Porcentaje Enganche: "+porcentaje_enganche + "\n"
                + "Plazo Maximo: " + plazo,Toast.LENGTH_SHORT).show();


//        Bundle args = new Bundle();
//        args.putString("financiamiento", taza_financiamiento);
//        args.putString("enganche", porcentaje_enganche);
//        args.putString("plazo", plazo);
//        SettingsFragment settingsFragment = new SettingsFragment();
//        settingsFragment.setArguments(args);
    }

    public boolean verConfiguracionAnterior(){
        String uno = "1";
        ConexionSQLiteHelper conn;
        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_la_vendimia",null,1);

        SQLiteDatabase db= conn.getReadableDatabase();
        String[] parametros={uno};
        String[] campos={Utils.taza_financiamiento,Utils.porcentaje_enganche,Utils.plazo_maximo};


        Cursor cursor =db.query(Utils.TABLA_CONFIGURACION,campos,null,null,null,null,null);
        cursor.moveToFirst();
        String taza_financiamiento = cursor.getString(0);
        String porcentaje_enganche = cursor.getString(1);
        String plazo = cursor.getString(2);
        cursor.close();
        Boolean tiene_configuracion = false;
        if(cursor != null){
            tiene_configuracion = true;
        }

        return tiene_configuracion;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}