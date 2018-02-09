package cinepoilisklic.com.ia.elcirculodelexito.ui.activities.seleccionAlumnoMaestro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Alumno;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.altaPaquete.AltaPaqueteActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.transicionAlumno.SeleccionaAlumnoActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.adapters.AlumnosAdapter;
import cinepoilisklic.com.ia.elcirculodelexito.ui.fragments.listaAlumnos.listaAlumnosFragment;

public class SeleccionAlumnoMaestroActivity extends AppCompatActivity implements AlumnosAdapter.onItemClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_alumno_maestro);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_list_alumnos, listaAlumnosFragment.newInstace())
                .commit();
    }

    @Override
    public void onItemClick(Alumno alumno) {
        Intent intentAlumno = new Intent(SeleccionAlumnoMaestroActivity.this, AltaPaqueteActivity.class);
        intentAlumno.putExtra(AltaPaqueteActivity.EXTRA_ID, alumno.getId());
        startActivity(intentAlumno);
    }
}
