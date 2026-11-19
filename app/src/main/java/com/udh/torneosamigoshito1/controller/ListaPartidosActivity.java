package com.udh.torneosamigoshito1.controller;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.udh.torneosamigoshito1.R;
import com.udh.torneosamigoshito1.model.AppDatabase;
import com.udh.torneosamigoshito1.model.Equipo;
import com.udh.torneosamigoshito1.model.PartidosProgramados;
import com.udh.torneosamigoshito1.view.PartidoAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaPartidosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_partidos);

        RecyclerView lista = findViewById(R.id.rvPartidos);
        lista.setLayoutManager(new LinearLayoutManager(this));

        AppDatabase.IO.execute(() -> {
            try {
                AppDatabase db = AppDatabase.getInstance(this);

                HashMap<Integer, String> nombres = new HashMap<>();
                for (Equipo equipo : db.equipoDao().listar()) {
                    nombres.put(equipo.id, equipo.getNombre());
                }

                ArrayList<String> resumenes = new ArrayList<>();
                for (PartidosProgramados partido : db.partidoDao().listar()) {
                    String local = nombres.get(partido.localId);
                    String visitante = nombres.get(partido.visitanteId);

                    if (local == null) {
                        local = "Equipo eliminado";
                    }
                    if (visitante == null) {
                        visitante = "Equipo eliminado";
                    }
                    resumenes.add(local + " vs. " + visitante
                            + "\nCancha: " + partido.cancha
                            + "\nFecha y hora: " + partido.fechaHora);
                }

                runOnUiThread(() -> {
                    lista.setAdapter(new PartidoAdapter(resumenes));
                    if (resumenes.isEmpty()) {
                        Toast.makeText(this, "No hay partidos programados",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (RuntimeException e) {
                runOnUiThread(() -> Toast.makeText(this,
                        "No se pudieron cargar los partidos", Toast.LENGTH_SHORT).show());
            }
        });
    }
}