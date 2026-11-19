package com.udh.torneosamigoshito1.controller;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.udh.torneosamigoshito1.R;
import com.udh.torneosamigoshito1.model.Equipo;
import com.udh.torneosamigoshito1.model.AppDatabase;

import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;
import com.udh.torneosamigoshito1.model.PartidosProgramados;
import com.udh.torneosamigoshito1.model.ReglasPartido;

public class PartidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partido);

        Spinner local = findViewById(R.id.spLocal);
        Spinner visita = findViewById(R.id.spVisita);
        EditText cancha = findViewById(R.id.etCancha);
        EditText fecha = findViewById(R.id.etFecha);

        ArrayList<String> nombres = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                nombres
        );

        ArrayList<Equipo> equiposDisponibles = new ArrayList<>();

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        local.setAdapter(adapter);
        visita.setAdapter(adapter);
        AppDatabase.IO.execute(() -> {
            try {
                java.util.List<Equipo> equipos =
                        AppDatabase.getInstance(this).equipoDao().listar();

                runOnUiThread(() -> {
                    equiposDisponibles.addAll(equipos);
                    for (Equipo equipo : equipos) {
                        adapter.add(equipo.getNombre());
                    }

                    if (adapter.getCount() >= 2) {
                        visita.setSelection(1);
                    }
                });
            } catch (RuntimeException e) {
                runOnUiThread(() -> Toast.makeText(this,
                        "No se pudieron cargar los equipos", Toast.LENGTH_SHORT).show());
            }
        });

        findViewById(R.id.btnValidarPartido).setOnClickListener(v -> {
            if (!ReglasPartido.hayEquiposSuficientes(equiposDisponibles.size())) {
                Toast.makeText(this, "Registra al menos dos equipos",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if (cancha.getText().toString().trim().isEmpty()) {
                cancha.setError("Completa la cancha");
                cancha.requestFocus();
                return;
            }

            if (fecha.getText().toString().trim().isEmpty()) {
                fecha.setError("Completa la fecha y hora");
                fecha.requestFocus();
                return;
            }
            Equipo equipoLocal = equiposDisponibles.get(local.getSelectedItemPosition());
            Equipo equipoVisitante = equiposDisponibles.get(visita.getSelectedItemPosition());

            if (!ReglasPartido.sonEquiposDistintos(equipoLocal.id, equipoVisitante.id)) {

                Toast.makeText(this, "Selecciona dos equipos diferentes", Toast.LENGTH_SHORT).show();
                return;
            }

            String resumen = "Local: " + local.getSelectedItem()
                    + "\nVisitante: " + visita.getSelectedItem()
                    + "\nCancha: " + cancha.getText().toString().trim()
                    + "\nFecha y hora: " + fecha.getText().toString().trim();

            new AlertDialog.Builder(PartidoActivity.this)
                    .setTitle("Resumen del partido")
                    .setMessage(resumen)
                    .setPositiveButton("Guardar partido", (dialogo, opcion) -> {

                        PartidosProgramados partido = new PartidosProgramados(
                                equipoLocal.id,
                                equipoVisitante.id,
                                cancha.getText().toString().trim(),
                                fecha.getText().toString().trim()
                        );

                        v.setEnabled(false);
                        AppDatabase.IO.execute(() -> {
                            try {
                                AppDatabase.getInstance(this).partidoDao().insertar(partido);
                                runOnUiThread(() -> {
                                    Toast.makeText(this, "Partido guardado", Toast.LENGTH_SHORT).show();
                                    finish();
                                });
                            } catch (RuntimeException e) {
                                runOnUiThread(() -> {
                                    v.setEnabled(true);
                                    Toast.makeText(this, "No se pudo guardar el partido",
                                            Toast.LENGTH_SHORT).show();
                                });
                            }
                        });
                    })
                    .show();
        });

        findViewById(R.id.btnVolverPartido).setOnClickListener(v -> {
            finish();
        });
    }
}
