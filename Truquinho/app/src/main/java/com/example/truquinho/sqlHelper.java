package com.example.truquinho;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

public class sqlHelper extends SQLiteOpenHelper { //precisar criar os dois métodos

    private static final String db_Name = "truco.db";
    private static final int db_Version = 1;

    private static sqlHelper instance;

    static sqlHelper getInstance(Context context){ // criar ou retornar instancia do banco.
        if (instance==null)
            instance=new sqlHelper(context);
        return instance;
    }
    //------------construtor (informa o contexto, nome da base e versão (resto pode excluir))-------------------------------------------------------
    public sqlHelper(@Nullable Context context) { //construtor da classe (deixar nessa estrutura utilizando as variavies db_Name e db_version, Factory nao precisa)
        super(context, db_Name, null, db_Version);
    }
    //---------------metodos para controle de para adição ou atualização da base de dados--------------------------------------------------------
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) { // executada quando ainda nao existe o banco
        sqLiteDatabase.execSQL( //text = string, decimal=float, date = datetime
                "CREATE TABLE partidas (id INTEGER primary key, nome TEXT, pt_maxima INTEGER, pt_nos INTEGER, pt_eles INTEGER)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { //executa quando esta atualizando...

    }
    //-----------------------------------------------------------------------
    long addPartida(String valor1, Integer valor2, Integer valor3, Integer valor4){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase(); //usamos writable ou readable
        // precisa porque podemos gerar erro ao adicionar dados...

        long idbanco = 0;
        try{
            sqLiteDatabase.beginTransaction();
            ContentValues valores = new ContentValues();
            valores.put("nome", valor1);
            valores.put("pt_maxima",valor2);
            valores.put("pt_nos",valor3);
            valores.put("pt_eles",valor4);
            idbanco = sqLiteDatabase.insertOrThrow("partidas",null,valores);
            sqLiteDatabase.setTransactionSuccessful();
        } catch (Exception e){
            Log.e("sqllite",e.getMessage(),e);

        } finally {
            sqLiteDatabase.endTransaction();
        }
        return idbanco;

    }

}
