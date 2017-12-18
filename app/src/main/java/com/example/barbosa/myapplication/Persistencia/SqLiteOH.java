/*
package com.example.barbosa.myapplication.Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.barbosa.myapplication.Objetos.Cliente;

*/
/**
 * Created by User on 16/12/2017.
 *//*


public class SqLiteOH extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SQLite_Pontos";

    public static final String TABELA_CLIENTES = "tb_pontos";

    public static final String COLUNA_CODIGO = "codigo";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_TELEFONE = "telefone";
    public static final String COLUNA_PONTOS = "pontos";

    public SqLiteOH(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY_COLUNA = "CREATE TABLE" + TABELA_CLIENTES + "("
                + COLUNA_CODIGO + " INTEGER PRIMARY KEY, " + COLUNA_NOME + " TEXT, "
                + COLUNA_EMAIL + " TEXT, " + COLUNA_TELEFONE+ " TEXT, " + COLUNA_PONTOS + " INTEGER)";

        db.execSQL(QUERY_COLUNA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // -->> Crud Abaixo

    public  void add(Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, cliente.getNome());
        values.put(COLUNA_EMAIL, cliente.getEmail());
        values.put(COLUNA_TELEFONE, cliente.getTelefone());
        values.put(COLUNA_PONTOS, cliente.getPontos());

        db.insert(TABELA_CLIENTES,null, values);
        db.close();
    }
    public void delete (Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_CLIENTES, COLUNA_CODIGO + " = ?", new String[]{String.valueOf(cliente.getCodigo())});
        db.close();
    }

    public Cliente get(int codigo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_CLIENTES, new String[]{
                COLUNA_CODIGO, COLUNA_NOME, COLUNA_EMAIL, COLUNA_TELEFONE, COLUNA_PONTOS},
                COLUNA_CODIGO + " = ?", new String[]{String.valueOf(codigo)},
                null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Cliente cliente = new Cliente(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), Integer.parseInt(cursor.getString(4)));

        db.close();
        return cliente;

    }

    public  void update(Cliente cliente) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, cliente.getNome());
        values.put(COLUNA_EMAIL, cliente.getEmail());
        values.put(COLUNA_TELEFONE, cliente.getTelefone());
        values.put(COLUNA_PONTOS, cliente.getPontos());

        db.update(TABELA_CLIENTES, values, COLUNA_CODIGO + " = ?", new String[]{String.valueOf(cliente.getCodigo())});
        db.close();


    }

}
*/
