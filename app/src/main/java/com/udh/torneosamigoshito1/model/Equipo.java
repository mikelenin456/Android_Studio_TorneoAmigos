package com.udh.torneosamigoshito1.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "equipos")
public class Equipo {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    private String nombre;
    @NonNull
    private String categoria;
    @NonNull
    private String delegado;

    public Equipo(@NonNull String nombre, @NonNull String categoria, @NonNull String delegado){
        this.nombre = nombre;
        this.categoria = categoria;
        this.delegado = delegado;
    }

    public String getNombre(){
        return nombre;
    }

    public String getCategoria(){
        return categoria;
    }

    public String getDelegado(){
        return delegado;
    }
}
