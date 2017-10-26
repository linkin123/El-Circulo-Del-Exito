package cinepoilisklic.com.ia.elcirculodelexito;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class AltaMaestrosActivity extends AppCompatActivity {
    Spinner spinnerMateria;
    Spinner spinnerHoras;
    Button btnAgregarMateria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_maestros);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinnerMateria = (Spinner)findViewById(R.id.spiner_materias);
        spinnerHoras = (Spinner)findViewById(R.id.spiner_horas);
        //setdataSpinners();

        btnAgregarMateria=(Button)findViewById(R.id.btn_agregar_materia);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        btnAgregarMateria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AltaMaestrosActivity.this , AgregarMateriasMaestros.class);
                startActivity(intent);
            }
        });
    }

}
