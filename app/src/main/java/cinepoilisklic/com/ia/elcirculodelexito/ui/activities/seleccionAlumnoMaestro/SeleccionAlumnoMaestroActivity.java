package cinepoilisklic.com.ia.elcirculodelexito.ui.activities.seleccionAlumnoMaestro;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.Utils.utils;
import cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Alumno;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Materia;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.altaPaquete.AltaPaqueteActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.adapters.AlumnosAdapter;
import static cinepoilisklic.com.ia.elcirculodelexito.Utils.utils.*;

import cinepoilisklic.com.ia.elcirculodelexito.ui.adapters.MateriasAdapter;
import cinepoilisklic.com.ia.elcirculodelexito.ui.adapters.MateriasLinealAdapter;
import okhttp3.internal.Util;

public class SeleccionAlumnoMaestroActivity extends AppCompatActivity implements AlumnosAdapter.onItemClickListener{

    ArrayList<String> idAlumns;
    ArrayList<String> idMaestros;
    ArrayAdapter arrayAlumnos;
    ArrayAdapter arrayMaestros;
    MateriasAdapter materiasAdapter;
    private int idAlumno;
    private int idMaestro;
    private Button btnInicioDeClase;
    private Button btnBuscarMaestro;
    private Button btnBuscarAlumno;
    private TextView tvNombreAlumno;
    private TextView tvNombreMaestro;
    private EditText etId;
    private Spinner spAlumnos;
    private Spinner spMaestros;
    private RecyclerView rvMateriasAlumno;
    List<Materia> listMaterias = new ArrayList<>();
    public static  int nivel = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_alumno_maestro);

        btnInicioDeClase = (Button) findViewById(R.id.btn_inicio_clases_paquete);
        tvNombreAlumno = (TextView) findViewById(R.id.tv_nombre_alumno_inicioclase_paquete);
        tvNombreMaestro = (TextView) findViewById(R.id.tv_nombre_maestro_inicioclase_paquete);
        etId = (EditText) findViewById(R.id.etSearchBox_alumMaestro);
        btnBuscarAlumno = (Button) findViewById(R.id.btn_buscar_alumno);
        btnBuscarMaestro = (Button) findViewById(R.id.btn_buscar_maestro);
        spAlumnos = (Spinner) findViewById(R.id.spiner_alumnos);
        spMaestros = (Spinner) findViewById(R.id.spiner_maestros);
        rvMateriasAlumno = (RecyclerView) findViewById(R.id.materias_recycler_iniciar_clase);
        populatePersons();
        arrays();
        setDatosPersons();
        getIdText();
    }

    private void getIdText() {
        btnBuscarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getPersonById( etId.getText().toString() , 1 ).length() > 0 ){
                    tvNombreAlumno.setText( getPersonById( etId.getText().toString() , 1 ));
                    /*buscar persona en bdd*/
                    BaseHelper helper = new BaseHelper(getApplicationContext(), "Demo" , null , 1);
                    SQLiteDatabase db = helper.getReadableDatabase();
                    String sql = "select id ,  idMateria , horasTomadas , horasRestantes from paquete where idAlumno="+etId.getText().toString();
                    Cursor c = db.rawQuery( sql , null);
                    if(c.moveToFirst()){
                        do{
                            System.out.println(" id : "+ c.getInt(0) + " idMateria : " + c.getInt(1) + " -horasTomadas : "+ c.getInt(2) + " horasRestantes : "+ c.getInt(3) );
/*                            listMaterias.add( new Alumno( c.getInt(0) , c.getString(1) , c.getString(2) , c.getString(3) ) );*/
                        }while( c.moveToNext());
                    }
                    db.close();

                }
                else{
                    tvNombreAlumno.setText( "id no encontrado !");
                }

            }
        });
        btnBuscarMaestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( getPersonById( etId.getText().toString() , 0 ).length() > 0 )
                    tvNombreMaestro.setText(getPersonById( etId.getText().toString() , 0 ) );
                else
                    tvNombreMaestro.setText("id no encontrado ! ");
            }
        });
    }

    private void getMaterias(String id) {
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("select idMateria , horasRestantes , fecha from Paquete where idAlumno=" + id, null);
        if(c.moveToFirst() ){
            do{
                System.out.println("id : " + c.getInt(0) + " imagen : " + utils.getImageMateria(utils.geMateriaById(c.getInt(0)))  + " materia : " + utils.geMateriaById(c.getInt(0)) + " horas : " + c.getInt(1) + " fecha : "+ c.getString(2));
/*                listMaterias.add(new Materia(  ));*/
            }while(c.moveToNext());
        }
        db.close();
        c.close();
    }

    private void setDatosPersons() {
        spAlumnos.setOnItemSelectedListener(onItemSelectedListener1);
        spMaestros.setOnItemSelectedListener(onItemSelectedListener2);
    }

    AdapterView.OnItemSelectedListener onItemSelectedListener1 = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            tvNombreAlumno.setText(spAlumnos.getSelectedItem().toString());

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    AdapterView.OnItemSelectedListener onItemSelectedListener2 = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            tvNombreMaestro.setText(spMaestros.getSelectedItem().toString());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void arrays() {
        arrayAlumnos = new ArrayAdapter(this, R.layout.item_view_spiner, idAlumns);
        arrayMaestros = new ArrayAdapter(this, R.layout.item_view_spiner, idMaestros);

        arrayAlumnos.setDropDownViewResource(R.layout.item_view_spiner);
        arrayMaestros.setDropDownViewResource(R.layout.item_view_spiner);

        spAlumnos.setAdapter(arrayAlumnos);
        spMaestros.setAdapter(arrayMaestros);
    }


    private String getPersonById(String id , int persona){
        String nombre="";
        String token = " - ";
        String sql;
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] args = new String[] {id};
        if(persona == 1)
            sql = "select nombre from Alumnos where id=?";
        else
            sql = "select nombre from Maestros where id=?";

        Cursor c = db.rawQuery(sql, args);
        if (c.moveToFirst()) {
            nombre = c.getString(0);
        }
         return nombre;
    }


    private void populatePersons() {
        idAlumns = new ArrayList<>();
        idAlumns.add("Alumnos");
        idMaestros = new ArrayList<>();
        idMaestros.add("Maestros");

        String token = " - ";
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();

        String sqlAlumnos = "select id, nombre from Alumnos";
        String sqlMaestros = "select id, nombre from Maestros";
        Cursor c = db.rawQuery(sqlAlumnos, null);
        if (c.moveToFirst()) {
            do {

                idAlumns.add(c.getInt(0) + token + c.getString(1));
            } while (c.moveToNext());
        }
        c = db.rawQuery(sqlMaestros, null);
        if (c.moveToFirst()) {
            do {
                idMaestros.add(c.getInt(0) + token + c.getString(1));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
    }

    @Override
    public void onItemClick(Alumno alumno) {
        Intent intentAlumno = new Intent(SeleccionAlumnoMaestroActivity.this, AltaPaqueteActivity.class);
        intentAlumno.putExtra(AltaPaqueteActivity.EXTRA_ID, alumno.getId());
        startActivity(intentAlumno);
    }
}
