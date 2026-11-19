package com.udh.torneosamigoshito1.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JugadorAccesoDatos {

    @Insert
    long insertar(Jugador jugador);

    @Query("SELECT * FROM jugadores WHERE equipoId = :equipoId ORDER BY nombre")
    List<Jugador> listarPorEquipo(int equipoId);
}