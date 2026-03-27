package com.leobottaro.uatizapinatoracme.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Path;

import com.leobottaro.uatizapinatoracme.connection.DatabaseConnection;
import com.leobottaro.uatizapinatoracme.models.Conversante;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConversanteDAO {
    public static final String TABLE_NAME = "Conversantes";
    private SQLiteDatabase db;
    private DatabaseConnection connection;

    public ConversanteDAO(Context context) {
        connection = new DatabaseConnection(context);
    }

    public void Open(){
        db = connection.getReadableDatabase();
    }

    public void Close(){
        db.close();
    }

    public long Insert(Conversante c){
        ContentValues dados = new ContentValues();
        dados.put("Nome", c.getNome());
        dados.put("Celular", c.getCelular());
        dados.put("Email", c.getEmail());
        Open();
        long key = db.insert(TABLE_NAME, null, dados);
        Close();
        return key;
    }

    public void Excluir(Conversante c){
        if (c.getId() <= 0){
            return;
        }

        db.delete("conversantes", "id?", new String[]{String.valueOf(c.getId())});
    }

    public void Atualizar ( Conversante c){
        if (c.getId() <= 0){
            return;
        }
        ContentValues dados = new ContentValues();
        dados.put("Nome", c.getNome());
        dados.put("Celular", c.getCelular());
        dados.put("Email", c.getEmail());
        this.Open();
        db.update("conversantes", dados, "Id=?", new String[]{String.valueOf(c.getId())});
        this.Close();
    }
    public List<Conversante> GetAll(){
        List<Conversante> lista = new ArrayList();
        String campos[] = new String[]{"Nome","Celular","Email"};
        Open();
        Cursor dados = db.query(
                TABLE_NAME,
                campos,
                null,
                null,
                null,
                null,
                "Nome",
                null);


        if (dados.moveToFirst()) {
            do {
                Conversante c = new Conversante(
                        dados.getString(0),
                        dados.getString(1),
                        dados.getString(2)
                );
                lista.add(c);
            } while (dados.moveToNext());
        }
        Close();
        return lista;
    }
}
