package cinepoilisklic.com.ia.elcirculodelexito.ui.altaPaquete;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import cinepoilisklic.com.ia.elcirculodelexito.data.models.Materia;
import cinepoilisklic.com.ia.elcirculodelexito.R;


public class AltaPaqueteActivity extends AppCompatActivity implements MateriasAdapter.onItemClickListener {

    MateriasAdapter materiasAdapter;
    private Button btnAgregarMateria;
    private Button btnGeneradorQR;
    RecyclerView recyclerView;
    Spinner spinnerMateria;
    Spinner spinnerHoras;

    public List<Materia> list = new ArrayList<>();

    List<Materia> listMaterias = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_paquete);

        btnAgregarMateria = (Button) findViewById(R.id.btn_agregar_materia);
        btnAgregarMateria.setOnClickListener(onClickListener);

        btnGeneradorQR = (Button) findViewById(R.id.btn_generar_qr);
        btnGeneradorQR.setOnClickListener(onClickListener);

        recyclerView = (RecyclerView) findViewById(R.id.materias_recycler);
        initAdapter();

        spinnerMateria = (Spinner) findViewById(R.id.spiner_materias);
        spinnerHoras = (Spinner) findViewById(R.id.spiner_horas);
        setdataSpinnersPaquete();



    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.btn_agregar_materia:
                    addPaquete(spinnerMateria.getSelectedItem().toString(), spinnerHoras.getSelectedItem().toString());
                    break;
            }
        }
    };

    private void addPaquete(String Materia, String horas) {
        Log.d("TAG", "horas : " + horas);
        Log.d("TAG", "materia : " + Materia);
        Calendar calendarNow = new GregorianCalendar(TimeZone.getTimeZone("Europe/Madrid"));
        int monthDay = calendarNow.get(Calendar.DAY_OF_MONTH);
        int month = calendarNow.get(Calendar.MONTH) + 1;
        int anio = calendarNow.get(Calendar.YEAR) + 1;
        String fecha = String.valueOf(monthDay + "/" + month + "/" + anio);
        listMaterias.add(new Materia(getImageMateria(Materia), Materia, Integer.parseInt(horas), fecha));
        materiasAdapter.notifyDataSetChanged();
    }

    public void initAdapter() {
        list = listMaterias;
        materiasAdapter = new MateriasAdapter(this, list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(materiasAdapter);
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

    private int getImageMateria(String Materia) {
        if (Materia.equals("Administración"))
            return R.drawable.administracion;
        if (Materia.equals("Biología"))
            return R.drawable.biologiax;
        if (Materia.equals("Comprensión Lectora"))
            return R.drawable.comprension_lectora;
        if (Materia.equals("Economía"))
            return R.drawable.economia;
        if (Materia.equals("Español"))
            return R.drawable.espaniolx;
        if (Materia.equals("Estadistica"))
            return R.drawable.estadistica;
        if (Materia.equals("Física"))
            return R.drawable.fisica;
        if (Materia.equals("Geografía"))
            return R.drawable.geografiax;
        if (Materia.equals("Historia Universal"))
            return R.drawable.historia_universal;
        if (Materia.equals("Historia de México"))
            return R.drawable.hitoriamexicox;
        if (Materia.equals("Inglés"))
            return R.drawable.ingles;
        if (Materia.equals("Literatura"))
            return R.drawable.literaturax;
        if (Materia.equals("Matemáticas"))
            return R.drawable.matematicasx;
        if (Materia.equals("Pensamiento Analítico"))
            return R.drawable.pensamiento_analitico;
        if (Materia.equals("Psicología"))
            return R.drawable.psicologia;
        if (Materia.equals("Química"))
            return R.drawable.quimicax;


        return 0;
    }

    @Override
    public void onItemClick(Materia mateia) {

    }
}
