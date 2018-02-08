package cinepoilisklic.com.ia.elcirculodelexito.ui.reporteAlumno;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Asesoria;

/**
 * Created by Dell on 02/11/2017.
 */

public class ListAdapterReporteAlumno extends RecyclerView.Adapter<ListAdapterReporteAlumno.ViewHolder> implements Filterable{

    private ArrayList<Asesoria> asesorias;
    private ArrayList<Asesoria> asesoriasFilter;
    private CustomFilter mFilter;

    public ListAdapterReporteAlumno(ArrayList<Asesoria> asesorias) {
        this.asesorias = asesorias;
        this.asesoriasFilter = new ArrayList<>();
        this.asesoriasFilter.addAll(asesorias);
        this.mFilter = new CustomFilter(ListAdapterReporteAlumno.this);
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView materiaAsesoria, maestroAsesoria;
        public TextView maestro, fecha, nivel, tiempoAsesoria, tiempoRestante;
        public CardView cardView;
        public RelativeLayout parentBodyRl;

        public ViewHolder(View v) {
            super(v);
            parentBodyRl = (RelativeLayout)v.findViewById(R.id.parent_body_rl);
            cardView = (CardView)v.findViewById(R.id.card_view);
            maestroAsesoria = (ImageView)v.findViewById(R.id.imagen_maestro_reporte_asesoria_alumno);
            materiaAsesoria = (ImageView)v.findViewById(R.id.imagen_materia_reporte_asesoria_alumno);
            maestro = (TextView)v.findViewById(R.id.nombre_maestro);
            fecha = (TextView)v.findViewById(R.id.fecha_asesoria);
            nivel = (TextView)v.findViewById(R.id.nivel_asesoria);
            tiempoAsesoria = (TextView)v.findViewById(R.id.tiempo_duracion);
            tiempoRestante = (TextView)v.findViewById(R.id.tiempo_restante);

        }
    }

    @Override
    public ListAdapterReporteAlumno.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter_reporte_alumno, parent , false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.maestro.setText(asesoriasFilter.get(position).getMaestroNombre());
        holder.fecha.setText("fecha : "+ asesoriasFilter.get(position).getFecha());
        holder.nivel.setText("nivel : "+ asesoriasFilter.get(position).getNivel());
        holder.tiempoAsesoria.setText("duracion : "+asesoriasFilter.get(position).getTiempoDuracion());
        holder.tiempoRestante.setText("tiempo restante : "+ asesoriasFilter.get(position).getTiempoRestante());

        holder.maestroAsesoria.setImageResource(asesoriasFilter.get(position).getMaestroImag());
        holder.materiaAsesoria.setImageResource(asesoriasFilter.get(position).getMateriaImag());
    }

    @Override
    public int getItemCount() {
        return asesoriasFilter.size();
    }

    public class CustomFilter extends Filter {

        private ListAdapterReporteAlumno listAdapterReporteAlumno;

        public CustomFilter( ListAdapterReporteAlumno listAdapterReporteAlumno) {
            super();
            this.listAdapterReporteAlumno = listAdapterReporteAlumno;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            asesoriasFilter.clear();
            final FilterResults results = new FilterResults();
            if(constraint.length() == 0){
                asesoriasFilter.addAll(asesorias);
            }else{
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for(final Asesoria asesoria : asesorias){
                    if(asesoria.getMaestroNombre().toLowerCase().contains(filterPattern)){
                        asesoriasFilter.add(asesoria);
                    }

                }
            }
            results.values = asesoriasFilter;
            results.count = asesoriasFilter.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.listAdapterReporteAlumno.notifyDataSetChanged();
        }
    }
}
