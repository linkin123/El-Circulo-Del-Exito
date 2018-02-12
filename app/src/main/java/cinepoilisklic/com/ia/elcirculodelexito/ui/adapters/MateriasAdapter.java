package cinepoilisklic.com.ia.elcirculodelexito.ui.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cinepoilisklic.com.ia.elcirculodelexito.R;
import cinepoilisklic.com.ia.elcirculodelexito.data.models.Materia;
import cinepoilisklic.com.ia.elcirculodelexito.ui.activities.altaPaquete.AltaPaqueteActivity;

/**
 * Created by Dell on 11/11/2017.
 */

public class MateriasAdapter extends RecyclerView.Adapter<MateriasAdapter.ItemViewHolder>{


    private Context mcontext;
    private List<Materia> list;
    private  onItemClickListener mListener;

    public MateriasAdapter(Context mcontext, List<Materia> list, onItemClickListener listener) {
        this.mcontext = mcontext;
        this.list = list;
        mListener = listener;

    }

    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_materia, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {

        final Materia materia = list.get(position);
        holder.nombre.setText("materia :" + materia.getNombre());
        holder.horas.setText("horas " + String.valueOf(materia.getHoras()));
        holder.fecha.setText("caduca :" + materia.getFecha());
        holder.image.setImageResource(materia.getImagen());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(v.getRootView().getContext());
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Elimina este teléfono ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Toast.makeText(mcontext , "elemento eliminado", Toast.LENGTH_LONG);
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                dialogo1.show();
/*                System.out.println("click largo para eliminar");*/
                /*lanzar dialog*/
                return false;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click corto para editar");
                /*lanzar dialog*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ItemViewHolder extends  RecyclerView.ViewHolder{

        ImageView image;
        TextView nombre, horas, fecha;

        public ItemViewHolder(View v) {
            super(v);
            nombre = (TextView) itemView.findViewById(R.id.nombre_materia_cardview);
            horas = (TextView) itemView.findViewById(R.id.horas_materia_cardview);
            fecha = (TextView) itemView.findViewById(R.id.fecha_materia_cardview);
            image = (ImageView) itemView.findViewById(R.id.imagen_materia_cardView);

        }
    }

    public interface onItemClickListener{
        void onItemClick(Materia mateia);
    }
}
