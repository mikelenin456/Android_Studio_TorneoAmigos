package com.udh.torneosamigoshito1.controller;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.udh.torneosamigoshito1.R;
import com.udh.torneosamigoshito1.model.Equipo;
import com.udh.torneosamigoshito1.model.EquiposEjemplo;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;

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

        for (Equipo equipo : EquiposEjemplo.obtener()) {
            nombres.add(equipo.getNombre());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                nombres
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        local.setAdapter(adapter);
        visita.setAdapter(adapter);
        visita.setSelection(1);

        findViewById(R.id.btnValidarPartido).setOnClickListener(v -> {

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

            if (local.getSelectedItemPosition()
                    == visita.getSelectedItemPosition()) {
                Toast.makeText(
                        this,
                        "Selecciona dos equipos diferentes",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }
            String resumen = "Local: " + local.getSelectedItem()
                    + "\nVisitante: " + visita.getSelectedItem()
                    + "\nCancha: " + cancha.getText().toString().trim()
                    + "\nFecha y hora: " + fecha.getText().toString().trim();

            new AlertDialog.Builder(PartidoActivity.this)
                    .setTitle("Resumen del partido")
                    .setMessage(resumen)
                    .setPositiveButton("Aceptar", null)
                    .show();
        });

        findViewById(R.id.btnVolverPartido).setOnClickListener(v -> {
            finish();
        });
    }
}
