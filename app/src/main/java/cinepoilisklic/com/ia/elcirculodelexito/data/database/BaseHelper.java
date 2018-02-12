package cinepoilisklic.com.ia.elcirculodelexito.data.database;

import android.app.ActionBar;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper.AlumnoMaestros.ALUMNO_MAESTROS_CREATE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper.AlumnoPaquete.ALUMNO_PAQUETE_CREATE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper.Alumnos.ALUMNOS_CREATE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper.Maestros.MAESTROS_CREATE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper.Materia.MATERIA_CREATE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper.MateriasNiveles.MATERIAS_NIVELES_CREATE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper.Nivel.NIVEL_CREATE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper.Paquete.PAQUETE_CREATE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper.PaqueteMaestro.PAQUETE_MAESTRO_CREATE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper.PaqueteMateria.PAQUETE_MATERIA_CREATE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper.PaqueteNivel.PAQUETE_NIVEL_CREATE;

/**
 * Created by lrangel on 07/02/2018.
 */

public class BaseHelper  extends SQLiteOpenHelper{


    public static final class Alumnos{
        public static String TABLE_ALUMNOS = "ALUMNOS";
        public static String COLUNM_ID = "id";
        public static String COLUNM_NOMBRE = "nombre";
        public static String COLUNM_NOMBREPADRE = "nombrePadre";
        public static String COLUNM_TELEFONO = "telefonoPadre";

        public static final String ALUMNOS_CREATE =
                "CREATE TABLE "
                        + TABLE_ALUMNOS
                        + "("
                        + COLUNM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUNM_NOMBRE + " TEXT NOT NULL, "
                        + COLUNM_NOMBREPADRE +" TEXT NOT NULL, "
                        + COLUNM_TELEFONO + " TEXT NOT NULL"
                        + ");";

    }
    public static final class Maestros{
        public static String TABLE_MAESTROS = "MAESTROS";
        public static String COLUNM_ID = "id";
        public static String COLUNM_NOMBRE = "nombre";
        public static String COLUNM_TELEFONO = "telefono";
        public static String COLUNM_DOMICILIO = "domicilio";
        public static String COLUNM_HORARIO = "horario";

        public static final String MAESTROS_CREATE =
                "CREATE TABLE "
                        + TABLE_MAESTROS
                        + "("
                        + COLUNM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUNM_NOMBRE + " TEXT NOT NULL, "
                        + COLUNM_TELEFONO +" TEXT NOT NULL, "
                        + COLUNM_DOMICILIO + " TEXT NOT NULL"
                        + COLUNM_HORARIO + " TEXT NOT NULL"
                        + ");";

    }

    public static final class AlumnoMaestros{
        public static String TABLE_ALUMNO_MAESTROS = "ALUMNOMAESTROS";
        public static String COLUNM_ID_ALUMNO = "idAlumno";
        public static String COLUNM_ID_MAESTRO = "idMaestro";

        public static final String ALUMNO_MAESTROS_CREATE =
                "CREATE TABLE "
                        + TABLE_ALUMNO_MAESTROS
                        + "("
                        + COLUNM_ID_ALUMNO + " INTEGER FOREIGN KEY, "
                        + COLUNM_ID_MAESTRO + " INTEGER FOREIGN KEY,"
                        + ");";

    }

    public static final class Materia{
        public static String TABLE_MATERIA = "MATERIA";
        public static String COLUNM_ID = "id";
        public static String COLUNM_NOMBRE = "nombre";

        public static final String MATERIA_CREATE =
                "CREATE TABLE "
                        + TABLE_MATERIA
                        + "("
                        + COLUNM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUNM_NOMBRE + " TEXT NOT NULL,"
                        + ");";

    }

    public static final class MateriasNiveles{
        public static String TABLE_MATERIAS_NIVEL = "MATERIASNIVEL";
        public static String COLUNM_ID_MATERIA = "idMateria";
        public static String COLUNM_ID_NIVEL = "idNivel";

        public static final String MATERIAS_NIVELES_CREATE =
                "CREATE TABLE "
                        + TABLE_MATERIAS_NIVEL
                        + "("
                        + COLUNM_ID_MATERIA + " INTEGER FOREIGN KEY, "
                        + COLUNM_ID_NIVEL + " INTEGER FOREIGN KEY,"
                        + ");";

    }

    public static final class Nivel{
        public static String TABLE_NIVEL = "NIVEL";
        public static String COLUNM_ID = "id";
        public static String COLUNM_NOMBRE = "nombre";

        public static final String NIVEL_CREATE =
                "CREATE TABLE "
                        + TABLE_NIVEL
                        + "("
                        + COLUNM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUNM_NOMBRE + " TEXT NOT NULL,"
                        + ");";

    }

    public static final class AlumnoPaquete{
        public static String TABLE_ALUMNO_PAQUETE = "ALUMNOPAQUETE";
        public static String COLUNM_ID_ALUMNO = "idAlumno";
        public static String COLUNM_ID_PAQUETE = "idPaquete";
        public static String COLUNM_TIEMPO_ASESORIA = "tiempoAsesoria";
        public static String COLUNM_TIEMPO_RESTANTE = "tiempoRestante";

