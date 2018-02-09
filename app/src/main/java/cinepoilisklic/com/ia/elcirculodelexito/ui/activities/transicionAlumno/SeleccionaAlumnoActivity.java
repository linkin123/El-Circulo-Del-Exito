package cinepoilisklic.com.ia.elcirculodelexito.ui.activities.transicionAlumno;


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
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.altaPaquete.AltaPaqueteActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.adapters.AlumnosAdapter;
import cinepoilisklic.com.ia.elcirculodelexito.ui.fragments.listaAlumnos.listaAlumnosFragment;

public class SeleccionaAlumnoActivity extends AppCompatActivity implements AlumnosAdapter.onItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecciona_alumno);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_list_alumnos, listaAlumnosFragment.newInstace())
                .commit();
    }

    @Override
    public void onItemClick(Alumno alumno) {
        Intent intentAlumno = new Intent(SeleccionaAlumnoActivity.this, AltaPaqueteActivity.class);
        intentAlumno.putExtra(AltaPaqueteActivity.EXTRA_ID, alumno.getId());
        startActivity(intentAlumno);
    }
}

