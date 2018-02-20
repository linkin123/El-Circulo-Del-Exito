package cinepoilisklic.com.ia.elcirculodelexito.ui.activities.altaPaquete;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import butterknife.internal.Utils;
import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.Utils.utils;
import cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Materia;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.opcionesAlumno.OpcionesActivity;
import cinepoilisklic.com.ia.elcirculodelexito.ui.adapters.MateriasAdapter;
import cinepoilisklic.com.ia.elcirculodelexito.ui.adapters.MateriasLinealAdapter;

import static cinepoilisklic.com.ia.elcirculodelexito.Utils.utils.*;
import static cinepoilisklic.com.ia.elcirculodelexito.data.Niveles.PREPARATORIA_PAQUETE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.Niveles.PRIMARIA_PAQUETE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.Niveles.SECUNDARIA_PAQUETE;
import static cinepoilisklic.com.ia.elcirculodelexito.data.Niveles.UNIVERSIDAD_PAQUETE;


public class AltaPaqueteActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    public static String EXTRA_ID = "id";
    private int idAlumno;
    public static  int nivel = 300;
    private boolean aceptarRegistroSinPago = false;

    MateriasAdapter materiasAdapter;
    MateriasLinealAdapter materiasLinealAdapter;
    private Button btnAgregarMateria;
    private Button btnRegistrarPaquete;
    private TextView nombreAlumno;
    RecyclerView recyclerView;
    RecyclerView recyclerViewPrecios;
    Spinner spinnerMateria;
    Spinner spinnerHoras;
    RadioGroup rgNivel;
    public static EditText etCostoTotal;
    public static EditText etDiferencia;
    public static EditText etRegistroPago;
    public static TextView tvDiferencia;

    public static List<Materia> list = new ArrayList<>();
    List<Materia> listMaterias = new ArrayList();
    HashMap<Integer , String> materiasMap = new HashMap<Integer , String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_paquete);

        btnAgregarMateria = (Button) findViewById(R.id.btn_agregar_materia);
        btnAgregarMateria.setOnClickListener(onClickListener);
        btnRegistrarPaquete = (Button) findViewById(R.id.btn_registrar_paquete);
        btnRegistrarPaquete.setOnClickListener(onClickListener);

        nombreAlumno = (TextView) findViewById(R.id.tv_nombre_alumno);
        tvDiferencia = (TextView) findViewById(R.id.tv_diferencia);
        recyclerView = (RecyclerView) findViewById(R.id.materias_recycler);
        recyclerViewPrecios = (RecyclerView) findViewById(R.id.materias_precio_recycler);
        spinnerMateria = (Spinner) findViewById(R.id.spiner_materias);
        spinnerHoras = (Spinner) findViewById(R.id.spiner_horas);
        etCostoTotal = (EditText) findViewById(R.id.et_alta_paquete_costo_total);
        etDiferencia = (EditText) findViewById(R.id.et_alta_paquete_diferencia);
        etRegistroPago = (EditText) findViewById(R.id.et_alta_paquete_registro_pago);
        rgNivel = (RadioGroup) findViewById(R.id.rg_nivel);
        rgNivel.setOnCheckedChangeListener(this);

        initAdapters();
        setdataSpinnersPaquete();
        recibirDatos(savedInstanceState);
        checarCambio();


    }

    public int getIdOfMateria(String materia) {
        int id = 0;
        switch (materia) {
            case "Administracion":
                id = 1;
                break;
            case "Biología":
                id = 2;
                break;
            case "Comprensión lectora":
                id = 3;
                break;
            case "Economía":
                id = 4;
                break;
            case "Español":
                id = 5;
                break;
            case "Estadistica":
                id = 6;
                break;
            case "Física":
                id = 7;
                break;
            case "Geografía":
                id = 8;
                break;
            case "Historia Universal":
                id = 9;
                break;
            case "Historia de México":
                id = 10;
                break;
            case "Ingles":
                id = 11;
                break;
            case "Literatura":
                id = 12;
                break;
            case "Matemáticas":
                id = 13;
                break;
            case "Pensamiento Analítico":
                id = 14;
                break;
            case "Psicología":
                id = 15;
                break;
            case "Química":
                id = 16;
                break;
            case "TICS":
                id = 17;
                break;
        }
        return id;
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
        if(TextUtils.isDigitsOnly( etRegistroPago.getText()) && etRegistroPago.length() > 0){
            int pago;
            int costo;
            if (etRegistroPago.length() > 0 )
                pago = Integer.valueOf(String.valueOf(etRegistroPago.getText()));
            else
                pago = 0;
            if(etCostoTotal.length() > 0){
                costo = Integer.valueOf(String.valueOf(etCostoTotal.getText()));
            }
            else
                costo = 0;

            int diferencia = pago - costo;
            if(diferencia < 0){
                tvDiferencia.setText("debe : ");
                diferencia = -1*diferencia;
            }else
                tvDiferencia.setText("cambio : ");

            etDiferencia.setText("$"+String.valueOf(diferencia));

        }else{
            printToast(this , "debe ingresar una cantidad numerica válida ");
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
        c.close();
    }

    private void setNameAlumno(String nameAlumno) {
        nombreAlumno.setText(nameAlumno);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_agregar_materia:
                    if(!spinnerMateria.getSelectedItem().toString().equals("Materia"))
                        if(!spinnerHoras.getSelectedItem().toString().equals("horas")){
                            addPaquete(spinnerMateria.getSelectedItemId() - 1, spinnerMateria.getSelectedItem().toString(), spinnerHoras.getSelectedItem().toString());
                            setPrecio();
                            setPrecioDiferencia();
                        }
                        else
                            printToast(getApplicationContext() , "error : debe seleccionar número de horas");
                    else
                        printToast(getApplicationContext() , "error : debe seleccionar una materia");


                    break;
                case R.id.btn_registrar_paquete:
                    if (camposNoEstanVacios()) {
                        if( etRegistroPago.getText().length() == 0 )
                            mostrarAlertPagoEnCero();
                        else{
                            if(TextUtils.isDigitsOnly( etRegistroPago.getText())  ){
                                guardar(list);
                            }
                            else
                                printToast(getApplicationContext() , "debe ingresar una cantidad numerica válida en registro de pago ");
                        }
                    }
                    else
                        Toast.makeText(getApplicationContext(), "error : debe agregar un paquete al menos", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    private void mostrarAlertPagoEnCero() {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(AltaPaqueteActivity.this);
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿ Seguro que quiere dar de alta sin pago inicial ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                guardar(list);
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {

            }
        });
        dialogo1.show();
    }


    private void guardar(List<Materia> listMaterias) {
        for (Materia materia: listMaterias) {
                materia.getNombre();
        }
        
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            ContentValues c = new ContentValues();
            for (Materia materia : listMaterias) {
                c.put("idAlumno", idAlumno);
                c.put("idMateria", materia.getId());
                c.put("horasTomadas", 0);
                c.put("horasRestantes", materia.getHoras());
                c.put("fecha" , materia.getFecha());
                System.out.println(materia.getNombre() + "--" + materia.getHoras() + "--" + materia.getFecha());
            }
            db.insert("PAQUETE", null, c);
            db.close();


            Toast.makeText(this, "registro insertado", Toast.LENGTH_SHORT).show();
//            startActivity( new Intent(AltaPaqueteActivity.this , OpcionesActivity.class));
        } catch (Exception e) {
            Toast.makeText(this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        Log.d("TAG", "se insertaron en la bdd idAlumno:" + idAlumno + ", nivel:" + nivel);

    }

    private void setPrecio() {
        int costoTotal = 0;
        for (int i = 0; i < list.size(); i++) {
            costoTotal += nivel * (list.get(i).getHoras() / 10);
        }

        etCostoTotal.setText(String.valueOf(costoTotal));
    }

    private boolean camposNoEstanVacios() {
        if (materiasAdapter.getItemCount() > 0) {
            return true;
        }
        return false;
    }

    private void addPaquete(long id, String Materia, String horas) {
        Calendar calendarNow = new GregorianCalendar(TimeZone.getTimeZone("Europe/Madrid"));
        int monthDay = calendarNow.get(Calendar.DAY_OF_MONTH);
        int month = calendarNow.get(Calendar.MONTH) + 1;
        int anio = calendarNow.get(Calendar.YEAR);
        String fecha = String.valueOf(monthDay + "/" + month + "/" + anio);

        if (!estaEnLista(id)) {
            listMaterias.add(new Materia(id, utils.getImageMateria(Materia), Materia, Integer.parseInt(horas), fecha));
            materiasAdapter.notifyDataSetChanged();
            materiasLinealAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Este paquete ya fue seleccionado", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean estaEnLista(long id) {
        for (int i = 0; i < listMaterias.size(); i++)
            if (id == listMaterias.get(i).getId())
                return true;
        return false;
    }


    public void initAdapters() {
        list = listMaterias;
        materiasLinealAdapter = new MateriasLinealAdapter(this, list, nivel);
        materiasAdapter = new MateriasAdapter(this, list, onClickListener, materiasLinealAdapter);
        recyclerViewPrecios.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerViewPrecios.setAdapter(materiasLinealAdapter);
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



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbtn_primaria:
                nivel = PRIMARIA_PAQUETE;
                setPrecio();
                setPrecioDiferencia();
                initAdapters();
                break;
            case R.id.rbtn_secundaria:
                nivel = SECUNDARIA_PAQUETE;
                setPrecio();
                setPrecioDiferencia();
                initAdapters();
                break;
            case R.id.rbtn_prepa:
                nivel = PREPARATORIA_PAQUETE;
                setPrecio();
                setPrecioDiferencia();
                initAdapters();
                break;
            case R.id.rbtn_universidad:
                nivel = UNIVERSIDAD_PAQUETE;
                setPrecio();
                setPrecioDiferencia();
                initAdapters();
                break;

        }
    }


}
