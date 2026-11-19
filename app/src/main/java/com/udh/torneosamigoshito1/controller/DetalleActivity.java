package com.udh.torneosamigoshito1.controller;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.udh.torneosamigoshito1.R;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.udh.torneosamigoshito1.model.AppDatabase;
import com.udh.torneosamigoshito1.model.Equipo;

import android.view.View;
import androidx.appcompat.app.AlertDialog;
import android.widget.EditText;
import com.udh.torneosamigoshito1.model.Jugador;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalle);

        TextView nombre = findViewById(R.id.tvNombreDetalle);
        TextView categoria = findViewById(R.id.tvCategoriaDetalle);
        TextView delegado = findViewById(R.id.tvDelegadoDetalle);

        nombre.setText(getIntent().getStringExtra("nombre"));
        categoria.setText(getIntent().getStringExtra("categoria"));
        delegado.setText(getIntent().getStringExtra("delegado"));

        findViewById(R.id.btnEditarEquipo).setOnClickListener(v -> {
            Intent intento = new Intent(this, EquipoActivity.class);
            intento.putExtra("equipoId", getIntent().getIntExtra("equipoId", -1));
            intento.putExtra("nombre", getIntent().getStringExtra("nombre"));
            intento.putExtra("categoria", getIntent().getStringExtra("categoria"));
            intento.putExtra("delegado", getIntent().getStringExtra("delegado"));
            startActivity(intento);
        });

        findViewById(R.id.btnEliminarEquipo).setOnClickListener(v ->
                new AlertDialog.Builder(this)
                        .setTitle("Eliminar equipo")
                        .setMessage("¿Eliminar a " + nombre.getText() + "?")
                        .setNegativeButton("Cancelar", null)
                        .setPositiveButton("Eliminar", (dialogo, opcion) -> eliminarEquipo(v))
                        .show()
        );

        findViewById(R.id.btnAgregarJugador).setOnClickListener(v -> mostrarRegistroJugador());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.btnVerJugadores).setOnClickListener(v -> mostrarJugadores());


    }
    @Override
    protected void onResume() {
        super.onResume();

        int equipoId = getIntent().getIntExtra("equipoId", -1);
        if (equipoId <= 0) {
            return;
        }

        TextView nombre = findViewById(R.id.tvNombreDetalle);
        TextView categoria = findViewById(R.id.tvCategoriaDetalle);
        TextView delegado = findViewById(R.id.tvDelegadoDetalle);

        AppDatabase.IO.execute(() -> {
            try {
                Equipo equipo = AppDatabase.getInstance(this).equipoDao().buscarPorId(equipoId);

                runOnUiThread(() -> {
                    if (equipo == null) {
                        Toast.makeText(this, "No se encontró el equipo", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }

                    nombre.setText(equipo.getNombre());
                    categoria.setText(equipo.getCategoria());
                    delegado.setText(equipo.getDelegado());

                    getIntent().putExtra("nombre", equipo.getNombre());
                    getIntent().putExtra("categoria", equipo.getCategoria());
                    getIntent().putExtra("delegado", equipo.getDelegado());
                });
            } catch (RuntimeException e) {
                runOnUiThread(() -> Toast.makeText(this,
                        "No se pudo cargar el equipo", Toast.LENGTH_SHORT).show());
            }
        });
    }
    private void eliminarEquipo(View boton) {
        int equipoId = getIntent().getIntExtra("equipoId", -1);
        boton.setEnabled(false);

        AppDatabase.IO.execute(() -> {
            try {
                var dao = AppDatabase.getInstance(this).equipoDao();
                Equipo equipo = dao.buscarPorId(equipoId);
                int eliminados = equipo == null ? 0 : dao.eliminar(equipo);

                runOnUiThread(() -> {
                    String mensaje = eliminados > 0
                            ? "Equipo eliminado"
                            : "No se encontró el equipo";
                    Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                    finish();
                });
            } catch (RuntimeException e) {
                runOnUiThread(() -> {
                    boton.setEnabled(true);
                    Toast.makeText(this, "No se pudo eliminar el equipo",
                            Toast.LENGTH_SHORT).show();
                });
            }
        });
    }
    private void mostrarRegistroJugador() {
        int equipoId = getIntent().getIntExtra("equipoId", -1);
        EditText campoNombre = new EditText(this);
        campoNombre.setHint("Nombre del jugador");
        campoNombre.setSingleLine(true);

        AlertDialog dialogo = new AlertDialog.Builder(this)
                .setTitle("Agregar jugador")
                .setView(campoNombre)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Guardar", null)
                .show();

        dialogo.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(boton -> {
            String nombreJugador = campoNombre.getText().toString().trim();
            if (nombreJugador.isEmpty()) {
                campoNombre.setError("Completa el nombre del jugador");
                return;
            }

            boton.setEnabled(false);
            AppDatabase.IO.execute(() -> {
                try {
                    AppDatabase db = AppDatabase.getInstance(this);
                    if (db.equipoDao().buscarPorId(equipoId) == null) {
                        runOnUiThread(() -> {
                            dialogo.dismiss();
                            Toast.makeText(this, "No se encontro el equipo",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        });
                        return;
                    }

                    db.jugadorDao().insertar(new Jugador(equipoId, nombreJugador));
                    runOnUiThread(() -> {
                        dialogo.dismiss();
                        Toast.makeText(this, "Jugador guardado", Toast.LENGTH_SHORT).show();
                    });
                } catch (RuntimeException e) {
                    runOnUiThread(() -> {
                        boton.setEnabled(true);
                        Toast.makeText(this, "No se pudo guardar el jugador",
                                Toast.LENGTH_SHORT).show();
                    });
                }
            });
        });
    }
    private void mostrarJugadores() {
        int equipoId = getIntent().getIntExtra("equipoId", -1);

        AppDatabase.IO.execute(() -> {
            try {
                java.util.List<Jugador> jugadores = AppDatabase.getInstance(this)
                        .jugadorDao().listarPorEquipo(equipoId);

                String[] nombres = new String[jugadores.size()];
                for (int i = 0; i < jugadores.size(); i++) {
                    nombres[i] = jugadores.get(i).nombre;
                }

                runOnUiThread(() -> {
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(this)
                            .setTitle("Jugadores del equipo")
                            .setPositiveButton("Cerrar", null);

                    if (nombres.length == 0) {
                        dialogo.setMessage("Este equipo aun no tiene jugadores");
                    } else {
                        dialogo.setItems(nombres, null);
                    }

                    dialogo.show();
                });
            } catch (RuntimeException e) {
                runOnUiThread(() -> Toast.makeText(this,
                        "No se pudieron cargar los jugadores", Toast.LENGTH_SHORT).show());
            }
        });
    }
}