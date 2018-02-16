package cinepoilisklic.com.ia.elcirculodelexito.ui.activities.reporteAlumno;

import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Asesoria;
import cinepoilisklic.com.ia.elcirculodelexito.ui.adapters.ListAdapterReporteAlumno;

public class AsesoriasAlumnoActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "id";
    public int id;
    TextView txtId;
    ArrayList<Asesoria> asesorias;
    private EditText etSearchBox;
    private Spinner spinnerBuscador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asesorias_alumno);
/*
        Intent intent = getIntent();
        if(savedInstanceState == null){
            if(intent.hasExtra(EXTRA_ID)){
                id = intent.getIntExtra(EXTRA_ID , 22);
                txtId = (TextView)findViewById(R.id.ash);
                txtId.setText(String.valueOf(id));
            }
        }
*/
        RecyclerView audioRv = (RecyclerView)findViewById(R.id.audio_rv);
        etSearchBox = (EditText) findViewById(R.id.etSearchBox);
        spinnerBuscador = (Spinner) findViewById(R.id.spinner_buscador);

        setdataSpinners();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        audioRv.setLayoutManager(linearLayoutManager);
        populatePersons();
        final ListAdapterReporteAlumno listAdapterReporteAlumno = new ListAdapterReporteAlumno(asesorias);
        audioRv.setAdapter(listAdapterReporteAlumno);


        etSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listAdapterReporteAlumno.getFilter().filter(s.toString());
                System.out.println("nada");
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setdataSpinners() {
        spinnerBuscador.setAdapter(getDataAdapter(R.array.opcionesBuscadorAsesorias));

    }
    private ArrayAdapter<CharSequence> getDataAdapter(@ArrayRes int array) {
        return ArrayAdapter.createFromResource(this, array, R.layout.itemview_spinner);

    }

    public void populatePersons(){
        asesorias = new ArrayList<>();
        asesorias.add(new Asesoria(R.drawable.matematicasx , R.drawable.professor_icon28 , "jose luis", "04/12/12", "preparatoria", "1 hr , 23 min", "8h , 37 min "));
        asesorias.add(new Asesoria(R.drawable.biologiax , R.drawable.professor_icon28 , "jorge uribe", "14/10/15", "secundaria", "2 hr , 00 min", "8h , 00 min "));
        asesorias.add(new Asesoria(R.drawable.fisica , R.drawable.professor_icon28 , "Ramon bermudez", "24/02/14", "primaria", "4 hr , 03 min", "5h , 57 min "));
    }


}
