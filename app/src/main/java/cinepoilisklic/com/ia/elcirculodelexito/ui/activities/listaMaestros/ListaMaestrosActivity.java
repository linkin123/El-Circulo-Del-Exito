package cinepoilisklic.com.ia.elcirculodelexito.ui.activities.listaMaestros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Alumno;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.listaAlumnos.ListaAlumnosActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.reporteAlumno.AsesoriasAlumnoActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.fragments.listaAlumnos.listaAlumnosFragment;
import cinepoilisklic.com.ia.elcirculodelexito.ui.fragments.listaMaestros.listaMaestrosFragment;

public class ListaMaestrosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_maestros);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_list_maestros, listaMaestrosFragment.newInstace())
                .commit();
    }


/*    @Override
    public void onItemClick(Alumno alumno) {
        Intent intentAlumno = new Intent(ListaAlumnosActivity.this, AsesoriasAlumnoActivity.class);
        intentAlumno.putExtra(AsesoriasAlumnoActivity.EXTRA_ID, alumno.getId());
        startActivity(intentAlumno);
    }
    }*/
}
