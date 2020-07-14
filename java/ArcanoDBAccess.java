package com.example.fechanactarot;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ArcanoDBAccess {
    private static final String DB_NAME = "arcanos.db";
    private static final Integer DB_VERSION = 1;

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static ArcanoDBAccess instance;
    Cursor c = null;


    private ArcanoDBAccess (Context context){
        this.openHelper = new DatabaseOpenHelper(context,DB_NAME,DB_VERSION);
    }

    public static ArcanoDBAccess getInstance(Context context){
        if (instance==null){
            instance= new ArcanoDBAccess(context);
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

    public Arcano getArcano(Integer id){
        c=db.rawQuery("Select nombre_corto, nombre_largo, descripcion, " +
                "significado_der, significado_reves from arcanos where id = " + id + "",new String[]{} );

        StringBuffer nombre_corto= new StringBuffer() ;
        StringBuffer nombre_largo= new StringBuffer() ;
        StringBuffer descripcion= new StringBuffer() ;
        StringBuffer sig_derecho= new StringBuffer() ;
        StringBuffer sig_reves= new StringBuffer() ;

        while (c.moveToNext()){
            nombre_corto.append("" + c.getString(0));
            nombre_largo.append("" + c.getString(1));
            descripcion.append("" + c.getString(2));
            sig_derecho.append("" + c.getString(3));
            sig_reves.append("" + c.getString(4));
        }

        Arcano arcanoActual= new Arcano(id, nombre_corto.toString(),nombre_largo.toString(),descripcion.toString()
                ,sig_derecho.toString(),sig_reves.toString());
        return arcanoActual;
    }
}
