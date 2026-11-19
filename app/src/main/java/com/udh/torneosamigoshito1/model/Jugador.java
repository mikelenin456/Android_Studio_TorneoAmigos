package com.udh.torneosamigoshito1.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "jugadores")
public class Jugador {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int equipoId;

    @NonNull
    public String nombre;

    public Jugador(int equipoId, @NonNull String nombre) {
        this.equipoId = equipoId;
        this.nombre = nombre;
    }
}