        public static final String ALUMNO_PAQUETE_CREATE =
                "CREATE TABLE "
                        + TABLE_ALUMNO_PAQUETE
                        + "("
                        + COLUNM_ID_ALUMNO + " INTEGER FOREIGN KEY, "
                        + COLUNM_ID_PAQUETE + " INTEGER FOREIGN KEY,"
                        + COLUNM_ID_ALUMNO + " INTEGER NOT NULL, "
                        + COLUNM_ID_PAQUETE + " INTEGER NOT NULL,"
                        + ");";

    }

    public static final class Paquete{
        public static String TABLE_PAQUETE = "PAQUETE";
        public static String COLUNM_ID = "id";
        public static String COLUNM_HORAS = "horas";
        public static String COLUNM_FECHA = "fecha";

        public static final String PAQUETE_CREATE =
                "CREATE TABLE "
                        + TABLE_PAQUETE
                        + "("
                        + COLUNM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUNM_HORAS + " INTEGER NOT NULL, "
                        + COLUNM_FECHA + " TEXT NOT NULL,"
                        + ");";

    }

    public static final class PaqueteMaestro{
        public static String TABLE_PAQUETE_MAESTRO = "PAQUETEMAESTRO";
        public static String COLUNM_ID_PAQUETE = "idPaquete";
        public static String COLUNM_ID_MAESTRO = "idMaestro";

        public static final String PAQUETE_MAESTRO_CREATE =
                "CREATE TABLE "
                        + TABLE_PAQUETE_MAESTRO
                        + "("
                        + COLUNM_ID_PAQUETE + " INTEGER FOREIGN KEY, "
                        + COLUNM_ID_MAESTRO + " INTEGER FOREIGN KEY,"
                        + ");";

    }

    public static final class PaqueteMateria{
        public static String TABLE_PAQUETE_MATERIA = "PAQUETEMATERIA";
        public static String COLUNM_ID_PAQUETE = "idPaquete";
        public static String COLUNM_ID_MATERIA = "idmateria";

        public static final String PAQUETE_MATERIA_CREATE =
                "CREATE TABLE "
                        + TABLE_PAQUETE_MATERIA
                        + "("
                        + COLUNM_ID_PAQUETE + " INTEGER FOREIGN KEY, "
                        + COLUNM_ID_MATERIA + " INTEGER FOREIGN KEY,"
                        + ");";

    }

    public static final class PaqueteNivel{
        public static String TABLE_PAQUETE_NIVEL = "PAQUETENIVEL";
        public static String COLUNM_ID_PAQUETE = "idPaquete";
        public static String COLUNM_ID_NIVEL = "idNivel";

        public static final String PAQUETE_NIVEL_CREATE =
                "CREATE TABLE "
                        + TABLE_PAQUETE_NIVEL
                        + "("
                        + COLUNM_ID_PAQUETE + " INTEGER FOREIGN KEY, "
                        + COLUNM_ID_NIVEL + " INTEGER FOREIGN KEY,"
                        + ");";

    }

    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ALUMNOS_CREATE);
        db.execSQL(MAESTROS_CREATE);
        db.execSQL(ALUMNO_MAESTROS_CREATE);
        db.execSQL(MATERIA_CREATE);
        db.execSQL(MATERIAS_NIVELES_CREATE);
        db.execSQL(NIVEL_CREATE);
        db.execSQL(ALUMNO_PAQUETE_CREATE);
        db.execSQL(PAQUETE_CREATE);
        db.execSQL(PAQUETE_MAESTRO_CREATE);
        db.execSQL(PAQUETE_MATERIA_CREATE);
        db.execSQL(PAQUETE_NIVEL_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table alumnos");
        db.execSQL("drop table maestros");
        db.execSQL("drop table alumnomaestros");
        db.execSQL("drop table materia");
        db.execSQL("drop table materiasnivel");
        db.execSQL("drop table nivel");
        db.execSQL("drop table alumnopaquete");
        db.execSQL("drop table paquete");
        db.execSQL("drop table paquetemaestro");
        db.execSQL("drop table paquetemateria");
        db.execSQL("drop table paquetenivel");


        db.execSQL(ALUMNOS_CREATE);
        db.execSQL(MAESTROS_CREATE);
        db.execSQL(ALUMNO_MAESTROS_CREATE);
        db.execSQL(MATERIA_CREATE);
        db.execSQL(MATERIAS_NIVELES_CREATE);
        db.execSQL(NIVEL_CREATE);
        db.execSQL(ALUMNO_PAQUETE_CREATE);
        db.execSQL(PAQUETE_CREATE);
        db.execSQL(PAQUETE_MAESTRO_CREATE);
        db.execSQL(PAQUETE_MATERIA_CREATE);
        db.execSQL(PAQUETE_NIVEL_CREATE);

    }

}
