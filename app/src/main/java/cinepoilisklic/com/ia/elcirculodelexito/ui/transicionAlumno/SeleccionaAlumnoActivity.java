package cinepoilisklic.com.ia.elcirculodelexito.ui.transicionAlumno;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Alumno;
import cinepoilisklic.com.ia.elcirculodelexito.ui.altaPaquete.AltaPaqueteActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.listaAlumnos.AlumnosAdapter;
import cinepoilisklic.com.ia.elcirculodelexito.ui.reporteAlumno.AsesoriasAlumnoActivity;

public class SeleccionaAlumnoActivity extends AppCompatActivity implements AlumnosAdapter.onItemClickListener {

    ArrayList<Alumno> persons;
    private EditText etSearchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecciona_alumno);

        RecyclerView audioRv = (RecyclerView) findViewById(R.id.audio_rv);
        etSearchBox = (EditText) findViewById(R.id.etSearchBox);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        audioRv.setLayoutManager(linearLayoutManager);
        populatePersons();
        final AlumnosAdapter alumnosAdapter = new AlumnosAdapter(persons, this);
        audioRv.setAdapter(alumnosAdapter);


        etSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                alumnosAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_reporte_alumnos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void populatePersons() {
        persons = new ArrayList<>();
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select Id , Nombre, NombrePadre , telefono from alumnos";
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()) {
            do {
                persons.add(new Alumno(c.getInt(0), c.getString(1), c.getString(2), c.getString(3)));
            } while (c.moveToNext());
        }
        db.close();

/*        persons.add(new Alumno("Karla Lopez Herrrera", "no ", 123, 0 , R.drawable.pegatina_circulo_rojo, "15/08/2017"));
        persons.add(new Alumno("Fernando juarez perez","si ", 127 , 6 ,R.drawable.pegatina_circulo_verde, "10/02/2018"));
        persons.add(new Alumno("isis gomez avila","no ", 232, 0, R.drawable.pegatina_circulo_rojo, "02/04/2016"));
        persons.add(new Alumno("jose luis pavia romero","no ",112, 0 ,R.drawable.pegatina_circulo_rojo, "03/03/2016"));
        persons.add(new Alumno("Dorian guzman hernandez","si ",223, 4, R.drawable.pegatina_circulo_verde, "03/06/2018"));
        persons.add(new Alumno("Claudia soto garcia","si ", 345 , 8 ,R.drawable.pegatina_circulo_verde , "05/"));*/
    }

    @Override
    public void onItemClick(Alumno alumno) {
        Intent intentAlumno = new Intent(cinepoilisklic.com.ia.elcirculodelexito.ui.transicionAlumno.SeleccionaAlumnoActivity.this, AltaPaqueteActivity.class);
        intentAlumno.putExtra(AltaPaqueteActivity.EXTRA_ID, alumno.getId());
        startActivity(intentAlumno);
    }
}

