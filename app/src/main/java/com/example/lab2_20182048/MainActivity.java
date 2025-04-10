package com.example.lab2_20182048;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnSoftware, btnCiberseguridad, btnOpticas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSoftware = findViewById(R.id.btnSoftware);
        btnCiberseguridad = findViewById(R.id.btnCiberseguridad);
        btnOpticas = findViewById(R.id.btnOpticas);

        btnSoftware.setOnClickListener(v -> {
            Intent i = new Intent(this, PreJuegoActivity.class);
            i.putExtra("tematica", "Software");
            startActivity(i);
        });

        btnCiberseguridad.setOnClickListener(v -> {
            Intent i = new Intent(this, PreJuegoActivity.class);
            i.putExtra("tematica", "Ciberseguridad");
            startActivity(i);
        });

        btnOpticas.setOnClickListener(v -> {
            Intent i = new Intent(this, PreJuegoActivity.class);
            i.putExtra("tematica", "Ópticas");
            startActivity(i);
        });

    }
}