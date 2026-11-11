package com.udh.torneosamigoshito1.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class EquiposGuardados {

    public static void guardar(Context contexto, Equipo equipo) {
        SharedPreferences datos = contexto.getSharedPreferences("equipos", Context.MODE_PRIVATE);
        int indice = datos.getInt("cantidad", 0);

        datos.edit()
                .putString("nombre_" + indice, equipo.getNombre())
                .putString("categoria_" + indice, equipo.getCategoria())
                .putString("delegado_" + indice, equipo.getDelegado())
                .putInt("cantidad", indice + 1)
                .apply();
    }

    public static ArrayList<Equipo> obtener(Context contexto) {
        SharedPreferences datos = contexto.getSharedPreferences("equipos", Context.MODE_PRIVATE);
        ArrayList<Equipo> equipos = new ArrayList<>();
        int cantidad = datos.getInt("cantidad", 0);

        for (int i = 0; i < cantidad; i++) {
            String nombre = datos.getString("nombre_" + i, "");
            String categoria = datos.getString("categoria_" + i, "");
            String delegado = datos.getString("delegado_" + i, "");
            equipos.add(new Equipo(nombre, categoria, delegado));
        }

        return equipos;
    }
}
