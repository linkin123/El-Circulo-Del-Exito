package cinepoilisklic.com.ia.elcirculodelexito.ui.altaPaquete;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.ArrayRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Materia;
import cinepoilisklic.com.ia.elcirculodelexito.R;

import static cinepoilisklic.com.ia.elcirculodelexito.Utils.utils.printToast;
import static cinepoilisklic.com.ia.elcirculodelexito.data.Niveles.PREPARATORIA_PAQUETE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.Niveles.PRIMARIA_PAQUETE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.Niveles.SECUNDARIA;
import static cinepoilisklic.com.ia.elcirculodelexito.data.Niveles.SECUNDARIA_PAQUETE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.Niveles.UNIVERSIDAD_PAQUETE;


public class AltaPaqueteActivity extends AppCompatActivity implements MateriasAdapter.onItemClickListener, RadioGroup.OnCheckedChangeListener {

    public static String EXTRA_ID = "id";
    private int idAlumno;
    private int nivel=300;

    MateriasAdapter materiasAdapter;
    private Button btnAgregarMateria;
    private Button btnRegistrarPaquete;
    private TextView nombreAlumno;
    RecyclerView recyclerView;
    Spinner spinnerMateria;
    Spinner spinnerHoras;
    RadioGroup rgNivel;
    EditText etCostoTotal;
    EditText etDiferencia;
    EditText etRegistroPago;

    public List<Materia> list = new ArrayList<>();
    List<Materia> listMaterias = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_paquete);

        btnAgregarMateria = (Button) findViewById(R.id.btn_agregar_materia);
        btnAgregarMateria.setOnClickListener(onClickListener);
        btnRegistrarPaquete = (Button) findViewById(R.id.btn_registrar_paquete);
        btnRegistrarPaquete.setOnClickListener(onClickListener);

        nombreAlumno = (TextView) findViewById(R.id.tv_nombre_alumno);
        recyclerView = (RecyclerView) findViewById(R.id.materias_recycler);
        spinnerMateria = (Spinner) findViewById(R.id.spiner_materias);
        spinnerHoras = (Spinner) findViewById(R.id.spiner_horas);
        etCostoTotal = (EditText) findViewById(R.id.et_alta_paquete_costo_total);
        etDiferencia = (EditText) findViewById(R.id.et_alta_paquete_diferencia);
        etRegistroPago = (EditText) findViewById(R.id.et_alta_paquete_registro_pago);
        rgNivel = (RadioGroup) findViewById(R.id.rg_nivel);
        rgNivel.setOnCheckedChangeListener(this);

        initAdapter();
        setdataSpinnersPaquete();
        recibirDatos(savedInstanceState);
        checarCambio();

    }

    private void checarCambio() {
        etRegistroPago.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setPrecioDiferencia();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setPrecioDiferencia() {
        if(etRegistroPago.length() > 0){
            int pago = Integer.valueOf(String.valueOf(etRegistroPago.getText()));
            int costo = Integer.valueOf(String.valueOf(etCostoTotal.getText()));
            int diferencia = pago - costo;
            etDiferencia.setText( String.valueOf( diferencia ) );
        }

    }

    private void recibirDatos(Object savedInstanceState) {

        Intent intent = getIntent();
        if (savedInstanceState == null) {
            if (intent.hasExtra(EXTRA_ID)) {
                idAlumno = intent.getIntExtra(EXTRA_ID, 1);
                consultarAlumno(idAlumno);
            }
        }
    }

    private void consultarAlumno(int id) {
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("select Nombre from alumnos where id=" + id, null);
        c.moveToFirst();
        setNameAlumno(c.getString(0));
        db.close();
    }

    private void setNameAlumno(String nameAlumno) {
        nombreAlumno.setText(nameAlumno);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.btn_agregar_materia:
                    addPaquete(spinnerMateria.getSelectedItemId()-1 ,spinnerMateria.getSelectedItem().toString(), spinnerHoras.getSelectedItem().toString());
                    setPrecio();
                    break;

                case R.id.btn_registrar_paquete:
                    if(camposNoEstanVacios()){
                        Log.d("TAG", "se insertaron en la bdd idAlumno:"+idAlumno +", nivel:"+nivel);
                        for(int i=0; i<list.size(); i++){
                            System.out.println(list.get(i).getNombre() + "--"+ list.get(i).getHoras() + "--" + list.get(i).getFecha());
                        }

/*                        insertarBDD();*/

                    }
                    break;

            }
        }
    };

    private void setPrecio() {
        int costoTotal = nivel*list.size();
        etCostoTotal.setText(String.valueOf( costoTotal ));
    }

    private void insertarBDD() {

    }

    private boolean camposNoEstanVacios() {
        if(materiasAdapter.getItemCount() == 0){
            printToast(getApplicationContext() , "debe seleccionar por lo menos un paquete");
            return false;
        }
        return true;
    }

    private void addPaquete(long id , String Materia, String horas) {
        Calendar calendarNow = new GregorianCalendar(TimeZone.getTimeZone("Europe/Madrid"));
        int monthDay = calendarNow.get(Calendar.DAY_OF_MONTH);
        int month = calendarNow.get(Calendar.MONTH) + 1;
        int anio = calendarNow.get(Calendar.YEAR);
        String fecha = String.valueOf(monthDay + "/" + month + "/" + anio);

        if( !estaEnLista(id) ){
            listMaterias.add(new Materia(id , getImageMateria(Materia), Materia, Integer.parseInt(horas), fecha));
            materiasAdapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(this, "Este paquete ya fue seleccionado", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean estaEnLista(long id){
        for(int i=0; i<listMaterias.size(); i++)
            if(id == listMaterias.get(i).getId() )
                return true;
            return false;
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rbtn_primaria:
                nivel = PRIMARIA_PAQUETE;
                setPrecio();
                setPrecioDiferencia();
                break;
            case R.id.rbtn_secundaria:
                nivel = SECUNDARIA_PAQUETE;
                setPrecio();
                setPrecioDiferencia();
                break;
            case R.id.rbtn_prepa:
                nivel = PREPARATORIA_PAQUETE;
                setPrecio();
                setPrecioDiferencia();
                break;
            case R.id.rbtn_universidad:
                nivel = UNIVERSIDAD_PAQUETE;
                setPrecio();
                setPrecioDiferencia();
                break;

        }

    }
}
