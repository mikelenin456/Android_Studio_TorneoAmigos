package com.udh.torneosamigoshito1.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PartidoAdapter extends RecyclerView.Adapter<PartidoAdapter.PartidoViewHolder> {

    private final List<String> resumenes;

    public interface AlTocarPartido {
        void tocar(int posicion);
    }

    private final AlTocarPartido alTocarPartido;

    public PartidoAdapter(List<String> resumenes, AlTocarPartido alTocarPartido) {
        this.resumenes = resumenes;
        this.alTocarPartido = alTocarPartido;
    }

    @NonNull
    @Override
    public PartidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View fila = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new PartidoViewHolder(fila);
    }

    @Override
    public void onBindViewHolder(@NonNull PartidoViewHolder holder, int position) {
        holder.texto.setText(resumenes.get(position));
        holder.itemView.setOnClickListener(v -> alTocarPartido.tocar(position));
    }

    @Override
    public int getItemCount() {
        return resumenes.size();
    }

    static class PartidoViewHolder extends RecyclerView.ViewHolder {
        final TextView texto;

        PartidoViewHolder(@NonNull View fila) {
            super(fila);
            texto = fila.findViewById(android.R.id.text1);
        }
    }
}