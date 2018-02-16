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
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private LinearLayout layoutContent;
    private LinearLayout layoutMsj;
    private RecyclerView audioRv;

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
        audioRv = (RecyclerView) view.findViewById(R.id.audio_rv);
        etSearchBox = (EditText) view.findViewById(R.id.etSearchBox);
        layoutContent = (LinearLayout) view.findViewById(R.id.layout_lista_alumnos);
        layoutMsj = (LinearLayout) view.findViewById(R.id.layout_msj);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        audioRv.setLayoutManager(linearLayoutManager);

        populatePersons();
        showUI();
    }

    private void showUI() {
        if(persons.isEmpty()){
            showMsj();
        }else {
            hideMsj();
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
        String sql = "select id , Nombre, NombrePadre , telefonoPadre from alumnos";
        Cursor c = db.rawQuery( sql , null);
        if(c.moveToFirst()){
            do{
                persons.add( new Alumno( c.getInt(0) , c.getString(1) , c.getString(2) , c.getString(3) ) );
            }while( c.moveToNext());
        }
        db.close();
    }

    public void hideMsj(){
        layoutMsj.setVisibility(View.GONE);
        layoutContent.setVisibility(View.VISIBLE);
    }
    public void showMsj(){
        layoutContent.setVisibility(View.GONE);
        layoutMsj.setVisibility(View.VISIBLE);
    }
}
