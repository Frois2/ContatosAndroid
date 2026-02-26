package com.leobottaro.uatizapinatoracme.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.leobottaro.uatizapinatoracme.R;

import java.util.List;

public class ConversanteArrayAdapter extends ArrayAdapter<Conversante> {
    private LayoutInflater myInflater;

    public ConversanteArrayAdapter(
            @NonNull Context context,
            int resource,
            @NonNull List<Conversante> objects) {
        super(context, resource, objects);
        this.myInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
        );
    }

    public View getView(int position, View row, ViewGroup parent)
    {
        ViewHolder holder;
        if (row == null){
            row = myInflater.inflate(R.layout.contatinho_layout, null);
            holder = new ViewHolder();
            holder.nome = row.findViewById(R.id.NomeItem);
            holder.celular = row.findViewById(R.id.CelularItem);
            holder.email = row.findViewById(R.id.EmailItem);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Conversante conversante = getItem(position);
        holder.nome.setText(conversante.getNome());
        holder.celular.setText(conversante.getCelular());
        holder.email.setText(conversante.getEmail());

        return row;
    }

    static class ViewHolder{
        public TextView nome;
        public TextView celular;
        public TextView email;
    }

}
