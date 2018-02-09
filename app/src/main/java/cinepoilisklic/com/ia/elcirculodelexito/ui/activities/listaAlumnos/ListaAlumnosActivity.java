package cinepoilisklic.com.ia.elcirculodelexito.ui.activities.listaAlumnos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import java.util.ArrayList;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Alumno;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.reporteAlumno.AsesoriasAlumnoActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.adapters.AlumnosAdapter;
import cinepoilisklic.com.ia.elcirculodelexito.ui.fragments.listaAlumnos.listaAlumnosFragment;

public class ListaAlumnosActivity extends AppCompatActivity implements AlumnosAdapter.onItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_alumnos);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_list_alumnos, listaAlumnosFragment.newInstace())
                .commit();
    }


    @Override
    public void onItemClick(Alumno alumno) {
        Intent intentAlumno = new Intent(ListaAlumnosActivity.this, AsesoriasAlumnoActivity.class);
        intentAlumno.putExtra(AsesoriasAlumnoActivity.EXTRA_ID, alumno.getId());
        startActivity(intentAlumno);
    }
}
