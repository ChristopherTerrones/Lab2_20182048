package com.example.lab2_20182048;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.lab2_20182048.bean.JuegoResultado;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeleMemoActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private TextView tvResultado;
    private Button btnNuevoJuego;
    private TextView tvIntentos;

    public static ArrayList<JuegoResultado> historial = new ArrayList<>();

    private boolean juegoTerminado = false;
    private int intentos = 0;
    private long startTime;


    private List<String> oracionCorrecta;
    private List<String> oracionDesordenada;
    private int palabraIndex = 0;
    private String tematica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_memo);

        gridLayout = findViewById(R.id.gridPalabras);
        tvResultado = findViewById(R.id.tvResultado);
        btnNuevoJuego = findViewById(R.id.btnNuevoJuego);
        tvIntentos = findViewById(R.id.tvIntentos);

        ImageButton btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(v -> finish());

        ImageButton btnEstadisticas = findViewById(R.id.btnVolverEstadisticas);
        btnEstadisticas.setOnClickListener(v -> {
            Intent intent = new Intent(this, EstadisticasActivity.class);
            startActivity(intent);
        });

        tematica = getIntent().getStringExtra("tematica");
        String oracion = getIntent().getStringExtra("oracion");
        oracionCorrecta = new ArrayList<>(List.of(oracion.split(" ")));
        oracionDesordenada = getIntent().getStringArrayListExtra("palabrasDesordenadas");

        startTime = SystemClock.elapsedRealtime();

        cargarBotones();

        btnNuevoJuego.setOnClickListener(v -> {
            if (!juegoTerminado) {
                historial.add(new JuegoResultado("Canceló", 0, 0));
            }
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        });
    }
    //se realizó una consulta a IA para generar los recuadros/botones y las palabras tal y como se pedía
    private void cargarBotones() {
        gridLayout.removeAllViews();

        int ancho = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, getResources().getDisplayMetrics());
        int alto = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, getResources().getDisplayMetrics());

        for (String palabra : oracionDesordenada) {
            Button btn = new Button(this);
            btn.setText("");
            btn.setTag(palabra);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = ancho;
            params.height = alto;
            params.setMargins(12, 12, 12, 12);

            btn.setLayoutParams(params);
            btn.setTextSize(14);
            btn.setAllCaps(false);
            btn.setSingleLine(true);
            btn.setMaxLines(1);
            btn.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            btn.setGravity(Gravity.CENTER);
            btn.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            btn.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.transparent));
            btn.setBackgroundResource(R.drawable.button_border);

            btn.setOnClickListener(this::verificarPalabra);
            gridLayout.addView(btn);
        }
    }

    private void verificarPalabra(View view) {
        if (juegoTerminado) {
            Toast.makeText(this, "Presiona 'Nuevo Juego' para volver a intentar", Toast.LENGTH_SHORT).show();
            return;
        }

        Button btn = (Button) view;
        String palabra = btn.getTag().toString();

        if (palabra.equals(oracionCorrecta.get(palabraIndex))) {
            btn.setText(palabra);
            palabraIndex++;

            if (palabraIndex == oracionCorrecta.size()) {
                long tiempo = (SystemClock.elapsedRealtime() - startTime) / 1000;
                tvResultado.setText("Ganó / Terminó en " + tiempo + "s");

                if (intentos > 0) {
                    tvIntentos.setText("Intentos: " + intentos);
                } else {
                    tvIntentos.setText("");
                }

                historial.add(new JuegoResultado("Ganó", (int) tiempo, intentos));
                juegoTerminado = true;
            }
        } else {
            palabraIndex = 0;
            intentos++;

            int intentosRestantes = 3 - intentos;
            if (intentos < 3) {
                tvIntentos.setText("Te quedan " + intentosRestantes + " intentos");
                cargarBotones();
            } else {
                long tiempo = (SystemClock.elapsedRealtime() - startTime) / 1000;
                tvResultado.setText("Perdió / Terminó en " + tiempo + "s");
                tvIntentos.setText("");
                historial.add(new JuegoResultado("Perdió", (int) tiempo, 0));
                juegoTerminado = true;
            }
        }
    }
}
