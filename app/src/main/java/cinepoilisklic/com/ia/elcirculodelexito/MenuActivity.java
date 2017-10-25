package cinepoilisklic.com.ia.elcirculodelexito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    private ImageButton btnAsesoriaTiempo;
    private ImageButton btnAsesoriaPaquete;
    private ImageButton btnAltaAlumno;
    private ImageButton btnAltaMaestro;
    private ImageButton btnReporteAlumno;
    private  ImageButton btnReporteMaestro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnAsesoriaTiempo = (ImageButton)findViewById(R.id.asesoria_tiempo);
        btnAsesoriaTiempo.setOnClickListener(onClickListener);

        btnAsesoriaPaquete = (ImageButton)findViewById(R.id.asesoria_paquete);
        btnAsesoriaPaquete.setOnClickListener(onClickListener);

        btnAltaAlumno = (ImageButton)findViewById(R.id.alta_alumno);
        btnAltaAlumno.setOnClickListener(onClickListener);

        btnAltaMaestro = (ImageButton)findViewById(R.id.alta_maestro);
        btnAltaMaestro.setOnClickListener(onClickListener);

        btnReporteAlumno = (ImageButton)findViewById(R.id.reporte_alumno);
        btnReporteAlumno.setOnClickListener(onClickListener);

        btnReporteMaestro = (ImageButton)findViewById(R.id.reporte_maestro);
        btnReporteMaestro.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.alta_alumno:
//                    Intent intent = new Intent(MenuActivity.this , AltaAlumnoActivity.class);
//                    startActivity(intent);
                    break;

            }
        }
    };
}

