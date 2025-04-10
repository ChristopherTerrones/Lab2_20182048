package com.example.lab2_20182048;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2_20182048.bean.JuegoResultado;

import java.util.ArrayList;

public class EstadisticasActivity extends AppCompatActivity {

    private ListView listView;
    private Button btnNuevoJuego;
    private ImageButton btnVolver;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        listView = findViewById(R.id.listaHistorial);
        btnNuevoJuego = findViewById(R.id.btnNuevoJuegoHistorial);
        btnVolver = findViewById(R.id.btnVolverEstadisticas);

        ArrayList<String> historialFormateado = new ArrayList<>();
        ArrayList<JuegoResultado> historialOriginal = TeleMemoActivity.historial;

        for (int i = 0; i < historialOriginal.size(); i++) {
            JuegoResultado resultado = historialOriginal.get(i);
            historialFormateado.add("Juego " + (i + 1) + ": " + resultado.toString());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historialFormateado);
        listView.setAdapter(adapter);

        btnNuevoJuego.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        });

        btnVolver.setOnClickListener(v -> finish());
    }
}


