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
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> implements Filterable {

    private ArrayList<Alumno> persons;
    private ArrayList<Alumno> personsFilter;
    private CustomFilter mFilter;
    private onItemClickListener mListener;

    Spinner spinnerBuscador;

    // Provee una referencia a cada item dentro de una vista y acceder a ellos facilmente
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        // Cada uno de los elementos de mi vista
        public TextView nameAlumnoReporte, statusAlumnoReporte, idAlumnoReporte, horasAlumnoReporte, fechaFinPaqeute;
        public CardView cardView;
        public Button btnStatus;
        public RelativeLayout parentBodyRl;

        public ItemViewHolder(View v) {
            super(v);
            parentBodyRl = (RelativeLayout) v.findViewById(R.id.parent_body_rl);
            cardView = (CardView) v.findViewById(R.id.card_view);
            nameAlumnoReporte = (TextView)v.findViewById(R.id.name_alumno_reporte);
            statusAlumnoReporte = (TextView) v.findViewById(R.id.status_alumno_reporte);
            idAlumnoReporte = (TextView)v.findViewById(R.id.id_alumno_reporte);
            horasAlumnoReporte = (TextView)v.findViewById(R.id.horas_alumno_reporte);
            btnStatus = (Button) v.findViewById(R.id.btn_status);
            fechaFinPaqeute = (TextView)v.findViewById(R.id.fecha_fin_paquete);
        }
    }

    public interface onItemClickListener{
        void onItemClick(Alumno alumno);
    }

    // Constructor
    public ListAdapter(ArrayList<Alumno> persons , onItemClickListener listener) {

        this.persons = persons;
        this.personsFilter = new ArrayList<>();
        this.personsFilter.addAll(persons);
        this.mFilter = new CustomFilter(ListAdapter.this);
        mListener = listener;
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // inflo la vista (vista padre)
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter, parent, false);
        // creo el grupo de vistas
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final Alumno alumno = persons.get(position);
        holder.nameAlumnoReporte.setText(personsFilter.get(position).getName());
        holder.statusAlumnoReporte.setText("Paquete : " + personsFilter.get(position).getStatus());
        holder.idAlumnoReporte.setText("id : " + String.valueOf(personsFilter.get(position).getId()));
        holder.horasAlumnoReporte.setText("horas : " + String.valueOf(personsFilter.get(position).getHoras()));
        holder.fechaFinPaqeute.setText("caducidad : " + personsFilter.get(position).getFechaFinPaquete());

//        personsFilter.get(position).getColor()
        holder.btnStatus.setBackgroundResource(personsFilter.get(position).getColor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(alumno);

            }
        });
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