package com.example.victorantonio.lavendimia.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.victorantonio.lavendimia.Models.Cliente;
import com.example.victorantonio.lavendimia.R;

/**
 * Created by CHENAO on 8/07/2017.
 */

public class ListaPersonasAdapter extends RecyclerView.Adapter<ListaPersonasAdapter.PersonasViewHolder>
 implements  View.OnClickListener
{

    ArrayList<Cliente> listaCliente;
    private View.OnClickListener listener;

    public ListaPersonasAdapter(ArrayList<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    @Override
    public PersonasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items_clientes,null,false);

        view.setOnClickListener(this);
        return new PersonasViewHolder(view);


    }

    @Override
    public void onBindViewHolder(PersonasViewHolder holder, int position) {
        holder.clave.setText(listaCliente.get(position).getClave().toString());
        holder.nombre.setText(listaCliente.get(position).getNombre());
        holder.apellido_pat.setText(listaCliente.get(position).getApellido_pat());
        holder.apellido_mat.setText(listaCliente.get(position).getApellido_mat());
        holder.rfc.setText(listaCliente.get(position).getRfc());
    }

    @Override
    public int getItemCount() {
        return listaCliente.size();
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

    public class PersonasViewHolder extends RecyclerView.ViewHolder {

        TextView clave,nombre,apellido_pat,apellido_mat,rfc;

        public PersonasViewHolder(View itemView) {
            super(itemView);
            clave = (TextView) itemView.findViewById(R.id.txt_clave);
            nombre = (TextView) itemView.findViewById(R.id.txt_nombre);
            apellido_pat = (TextView) itemView.findViewById(R.id.txt_apellido_pat);
            apellido_mat = (TextView) itemView.findViewById(R.id.txt_apellido_mat);
            rfc = (TextView) itemView.findViewById(R.id.txt_rfc);
        }
    }
}