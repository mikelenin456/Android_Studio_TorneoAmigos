package com.udh.torneosamigoshito1;
import android.content.Intent;
import com.udh.torneosamigoshito1.controller.EquipoActivity;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.udh.torneosamigoshito1.controller.PartidoActivity;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnEquipo).setOnClickListener(v -> {
            Intent intento = new Intent(MainActivity.this, EquipoActivity.class);
            startActivity(intento);
        });

        findViewById(R.id.btnPartido).setOnClickListener(v -> {
            Intent intento = new Intent(MainActivity.this, PartidoActivity.class);
            startActivity(intento);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}