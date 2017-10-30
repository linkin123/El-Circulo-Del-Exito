package cinepoilisklic.com.ia.elcirculodelexito;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by andres on 04/10/15.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements Filterable {

    private ArrayList<Alumno> persons;
    private ArrayList<Alumno> personsFilter;
    private CustomFilter mFilter;

    Spinner spinnerBuscador;


    // Provee una referencia a cada item dentro de una vista y acceder a ellos facilmente
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Cada uno de los elementos de mi vista
        public TextView nameAlumnoReporte, statusAlumnoReporte, idAlumnoReporte, horasAlumnoReporte;
        public CardView cardView;
        public Button btnStatus;
        public RelativeLayout parentBodyRl;

        public ViewHolder(View v) {
            super(v);
            parentBodyRl = (RelativeLayout) v.findViewById(R.id.parent_body_rl);
            cardView = (CardView) v.findViewById(R.id.card_view);
            nameAlumnoReporte = (TextView)v.findViewById(R.id.name_alumno_reporte);
            statusAlumnoReporte = (TextView) v.findViewById(R.id.status_alumno_reporte);
            idAlumnoReporte = (TextView)v.findViewById(R.id.id_alumno_reporte);
            horasAlumnoReporte = (TextView)v.findViewById(R.id.horas_alumno_reporte);
            btnStatus = (Button) v.findViewById(R.id.btn_status);
        }
    }

    // Constructor
    public ListAdapter(ArrayList<Alumno> persons) {

        this.persons = persons;
        this.personsFilter = new ArrayList<>();
        this.personsFilter.addAll(persons);
        this.mFilter = new CustomFilter(ListAdapter.this);
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // inflo la vista (vista padre)
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter, parent, false);
        // creo el grupo de vistas
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // Reemplaza en contenido de la vista
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.nameAlumnoReporte.setText(personsFilter.get(position).getName());
        viewHolder.statusAlumnoReporte.setText("Paquete : " + personsFilter.get(position).getStatus());
        viewHolder.idAlumnoReporte.setText("id : " + String.valueOf(personsFilter.get(position).getId()));
        viewHolder.horasAlumnoReporte.setText("horas : " + String.valueOf(personsFilter.get(position).getHoras()));

//        personsFilter.get(position).getColor()
        viewHolder.btnStatus.setBackgroundResource(personsFilter.get(position).getColor());
    }

    // Retorna el tamano de nuestra data
    @Override
    public int getItemCount() {
        return personsFilter.size();
    }

    /*Filtro*/
    public class CustomFilter extends Filter {
        private ListAdapter listAdapter;

        private CustomFilter(ListAdapter listAdapter) {
            super();
            this.listAdapter = listAdapter;
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
                    if (person.getName().toLowerCase().contains(filterPattern) || person.getStatus().toLowerCase().contains(filterPattern) ) {
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
            this.listAdapter.notifyDataSetChanged();
        }
    }

}