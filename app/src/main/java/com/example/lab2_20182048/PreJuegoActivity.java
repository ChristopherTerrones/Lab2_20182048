package com.example.lab2_20182048;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PreJuegoActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private Button btnJugar;
    private String tematica;
    private String oracionElegida;

    private List<String> palabras;

    private String[] oracionesSoftware = {
            "La fibra óptica envía datos a gran velocidad evitando cualquier interferencia eléctrica",
            "Los amplificadores EDFA mejoran la señal óptica en redes de larga distancia"
    };

    private String[] oracionesCiber = {
            "Una VPN encripta tu conexión para navegar de forma anónima y segura",
            "El ataque DDoS satura servidores con tráfico falso y causa caídas masivas"
    };

    private String[] oracionesOptica = {
            "Los fragments reutilizan partes de pantalla en distintas actividades de la app",
            "Los intents permiten acceder a apps como la cámara o WhatsApp directamente"
    };
    ImageButton btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_juego);

        gridLayout = findViewById(R.id.gridPreview);
        btnJugar = findViewById(R.id.btnJugar);
        btnVolver = findViewById(R.id.btnVolver);

        tematica = getIntent().getStringExtra("tematica");
        oracionElegida = obtenerOracionAleatoria(tematica);

        mostrarPalabras(oracionElegida);

        btnJugar.setOnClickListener(v -> {
            Intent i = new Intent(this, TeleMemoActivity.class);
            i.putExtra("tematica", tematica);
            i.putExtra("oracion", oracionElegida);
            i.putStringArrayListExtra("palabrasDesordenadas", new ArrayList<>(palabras));
            startActivity(i);
        });
        btnVolver.setOnClickListener(v -> {
            finish();
        });
    }

    private String obtenerOracionAleatoria(String tema) {
        String[] lista;
        switch (tema) {
            case "Software":
                lista = oracionesSoftware;
                break;
            case "Ciberseguridad":
                lista = oracionesCiber;
                break;
            case "Ópticas":
                lista = oracionesOptica;
                break;
            default:
                lista = oracionesSoftware;
        }
        return lista[(int) (Math.random() * lista.length)];
    }

    private void mostrarPalabras(String oracion) {
        palabras = new ArrayList<>(List.of(oracion.split(" ")));
        Collections.shuffle(palabras);

        int ancho = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, getResources().getDisplayMetrics());
        int alto = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, getResources().getDisplayMetrics());

        for (String palabra : palabras) {
            Button btn = new Button(this);
            btn.setText(palabra);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = ancho;
            params.height = alto;
            params.setMargins(12, 12, 12, 12);

            btn.setLayoutParams(params);
            btn.setTextSize(14);
            btn.setAllCaps(false);
            btn.setSingleLine(true);
            btn.setGravity(Gravity.CENTER);
            btn.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            btn.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.transparent));
            btn.setBackgroundResource(R.drawable.button_border);

            gridLayout.addView(btn);
        }
    }
}
