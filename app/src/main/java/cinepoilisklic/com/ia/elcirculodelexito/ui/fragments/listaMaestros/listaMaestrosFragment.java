package cinepoilisklic.com.ia.elcirculodelexito.ui.fragments.listaMaestros;

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
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Maestro;
import cinepoilisklic.com.ia.elcirculodelexito.ui.adapters.MaestrosAdapter;

/**
 * Created by lrangel on 26/02/2018.
 */

public class listaMaestrosFragment extends Fragment implements  MaestrosAdapter.onItemClickListener{

    ArrayList<Maestro> persons;
    private EditText etSearchBox;
    private LinearLayout layoutContent;
    private LinearLayout layoutMsj;
    private RecyclerView audioRv;

    public static Fragment newInstace() {
        return new listaMaestrosFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_maestros, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        audioRv = (RecyclerView) view.findViewById(R.id.audio_rv_maestros);
        etSearchBox = (EditText) view.findViewById(R.id.etSearchBox_maestros);
        layoutContent = (LinearLayout) view.findViewById(R.id.layout_lista_maestros);
        layoutMsj = (LinearLayout) view.findViewById(R.id.layout_msj);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        audioRv.setLayoutManager(linearLayoutManager);

        populatePersons();
        showUI();
    }

    public void populatePersons() {
        persons = new ArrayList<>();
        BaseHelper helper = new BaseHelper(getContext(), "Demo", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select id , Nombre, domicilio , telefono , horario from Maestros";
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()) {
            do {
                persons.add(new Maestro(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4)));
            } while (c.moveToNext());
        }
        db.close();
    }

    private void showUI() {
        if(persons.isEmpty()){
            showMsj();
        }else {
            hideMsj();
            final MaestrosAdapter maestrosAdapter = new MaestrosAdapter(persons , this);
            audioRv.setAdapter(maestrosAdapter);

            etSearchBox.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    maestrosAdapter.getFilter().filter(s.toString());
                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    public void hideMsj(){
        layoutMsj.setVisibility(View.GONE);
        layoutContent.setVisibility(View.VISIBLE);
    }
    public void showMsj(){
        layoutContent.setVisibility(View.GONE);
        layoutMsj.setVisibility(View.VISIBLE);
    }

    @Override
    public void onItemClick(Maestro maestro) {

    }
}
