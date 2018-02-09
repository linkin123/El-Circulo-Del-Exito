package cinepoilisklic.com.ia.elcirculodelexito.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lrangel on 07/02/2018.
 */

public class BaseHelper  extends SQLiteOpenHelper{

    String tablaAlumnos= "CREATE TABLE ALUMNOS(ID INTEGER  PRIMARY KEY, NOMBRE TEXT, NOMBREPADRE TEXT , TELEFONO TEXT)";
    String tabla= "CREATE TABLE ALUMNOS(ID INTEGER  PRIMARY KEY, NOMBRE TEXT, NOMBREPADRE TEXT , TELEFONO TEXT)";

    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tablaAlumnos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table alumnos");
        db.execSQL( tablaAlumnos );
    }

}
