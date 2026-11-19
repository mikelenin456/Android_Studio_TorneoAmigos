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

import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.app.AlertDialog;

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
                java.util.List<PartidosProgramados> partidos = db.partidoDao().listar();
                for (PartidosProgramados partido : partidos){
                    String local = nombres.get(partido.localId);
                    String visitante = nombres.get(partido.visitanteId);

                    if (local == null) {
                        local = "Equipo eliminado";
                    }
                    if (visitante == null) {
                        visitante = "Equipo eliminado";
                    }
                    String resultado = "Sin resultado";
                    if (partido.golesLocal != null && partido.golesVisitante != null) {
                        resultado = partido.golesLocal + " - " + partido.golesVisitante;
                    }
                    resumenes.add(local + " vs. " + visitante
                            + "\nCancha: " + partido.cancha
                            + "\nFecha y hora: " + partido.fechaHora
                            + "\nResultado: " + resultado);
                }

                runOnUiThread(() -> {
                    lista.setAdapter(new PartidoAdapter(resumenes, posicion -> {
                        PartidosProgramados partido = partidos.get(posicion);
                        mostrarDialogoResultado(partido);
                    }));
                    if (resumenes.isEmpty()) {
                        Toast.makeText(this, "No hay partidos programados",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (RuntimeException e) {
                android.util.Log.e("ListaPartidos", "Error al cargar los partidos", e);
                runOnUiThread(() -> Toast.makeText(this,
                        "No se pudieron cargar los partidos", Toast.LENGTH_SHORT).show());
            }
        });
    }
    private void mostrarDialogoResultado(PartidosProgramados partido) {
        EditText golesLocal = new EditText(this);
        golesLocal.setHint("Goles del local");
        golesLocal.setInputType(InputType.TYPE_CLASS_NUMBER);

        EditText golesVisitante = new EditText(this);
        golesVisitante.setHint("Goles del visitante");
        golesVisitante.setInputType(InputType.TYPE_CLASS_NUMBER);

        if (partido.golesLocal != null) {
            golesLocal.setText(String.valueOf(partido.golesLocal));
        }
        if (partido.golesVisitante != null) {
            golesVisitante.setText(String.valueOf(partido.golesVisitante));
        }

        LinearLayout campos = new LinearLayout(this);
        campos.setOrientation(LinearLayout.VERTICAL);
        campos.addView(golesLocal);
        campos.addView(golesVisitante);

        AlertDialog dialogo = new AlertDialog.Builder(this)
                .setTitle("Registrar resultado")
                .setView(campos)
                .setNegativeButton("Cerrar", null)
                .setPositiveButton("Guardar", null)
                .show();

        dialogo.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(boton -> {
            int local;
            int visitante;

            try {
                local = Integer.parseInt(golesLocal.getText().toString().trim());
                visitante = Integer.parseInt(golesVisitante.getText().toString().trim());
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Ingresa los goles de ambos equipos",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (local < 0 || visitante < 0) {
                Toast.makeText(this, "Los goles no pueden ser negativos",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            boton.setEnabled(false);

            AppDatabase.IO.execute(() -> {
                try {
                    int actualizados = AppDatabase.getInstance(this).partidoDao()
                            .guardarResultado(partido.id, local, visitante);

                    runOnUiThread(() -> {
                        dialogo.dismiss();
                        String mensaje = actualizados == 1
                                ? "Resultado guardado"
                                : "No se encontro el partido";
                        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                        recreate();
                    });
                } catch (RuntimeException e) {
                    runOnUiThread(() -> {
                        boton.setEnabled(true);
                        Toast.makeText(this, "No se pudo guardar el resultado",
                                Toast.LENGTH_SHORT).show();
                    });
                }
            });
        });

    }
}