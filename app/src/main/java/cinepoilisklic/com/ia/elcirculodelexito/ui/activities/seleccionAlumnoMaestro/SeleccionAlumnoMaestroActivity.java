package cinepoilisklic.com.ia.elcirculodelexito.ui.activities.seleccionAlumnoMaestro;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Alumno;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.altaPaquete.AltaPaqueteActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.adapters.AlumnosAdapter;

public class SeleccionAlumnoMaestroActivity extends AppCompatActivity implements AlumnosAdapter.onItemClickListener{

    ArrayList<String> idAlumns;
    ArrayList<String> idMaestros;
    ArrayAdapter arrayAlumnos;
    ArrayAdapter arrayMaestros;
    private int idAlumno;
    private int idMaestro;
    private Button btnInicioDeClase;
    private TextView tvNombreAlumno;
    private TextView tvNombreMaestro;
    private EditText etFilterAlumno;
    private EditText etFilterMaestro;
    private Spinner spAlumnos;
    private Spinner spMaestros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_alumno_maestro);

        btnInicioDeClase = (Button) findViewById(R.id.btn_inicio_clases_paquete);
        tvNombreAlumno = (TextView) findViewById(R.id.tv_nombre_alumno_inicioclase_paquete);
        tvNombreMaestro = (TextView) findViewById(R.id.tv_nombre_maestro_inicioclase_paquete);
        etFilterAlumno = (EditText) findViewById(R.id.etSearchBox_alumMaestro);
        etFilterMaestro = (EditText) findViewById(R.id.etSearchBox_maestroAlumno);
        spAlumnos = (Spinner) findViewById(R.id.spiner_alumnos);
        spMaestros = (Spinner) findViewById(R.id.spiner_maestros);
        populatePersons();
        arrays();
        setDatosPersons();
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


    private int getPersonsById(String nombre){
        String token = " - ";
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        int id=0;
        String sqlAlumnos = "select id from Alumnos where nombre= '" + nombre + "'";
        Cursor c = db.rawQuery(sqlAlumnos, null);
        if (c.moveToFirst()) {
            id = c.getInt(0);
        }
         return id;
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
