package com.udh.torneosamigoshito1.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.udh.torneosamigoshito1.R;
import com.udh.torneosamigoshito1.model.Equipo;

import java.util.ArrayList;


public class EquipoAdapter extends RecyclerView.Adapter<EquipoAdapter.EquipoViewHolder> {

    private final ArrayList<Equipo> equipos;

    public interface AlPulsarEquipo {
        void abrir(Equipo equipo);
    }

    private final AlPulsarEquipo alPulsar;

    public EquipoAdapter(ArrayList<Equipo> equipos, AlPulsarEquipo alPulsar) {
        this.equipos = equipos;
        this.alPulsar = alPulsar;
    }

    @Override
    public EquipoViewHolder onCreateViewHolder(ViewGroup padre, int tipo) {
        View fila = LayoutInflater.from(padre.getContext()).inflate(R.layout.item_equipo, padre, false);
        return new EquipoViewHolder(fila);

    }


    @Override
    public void onBindViewHolder(EquipoViewHolder holder, int posicion) {
        Equipo equipo = equipos.get(posicion);
        holder.nombre.setText(equipo.getNombre());
        holder.categoria.setText(equipo.getCategoria());
        holder.itemView.setOnClickListener(v -> alPulsar.abrir(equipo));
    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    static class EquipoViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView categoria;

        EquipoViewHolder(View fila) {
            super(fila);
            nombre = fila.findViewById(R.id.tvNombreEquipo);
            categoria = fila.findViewById(R.id.tvCategoriaEquipo);
        }
    }
}