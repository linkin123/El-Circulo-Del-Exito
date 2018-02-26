package cinepoilisklic.com.ia.elcirculodelexito.ui.activities.opcionesAlumno;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.seleccionAlumnoMaestro.SeleccionAlumnoMaestroActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.transicionAlumno.SeleccionaAlumnoActivity;

public class OpcionesActivity extends AppCompatActivity {

    private Button btnRegistro;
    private Button btnAltaPaquete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        btnRegistro = (Button) findViewById(R.id.opciones_btn_entrar_a_clases);
        btnRegistro.setOnClickListener(onClickListener);
        btnAltaPaquete = (Button)findViewById(R.id.opciones_btn_alta_paquete);
        btnAltaPaquete.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.opciones_btn_entrar_a_clases:
                    Intent intent = new Intent(OpcionesActivity.this , SeleccionAlumnoMaestroActivity.class);
                    startActivity(intent);
                    break;

                case R.id.opciones_btn_alta_paquete:
                    intent = new Intent(OpcionesActivity.this , SeleccionaAlumnoActivity.class);
                    startActivity(intent);
                    break;

                case R.id.opciones_btn_salir_de_clases:
                    intent = new Intent(OpcionesActivity.this , SeleccionaAlumnoActivity.class);
                    startActivity(intent);
                    break;

            }
        }
    };
}
