package com.udh.torneosamigoshito1.model;

public class ReglasPartido {

    public static boolean hayEquiposSuficientes(int cantidad) {
        return cantidad >= 2;
    }

    public static boolean sonEquiposDistintos(int localId, int visitanteId) {
        return localId != visitanteId;
    }
}