package cinepoilisklic.com.ia.elcirculodelexito.ui.listaAlumnos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Alumno;

/**
 * Created by lrangel on 07/02/18.
 */
public class AlumnosAdapter extends RecyclerView.Adapter<AlumnosAdapter.ItemViewHolder> implements Filterable {

    private ArrayList<Alumno> persons;
    private ArrayList<Alumno> personsFilter;
    private CustomFilter mFilter;
    private onItemClickListener mListener;

    // Constructor
    public AlumnosAdapter(ArrayList<Alumno> persons , onItemClickListener listener) {

        this.persons = persons;
        this.personsFilter = new ArrayList<>();
        this.personsFilter.addAll(persons);
        this.mFilter = new CustomFilter(AlumnosAdapter.this);
        mListener = listener;
    }

    @Override
    public AlumnosAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter, parent, false);
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final Alumno alumno = persons.get(position);
        holder.nameAlumnoReporte.setText(personsFilter.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick( alumno );
            }
        });
    }

    @Override
    public int getItemCount() {
        return personsFilter.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView nameAlumnoReporte;

        public ItemViewHolder(View v) {
            super(v);
            nameAlumnoReporte = (TextView)v.findViewById(R.id.name_alumno_reporte);
        }
    }

    public interface onItemClickListener{
        void onItemClick(Alumno alumno);
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }



    /*Filtro*/
    public class CustomFilter extends Filter {
        private AlumnosAdapter alumnosAdapter;

        private CustomFilter(AlumnosAdapter alumnosAdapter) {
            super();
            this.alumnosAdapter = alumnosAdapter;
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
                for (final Alumno person : persons) {
                    if (person.getName().toLowerCase().contains(filterPattern) || person.getNamePadre().toLowerCase().contains(filterPattern) ) {
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
            this.alumnosAdapter.notifyDataSetChanged();
        }
    }

}