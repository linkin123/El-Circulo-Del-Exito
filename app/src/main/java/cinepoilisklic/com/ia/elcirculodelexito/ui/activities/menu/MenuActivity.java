package cinepoilisklic.com.ia.elcirculodelexito.ui.activities.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.listaAlumnos.ListaAlumnosActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.listaMaestros.ListaMaestrosActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.opcionesAlumno.OpcionesActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.registroAlumno.AltaAlumnActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.registroMaestro.AltaMaestroActivity;

public class MenuActivity extends AppCompatActivity {

    private ImageButton btnAsesoriaTiempo;
    private ImageButton btnAsesoriaPaquete;
    private ImageButton btnAltaAlumno;
    private ImageButton btnAltaMaestro;
    private ImageButton btnReporteAlumno;
    private ImageButton btnReporteMaestro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnAsesoriaTiempo = (ImageButton) findViewById(R.id.asesoria_tiempo);
        btnAsesoriaTiempo.setOnClickListener(onClickListener);

        btnAsesoriaPaquete = (ImageButton) findViewById(R.id.asesoria_paquete);
        btnAsesoriaPaquete.setOnClickListener(onClickListener);

        btnAltaAlumno = (ImageButton) findViewById(R.id.alumno);
        btnAltaAlumno.setOnClickListener(onClickListener);

        btnAltaMaestro = (ImageButton) findViewById(R.id.maestro);
        btnAltaMaestro.setOnClickListener(onClickListener);

        btnReporteAlumno = (ImageButton) findViewById(R.id.reporte_alumno);
        btnReporteAlumno.setOnClickListener(onClickListener);

        btnReporteMaestro = (ImageButton) findViewById(R.id.reporte_maestro);
        btnReporteMaestro.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.alumno:
                    startActivity(new Intent(MenuActivity.this, AltaAlumnActivity.class));
                    break;

                case R.id.reporte_alumno:
                    startActivity(new Intent(MenuActivity.this, ListaAlumnosActivity.class));
                    break;

                case R.id.asesoria_paquete:
                    startActivity(new Intent(MenuActivity.this, OpcionesActivity.class));
                    break;

                case R.id.maestro:
                    startActivity(new Intent(MenuActivity.this, AltaMaestroActivity.class));
                    break;

                case R.id.reporte_maestro:
                    startActivity(new Intent(MenuActivity.this , ListaMaestrosActivity.class));
                    break;
            }
        }
    };
}


