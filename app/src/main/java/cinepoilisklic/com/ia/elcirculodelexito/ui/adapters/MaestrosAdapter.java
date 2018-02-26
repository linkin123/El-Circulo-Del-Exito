package cinepoilisklic.com.ia.elcirculodelexito.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.data.database.BaseHelper;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Alumno;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Maestro;

/**
 * Created by lrangel on 26/02/2018.
 */

public class MaestrosAdapter extends RecyclerView.Adapter<MaestrosAdapter.ItemViewHolder> implements Filterable{

    private ArrayList<Maestro> persons;
    private ArrayList<Maestro> personsFilter;
    private CustomFilter mFilter;
    private onItemClickListener mListener;

    public MaestrosAdapter(ArrayList<Maestro> persons , onItemClickListener listener) {
        this.persons = persons;
        this.personsFilter = new ArrayList<>();
        this.personsFilter.addAll(persons);
        this.mFilter = new CustomFilter(MaestrosAdapter.this);
        this.mListener = listener;
    }

    @Override
    public MaestrosAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter_maestros, parent, false);
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final Maestro maestro = persons.get(position);
        holder.nameMaestro.setText(personsFilter.get(position).getNombre());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick( maestro );
            }
        });

    }

    @Override
    public int getItemCount() {
        return personsFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }



    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView nameMaestro;

        public ItemViewHolder(View v) {
            super(v);
            nameMaestro = (TextView)v.findViewById(R.id.tv_name_maestro);
        }
    }

    public interface onItemClickListener{
        void onItemClick(Maestro maestro);
    }

    /*Filtro*/
    public class CustomFilter extends Filter {
        private MaestrosAdapter maestrosAdapter;

        private CustomFilter(MaestrosAdapter maestrosAdapter) {
            super();
            this.maestrosAdapter = maestrosAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            personsFilter.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                personsFilter.addAll(persons);
            } else {

                /**
                 * Para comparar toda la cadena
                 *  final String filterPattern = constraint.toString();
                 for (final Person person : persons) {
                 if (person.getName().equals(filterPattern) || person.getStatus().equals(filterPattern) ||
                 person.getId()==Integer.parseInt(filterPattern) ) {
                 personsFilter.add(person);
                 }
                 }
                 * para comparar letra por letra:
                 */
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final Maestro person : persons) {
                    if (person.getNombre().toLowerCase().contains(filterPattern) ) {
                        personsFilter.add(person);
                    }
                }
            }
            results.values = personsFilter;
            results.count = personsFilter.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.maestrosAdapter.notifyDataSetChanged();
        }
    }



}
