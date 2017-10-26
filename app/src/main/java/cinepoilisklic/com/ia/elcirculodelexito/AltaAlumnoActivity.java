package cinepoilisklic.com.ia.elcirculodelexito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AltaAlumnoActivity extends AppCompatActivity {

    private Button btnAgregarMateria;
    private Button btnGeneradorQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_alumno);

        btnAgregarMateria = (Button) findViewById(R.id.btn_agregar_materia);
        btnAgregarMateria.setOnClickListener(onClickListener);

        btnGeneradorQR = (Button)findViewById(R.id.btn_generar_qr);
        btnGeneradorQR.setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.btn_agregar_materia:
                    Intent intent = new Intent(AltaAlumnoActivity.this , AgregarMateria.class);
                    startActivity(intent);
                    break;

                case R.id.btn_generar_qr:
                    Intent intent2 = new Intent(AltaAlumnoActivity.this , GeneradorQR.class);
                    startActivity(intent2);
                    break;
            }
        }
    };
}
