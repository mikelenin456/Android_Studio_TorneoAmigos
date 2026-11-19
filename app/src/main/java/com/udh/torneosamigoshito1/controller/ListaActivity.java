package com.udh.torneosamigoshito1.controller;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.udh.torneosamigoshito1.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.udh.torneosamigoshito1.model.EquiposEjemplo;
import com.udh.torneosamigoshito1.view.EquipoAdapter;
import android.content.Intent;
import android.widget.Toast;
import com.udh.torneosamigoshito1.model.AppDatabase;
import com.udh.torneosamigoshito1.model.Equipo;
import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista);

        RecyclerView lista = findViewById(R.id.rvEquipos);
        lista.setLayoutManager(new LinearLayoutManager(this));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        RecyclerView lista = findViewById(R.id.rvEquipos);
        AppDatabase.IO.execute(() -> {
            try {
                ArrayList<Equipo> equipos = new ArrayList<>(
                        AppDatabase.getInstance(this).equipoDao().listar()
                );
                runOnUiThread(() -> {
                    lista.setAdapter(new EquipoAdapter(equipos, equipo -> {
                        Intent intento = new Intent(this, DetalleActivity.class);
                        intento.putExtra("equipoId", equipo.id);
                        intento.putExtra("nombre", equipo.getNombre());
                        intento.putExtra("categoria", equipo.getCategoria());
                        intento.putExtra("delegado", equipo.getDelegado());
                        startActivity(intento);
                    }));
                });
            } catch (RuntimeException e) {
                runOnUiThread(() -> Toast.makeText(this,
                        "No se pudieron cargar los equipos", Toast.LENGTH_SHORT).show());
            }
        });
    }
}