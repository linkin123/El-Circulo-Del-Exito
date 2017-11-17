package cinepoilisklic.com.ia.elcirculodelexito;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class AltaAlumnoActivity extends AppCompatActivity implements ListAdapterAlumnosConPaquete.onItemClickListener, MateriasAdapter.onItemClickListener {


    Spinner spinnerMateria;
    Spinner spinnerHoras;


    RecyclerView recyclerView;
    RecyclerView audioRv;
    MateriasAdapter materiasAdapter;


    private Button btnAgregarMateria;
    private Button btnGeneradorQR;


    ArrayList<Alumno> persons;
    public List<Materia> list = new ArrayList<>();
    private EditText etSearchBox;
    private Spinner spinnerBuscador;

    List<Materia> listMaterias = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_alumno);

        btnAgregarMateria = (Button) findViewById(R.id.btn_agregar_materia);
        btnAgregarMateria.setOnClickListener(onClickListener);

        btnGeneradorQR = (Button)findViewById(R.id.btn_generar_qr);
        btnGeneradorQR.setOnClickListener(onClickListener);

        audioRv = (RecyclerView) findViewById(R.id.audio_rv);
        etSearchBox = (EditText) findViewById(R.id.etSearchBox);
        spinnerBuscador = (Spinner)findViewById(R.id.spinner_buscador);
        setdataSpinners();

        recyclerView = (RecyclerView)findViewById(R.id.materias_recycler);
        initAdapter();

        spinnerMateria = (Spinner)findViewById(R.id.spiner_materias);
        spinnerHoras = (Spinner)findViewById(R.id.spiner_horas);
        setdataSpinnersPaquete();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        audioRv.setLayoutManager(linearLayoutManager);

        populatePersons();

        final ListAdapterAlumnosConPaquete listAdapterAlumnosConPaquete = new ListAdapterAlumnosConPaquete(persons , this);
        audioRv.setAdapter(listAdapterAlumnosConPaquete);

        etSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                listAdapterAlumnosConPaquete.getFilter().filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    private void setdataSpinnersPaquete() {
        spinnerMateria.setAdapter(getDataAdapterPaquete(R.array.materia));
        spinnerHoras.setAdapter(getDataAdapterPaquete(R.array.horas));
              hideKeyboard(spinnerMateria);
              hideKeyboard(spinnerHoras);

    }

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


    private ArrayAdapter<CharSequence> getDataAdapterPaquete(@ArrayRes int array) {
        return ArrayAdapter.createFromResource(this, array, R.layout.item_view_spiner);

    }

    public void initAdapter(){
        list = listMaterias;
        materiasAdapter = new MateriasAdapter(this, list , this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(materiasAdapter);
    }


    private List<Materia> getList(){
        //creamos una lista llamada list de tipo <UserModel>


//        list.add(new Materia(R.drawable.matematicasx,"Matemáticas", 10, "10/agosto/2018"));
/*
        list.add(new Materia(R.drawable.ticsx,"Tics", 20, "20/Mayo/2018"));
        list.add(new Materia(R.drawable.comprension_lectora,"Comprensión lectora", 16, "01/Noviembre/2018"));
        list.add(new Materia(R.drawable.espaniolx,"Español", 10, "10/Septiembre/2018"));
        list.add(new Materia(R.drawable.geografiax,"Geografía", 20, "20/Mayo/2018"));
        list.add(new Materia(R.drawable.administracion,"Administración", 16, "01/Noviembre/2018"));
*/
        return list;

    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.btn_agregar_materia:
                    addPaquete(spinnerMateria.getSelectedItem().toString() , spinnerHoras.getSelectedItem().toString());
                    break;

                case R.id.btn_generar_qr:
                    Intent intent2 = new Intent(AltaAlumnoActivity.this , GeneradorQR.class);
                    startActivity(intent2);
                    break;
            }
        }
    };

    private void addPaquete(String Materia , String horas){
        Log.d("TAG", "horas : "+ horas);
        Log.d("TAG", "materia : "+ Materia);
        Calendar calendarNow = new GregorianCalendar(TimeZone.getTimeZone("Europe/Madrid"));
        int monthDay =calendarNow.get(Calendar.DAY_OF_MONTH);
        int month = calendarNow.get(Calendar.MONTH)+1;
        int anio = calendarNow.get(Calendar.YEAR)+1;
        String fecha = String.valueOf(monthDay + "/" + month + "/" +  anio);
        listMaterias.add(new Materia(getImageMateria(Materia) , Materia, Integer.parseInt(horas),  fecha ));
        materiasAdapter.notifyDataSetChanged();
    }

    private int getImageMateria(String Materia){
        if(Materia.equals("Administración"))
            return R.drawable.administracion;
        if(Materia.equals("Biología"))
            return  R.drawable.biologiax;
        if(Materia.equals("Comprensión Lectora"))
            return  R.drawable.comprension_lectora;
        if(Materia.equals("Economía"))
            return  R.drawable.economia;
        if(Materia.equals("Español"))
            return  R.drawable.espaniolx;
        if(Materia.equals("Estadistica"))
            return  R.drawable.estadistica;
        if(Materia.equals("Física"))
            return  R.drawable.fisica;
        if(Materia.equals("Geografía"))
            return  R.drawable.geografiax;
        if(Materia.equals("Historia Universal"))
            return  R.drawable.historia_universal;
        if(Materia.equals("Historia de México"))
            return  R.drawable.hitoriamexicox;
        if(Materia.equals("Inglés"))
            return  R.drawable.ingles;
        if(Materia.equals("Literatura"))
            return  R.drawable.literaturax;
        if(Materia.equals("Matemáticas"))
            return  R.drawable.matematicasx;
        if(Materia.equals("Pensamiento Analítico"))
            return  R.drawable.pensamiento_analitico;
        if(Materia.equals("Psicología"))
            return  R.drawable.psicologia;
        if(Materia.equals("Química"))
            return  R.drawable.quimicax;



        return 0;
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
        persons.add(new Alumno("Karla Lopez Herrrera", "no ", 123, 0 , R.drawable.pegatina_circulo_rojo, "15/08/2017"));
        persons.add(new Alumno("Fernando juarez perez","si ", 127 , 6 ,R.drawable.pegatina_circulo_verde, "10/02/2018"));
        persons.add(new Alumno("isis gomez avila","no ", 232, 0, R.drawable.pegatina_circulo_rojo, "02/04/2016"));
        persons.add(new Alumno("jose luis pavia romero","no ",112, 0 ,R.drawable.pegatina_circulo_rojo, "03/03/2016"));
        persons.add(new Alumno("Dorian guzman hernandez","si ",223, 4, R.drawable.pegatina_circulo_verde, "03/06/2018"));
        persons.add(new Alumno("Claudia soto garcia","si ", 345 , 8 ,R.drawable.pegatina_circulo_verde , "05/"));
    }

    @Override
    public void onItemClick(Alumno alumno) {
        Toast.makeText(this, alumno.getName() , Toast.LENGTH_LONG).show();
        Intent intentAlumno = new Intent(AltaAlumnoActivity.this , AsesoriasAlumnoActivity.class);
        intentAlumno.putExtra(AsesoriasAlumnoActivity.EXTRA_ID , alumno.getId());
        startActivity(intentAlumno);
    }

    @Override
    public void onItemClick(Materia mateia) {


    }
}
