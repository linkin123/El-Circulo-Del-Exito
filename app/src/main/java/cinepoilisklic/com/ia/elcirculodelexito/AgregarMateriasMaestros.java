package cinepoilisklic.com.ia.elcirculodelexito;

import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AgregarMateriasMaestros extends AppCompatActivity {
    Spinner spinnerMateria;
    Spinner spinnerNivel;
    Button btnAgregarMateria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_materias_maestros);

        spinnerMateria=(Spinner)findViewById(R.id.sp_materias);
        spinnerNivel=(Spinner)findViewById(R.id.sp_Nivel);
        btnAgregarMateria=(Button)findViewById(R.id.btn_agregar_materia_maestros);


        setdataSpinners();
    }

    private void setdataSpinners() {
        spinnerMateria.setAdapter(getDataAdapter(R.array.materia));
        spinnerNivel.setAdapter(getDataAdapter(R.array.nivel));


    }
    private ArrayAdapter<CharSequence> getDataAdapter(@ArrayRes int array) {
        return ArrayAdapter.createFromResource(this, array, R.layout.item_view_spiner);



    }
}
