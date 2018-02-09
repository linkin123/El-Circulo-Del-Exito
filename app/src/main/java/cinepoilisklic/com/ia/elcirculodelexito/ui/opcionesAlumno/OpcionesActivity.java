package cinepoilisklic.com.ia.elcirculodelexito.ui.opcionesAlumno;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.ui.altaPaquete.AltaPaqueteActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.registroAlumno.AltaAlumnActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.transicionAlumno.SeleccionaAlumnoActivity;

public class OpcionesActivity extends AppCompatActivity {

    private Button btnRegistro;
    private Button btnAltaPaquete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        btnRegistro = (Button) findViewById(R.id.opciones_btn_elegir_maestro);

        btnRegistro.setOnClickListener(onClickListener);

        btnAltaPaquete = (Button)findViewById(R.id.opciones_btn_alta_paquete);
        btnAltaPaquete.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.opciones_btn_elegir_maestro:
/*                    Intent intentAltaAlumno = new Intent(OpcionesActivity.this , AltaAlumnActivity.class);
                    startActivity(intentAltaAlumno);*/
                    break;

                case R.id.opciones_btn_alta_paquete:
                    Intent intent = new Intent(OpcionesActivity.this , AltaPaqueteActivity.class);
                    startActivity(intent);
                    break;

            }
        }
    };
}
