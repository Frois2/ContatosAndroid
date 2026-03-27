package com.leobottaro.uatizapinatoracme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.leobottaro.uatizapinatoracme.R;
import com.leobottaro.uatizapinatoracme.models.Conversante;

import java.util.List;

public class ConversanteAdapter extends ArrayAdapter<Conversante> {
    private LayoutInflater mInflator;

    public ConversanteAdapter(Context context, int resource, List<Conversante> dados){
        super(context, resource, dados);
        this.mInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public View getView(int posicao, View linha, ViewGroup parent){
        ViewHolder holder;
        if(linha == null){
            linha = mInflator.inflate(R.layout.item_detalhe, null);
            holder = new ViewHolder();
            holder.nome = linha.findViewById(R.id.txtNome);
            holder.celular = linha.findViewById(R.id.txtCelular);
            holder.email = linha.findViewById(R.id.txtEmial);
            linha.setTag(holder);
        } else{
            holder = (ViewHolder) linha.getTag();
        }

        Conversante c = getItem(posicao);
        holder.nome.setText(c.getNome());
        holder.celular.setText(c.getCelular());
        holder.email.setText(c.getEmail());

        return linha;
    }

    static class ViewHolder{
        public TextView nome;
        public TextView celular;
        public TextView email;
    }
}
