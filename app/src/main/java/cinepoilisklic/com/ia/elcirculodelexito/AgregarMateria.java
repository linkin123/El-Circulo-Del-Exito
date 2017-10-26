package cinepoilisklic.com.ia.elcirculodelexito;

import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Dell on 25/10/2017.
 */

public class AgregarMateria extends AppCompatActivity {

    Spinner spinnerMateria;
    Spinner spinnerHoras;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_materia);
        spinnerMateria = (Spinner)findViewById(R.id.spiner_materias);
        spinnerHoras = (Spinner)findViewById(R.id.spiner_horas);
        setdataSpinners();
    }

    private void setdataSpinners() {
        spinnerMateria.setAdapter(getDataAdapter(R.array.materia));
        spinnerHoras.setAdapter(getDataAdapter(R.array.horas));
  //      hideKeyboard(spinnerMateria);
  //      hideKeyboard(spinnerHoras);

    }
    private ArrayAdapter<CharSequence> getDataAdapter(@ArrayRes int array) {
        return ArrayAdapter.createFromResource(this, array, R.layout.item_view_spiner);

    }
/*
    private void hideKeyboard(Spinner spinner) {
        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return false;
            }
        });

    }
*/
}

