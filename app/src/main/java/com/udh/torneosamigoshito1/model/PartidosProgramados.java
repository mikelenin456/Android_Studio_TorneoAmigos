package com.udh.torneosamigoshito1.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "partidos")
public class PartidosProgramados {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int localId;
    public int visitanteId;

    @NonNull
    public String cancha;

    @NonNull
    public String fechaHora;

    public PartidosProgramados(int localId, int visitanteId,
                               @NonNull String cancha, @NonNull String fechaHora) {
        this.localId = localId;
        this.visitanteId = visitanteId;
        this.cancha = cancha;
        this.fechaHora = fechaHora;
    }
}