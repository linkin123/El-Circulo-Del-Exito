package cinepoilisklic.com.ia.elcirculodelexito.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Materia;

/**
 * Created by lrangel on 09/02/2018.
 */

public class MateriasLinealAdapter extends RecyclerView.Adapter<MateriasLinealAdapter.ItemViewHolder> {


    private Context mcontext;
    private List<Materia> list;
    private int nivel;

    public MateriasLinealAdapter(Context mcontext, List<Materia> list , int nivel) {
        this.mcontext = mcontext;
        this.list = list;
        this.nivel = nivel;

    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_materia_lineal, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        final Materia materia = list.get(position);
        holder.nombre.setText("materia :" + materia.getNombre());
        holder.horas.setText(" horas : " + String.valueOf(materia.getHoras()));
        holder.precio.setText(" precio : $"+ nivel*(materia.getHoras()/10));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, horas , precio;

        public ItemViewHolder(View v) {
            super(v);
            nombre = (TextView) itemView.findViewById(R.id.nombre_materia_cardview);
            horas = (TextView) itemView.findViewById(R.id.horas_materia_cardview);
            precio = (TextView) itemView.findViewById(R.id.precio_materia_cardview);

        }
    }
}
