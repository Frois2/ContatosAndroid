package com.leobottaro.uatizapinatoracme.connection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseConnection extends SQLiteOpenHelper {

    public static final int DBVersion = 1;

    public DatabaseConnection(Context context) {
        super(context, "Uatizapinator", null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE Conversantes(" +
                    "Id integer primary key autoincrement," +
                    "Nome text not null," +
                    "Celular text not null," +
                    "Email text not null)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("Atualização de Banco:",
                "Versão Antiga: " + oldVersion + " | Versão Nova: " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS Conversantes");
        onCreate(db);
    }
}
