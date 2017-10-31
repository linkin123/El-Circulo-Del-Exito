package cinepoilisklic.com.ia.elcirculodelexito;

import android.content.Intent;
import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ReporteAlumnosActivity extends AppCompatActivity implements ListAdapter.onItemClickListener{


    ArrayList<Alumno> persons;
    private EditText etSearchBox;
    private Spinner spinnerBuscador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_alumnos);

        RecyclerView audioRv = (RecyclerView) findViewById(R.id.audio_rv);
        etSearchBox = (EditText) findViewById(R.id.etSearchBox);
        spinnerBuscador = (Spinner)findViewById(R.id.spinner_buscador);
        setdataSpinners();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        audioRv.setLayoutManager(linearLayoutManager);
        populatePersons();
        final ListAdapter listAdapter = new ListAdapter(persons , this);
        audioRv.setAdapter(listAdapter);

        etSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listAdapter.getFilter().filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setdataSpinners() {
        spinnerBuscador.setAdapter(getDataAdapter(R.array.opcionesBuscador));

    }
    private ArrayAdapter<CharSequence> getDataAdapter(@ArrayRes int array) {
        return ArrayAdapter.createFromResource(this, array, R.layout.itemview_spinner);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_reporte_alumnos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void populatePersons(){
        persons = new ArrayList<>();
        persons.add(new Alumno("Karla Lopez Herrrera", "no ", 123, 0 , R.drawable.pegatina_circulo_rojo));
        persons.add(new Alumno("Fernando juarez perez","si ", 127 , 6 ,R.drawable.pegatina_circulo_verde));
        persons.add(new Alumno("isis gomez avila","no ", 232, 0, R.drawable.pegatina_circulo_rojo));
        persons.add(new Alumno("jose luis pavia romero","no ",112, 0 ,R.drawable.pegatina_circulo_rojo));
        persons.add(new Alumno("Dorian guzman hernandez","si ",223, 4, R.drawable.pegatina_circulo_verde));
        persons.add(new Alumno("Claudia soto garcia","si ", 345 , 8 ,R.drawable.pegatina_circulo_verde));
    }

    @Override
    public void onItemClick(Alumno alumno) {
        Toast.makeText(this, alumno.getName() , Toast.LENGTH_LONG).show();
        Intent intentAlumno = new Intent(ReporteAlumnosActivity.this , AsesoriasAlumnoActivity.class);
        intentAlumno.putExtra(AsesoriasAlumnoActivity.EXTRA_ID , alumno.getId());
        startActivity(intentAlumno);
    }
}
