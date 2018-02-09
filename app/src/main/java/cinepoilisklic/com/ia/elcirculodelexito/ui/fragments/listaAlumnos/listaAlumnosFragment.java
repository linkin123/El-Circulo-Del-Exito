package cinepoilisklic.com.ia.elcirculodelexito.ui.fragments.listaAlumnos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Alumno;
import cinepoilisklic.com.ia.elcirculodelexito.ui.adapters.AlumnosAdapter;

/**
 * Created by lrangel on 09/02/2018.
 */

public class listaAlumnosFragment  extends Fragment{

    ArrayList<Alumno> persons;
    private EditText etSearchBox;
    public static listaAlumnosFragment newInstace() {
        return new listaAlumnosFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_alumnos, container , false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView audioRv = (RecyclerView) view.findViewById(R.id.audio_rv);
        etSearchBox = (EditText) view.findViewById(R.id.etSearchBox);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        audioRv.setLayoutManager(linearLayoutManager);

        populatePersons();
        final AlumnosAdapter alumnosAdapter = new AlumnosAdapter(persons , (AlumnosAdapter.onItemClickListener) getContext());
        audioRv.setAdapter(alumnosAdapter);

        etSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                alumnosAdapter.getFilter().filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
        BaseHelper helper = new BaseHelper( getContext(), "Demo" , null , 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select Id , Nombre, NombrePadre , telefono from alumnos";
        Cursor c = db.rawQuery( sql , null);
        if(c.moveToFirst()){
            do{
                persons.add( new Alumno( c.getInt(0) , c.getString(1) , c.getString(2) , c.getString(3) ) );
            }while( c.moveToNext());
        }
        db.close();

/*        persons.add(new Alumno("Karla Lopez Herrrera", "no ", 123, 0 , R.drawable.pegatina_circulo_rojo, "15/08/2017"));
        persons.add(new Alumno("Fernando juarez perez","si ", 127 , 6 ,R.drawable.pegatina_circulo_verde, "10/02/2018"));
        persons.add(new Alumno("isis gomez avila","no ", 232, 0, R.drawable.pegatina_circulo_rojo, "02/04/2016"));
        persons.add(new Alumno("jose luis pavia romero","no ",112, 0 ,R.drawable.pegatina_circulo_rojo, "03/03/2016"));
        persons.add(new Alumno("Dorian guzman hernandez","si ",223, 4, R.drawable.pegatina_circulo_verde, "03/06/2018"));
        persons.add(new Alumno("Claudia soto garcia","si ", 345 , 8 ,R.drawable.pegatina_circulo_verde , "05/"));*/
    }


}
