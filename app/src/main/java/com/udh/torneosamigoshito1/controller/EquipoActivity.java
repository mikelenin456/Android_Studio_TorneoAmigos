package com.udh.torneosamigoshito1.controller;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.udh.torneosamigoshito1.R;
import com.udh.torneosamigoshito1.model.AppDatabase;
import com.udh.torneosamigoshito1.model.Equipo;

public class EquipoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo);

        EditText nombre = findViewById(R.id.etNombre);
        EditText delegado = findViewById(R.id.etDelegado);
        Spinner categoria = findViewById(R.id.spCategoria);
        CheckBox confirmar = findViewById(R.id.cbConfirmar);

        String[] categorias = {"Libre", "Juvenil", "Máster"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categorias
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        categoria.setAdapter(adapter);

        int equipoId = getIntent().getIntExtra("equipoId", -1);

        if (equipoId > 0) {
            nombre.setText(getIntent().getStringExtra("nombre"));
            delegado.setText(getIntent().getStringExtra("delegado"));

            int posicion = adapter.getPosition(getIntent().getStringExtra("categoria"));
            if (posicion >= 0) {
                categoria.setSelection(posicion);
            }
        }

        findViewById(R.id.btnValidarEquipo).setOnClickListener(v -> {

            if (nombre.getText().toString().trim().isEmpty()) {
                nombre.setError("Completa el nombre del equipo");
                nombre.requestFocus();
                return;
            }

            if (delegado.getText().toString().trim().isEmpty()) {
                delegado.setError("Completa el nombre del delegado");
                delegado.requestFocus();
                return;
            }

            if (!confirmar.isChecked()) {
                Toast.makeText(
                        this,
                        "Debes confirmar los datos",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }
            Equipo equipo = new Equipo(
                    nombre.getText().toString().trim(),
                    categoria.getSelectedItem().toString(),
                    delegado.getText().toString().trim()
            );

            v.setEnabled(false);

            AppDatabase.IO.execute(() -> {
                try {
                    AppDatabase.getInstance(this).equipoDao();
                    if (equipoId > 0) {
                        equipo.id = equipoId;
                        AppDatabase.getInstance(this).equipoDao().actualizar(equipo);
                    } else {
                        AppDatabase.getInstance(this).equipoDao().insertar(equipo);
                    }

                    runOnUiThread(() -> {
                        Toast.makeText(this, "Equipo guardado", Toast.LENGTH_SHORT).show();
                        finish();
                    });
                } catch (RuntimeException e) {
                    runOnUiThread(() -> {
                        v.setEnabled(true);
                        Toast.makeText(this, "No se pudo guardar el equipo",
                                Toast.LENGTH_SHORT).show();
                    });
                }
            });

        });

        findViewById(R.id.btnVolverEquipo).setOnClickListener(v -> {
            finish();
        });
    }
}
