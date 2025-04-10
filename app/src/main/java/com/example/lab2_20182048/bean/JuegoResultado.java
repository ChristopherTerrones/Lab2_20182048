package com.example.lab2_20182048.bean;

import java.io.Serializable;

public class JuegoResultado implements Serializable {
    private String estado;
    private int tiempo;
    private int intentos;

    public JuegoResultado(String estado, int tiempo, int intentos) {
        this.estado = estado;
        this.tiempo = tiempo;
        this.intentos = intentos;
    }

    public String getEstado() { return estado; }
    public int getTiempo() { return tiempo; }
    public int getIntentos() { return intentos; }

    @Override
    public String toString() {
        if ("Ganó".equals(estado)) {
            return estado + " / Terminó en " + tiempo + "s\nIntentos: " + intentos;
        } else if ("Perdió".equals(estado)) {
            return estado + " / Terminó en " + tiempo + "s";
        } else {
            return estado;
        }
    }
}

