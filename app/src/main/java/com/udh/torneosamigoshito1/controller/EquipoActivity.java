package com.udh.torneosamigoshito1.controller;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.udh.torneosamigoshito1.R;

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

            Toast.makeText(
                    this,
                    "Formulario validado correctamente",
                    Toast.LENGTH_LONG
            ).show();
        });

        findViewById(R.id.btnVolverEquipo).setOnClickListener(v -> {
            finish();
        });
    }
}
