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
import com.udh.torneosamigoshito1.model.Equipo;
import com.udh.torneosamigoshito1.model.EquiposGuardados;
import java.util.ArrayList;


public class ListaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista);

        RecyclerView lista = findViewById(R.id.rvEquipos);
        lista.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Equipo> equipos =EquiposEjemplo.obtener();
        equipos.addAll(EquiposGuardados.obtener(this));
        lista.setAdapter(new EquipoAdapter(equipos, equipo ->{
            Intent intento = new Intent(this, DetalleActivity.class);
            intento.putExtra("nombre", equipo.getNombre());
            intento.putExtra("categoria", equipo.getCategoria());
            intento.putExtra("delegado", equipo.getDelegado());
            startActivity(intento);
        }));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}