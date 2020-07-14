package com.example.fechanactarot;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NumeroEspecialDBAccess {
    private static final String DB_NAME = "numeros_especiales.db";
    private static final Integer DB_VERSION = 1;

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static NumeroEspecialDBAccess instance;
    Cursor c = null;


    private NumeroEspecialDBAccess (Context context){
        this.openHelper = new DatabaseOpenHelper(context,DB_NAME,DB_VERSION);
    }

    public static NumeroEspecialDBAccess getInstance(Context context){
        if (instance==null){
            instance= new NumeroEspecialDBAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    public void close(){
        if (db!=null) {
            this.db.close();
        }
    }

    public NumeroEspecial getNumeroEspecial(Integer numero){
        c=db.rawQuery("Select id, tipo, descripcion, " +
                "contra from numeros_especiales where numero = " + numero + "",new String[]{} );

        StringBuffer id= new StringBuffer() ;
        StringBuffer tipo= new StringBuffer() ;
        StringBuffer descripcion= new StringBuffer() ;
        StringBuffer contra = new StringBuffer() ;

        while (c.moveToNext()){
            id.append("" + c.getString(0));
            tipo.append("" + c.getString(1));
            descripcion.append("" + c.getString(2));
            contra.append("" + c.getString(3));
        }

        NumeroEspecial numeroActual= new NumeroEspecial(Integer.getInteger(id.toString()),
                tipo.toString(),numero,descripcion.toString(),contra.toString());
        return numeroActual;
    }
}

