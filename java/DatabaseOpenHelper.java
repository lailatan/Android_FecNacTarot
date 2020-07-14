package com.example.fechanactarot;

import android.content.Context;
/*
Poner en dependencias del Gradle Scripts/build.gradle(Module: app)
compile 'com.readystatesoftware.sqliteasset:sqliteassethelper:+'
y sincronizar.
Las bases de datos deben copiarse en main/assets/databases/
y en el celular van al data/data/com.example.xxx/cache/databases
* */
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {


    public DatabaseOpenHelper(Context context, String dbName, Integer dbVersion) {
        super(context, dbName, null, dbVersion);
    }
}