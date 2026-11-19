package com.udh.torneosamigoshito1.controller;
import android.content.Intent;

import com.udh.torneosamigoshito1.R;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbarInicio));

        findViewById(R.id.btnEquipo).setOnClickListener(v -> {
            Intent intento = new Intent(MainActivity.this, EquipoActivity.class);
            startActivity(intento);
        });

        findViewById(R.id.btnPartido).setOnClickListener(v -> {
            Intent intento = new Intent(MainActivity.this, PartidoActivity.class);
            startActivity(intento);
        });

        findViewById(R.id.btnLista).setOnClickListener(v -> {
            Intent intento = new Intent(MainActivity.this, ListaActivity.class);
            startActivity(intento);
        });

        findViewById(R.id.btnListaPartidos).setOnClickListener(v -> {
            Intent intento = new Intent(MainActivity.this, ListaPartidosActivity.class);
            startActivity(intento);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuEquipo) {
            startActivity(new Intent(this, EquipoActivity.class));
            return true;
        }

        if (item.getItemId() == R.id.menuLista) {
            startActivity(new Intent(this, ListaActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}