package com.example.victorantonio.lavendimia.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.victorantonio.lavendimia.Models.Articulo;
import com.example.victorantonio.lavendimia.R;

import java.util.ArrayList;

public class ListaArticulosAdapter extends RecyclerView.Adapter<ListaArticulosAdapter.ArticulosViewHolder>
        implements  View.OnClickListener
{

    ArrayList<Articulo> listaArticulos;
    private View.OnClickListener listener;

    public ListaArticulosAdapter(ArrayList<Articulo> listaArticulos) {
        this.listaArticulos = listaArticulos;
    }

    @Override
    public ArticulosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items_articulos,null,false);

        view.setOnClickListener(this);
        return new ArticulosViewHolder(view);


    }

    @Override
    public void onBindViewHolder(ArticulosViewHolder holder, int position) {
        holder.clave.setText(listaArticulos.get(position).getClave().toString());
        holder.nombre.setText(listaArticulos.get(position).getDescripcion());
        holder.modelo.setText(listaArticulos.get(position).getModelo());
        holder.precio.setText(listaArticulos.get(position).getPrecio().toString());
        holder.existencia.setText(listaArticulos.get(position).getExistencia().toString());
    }

    @Override
    public int getItemCount() {
        return listaArticulos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    public class ArticulosViewHolder extends RecyclerView.ViewHolder {

        TextView clave,nombre,modelo,precio,existencia;

        public ArticulosViewHolder(View itemView) {
            super(itemView);
            clave = (TextView) itemView.findViewById(R.id.txt_clave);
            nombre = (TextView) itemView.findViewById(R.id.txt_nombre);
            modelo = (TextView) itemView.findViewById(R.id.txt_modelo);
            precio = (TextView) itemView.findViewById(R.id.txt_precio);
            existencia = (TextView) itemView.findViewById(R.id.txt_existencia);
        }
    }
}