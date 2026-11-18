package com.udh.torneosamigoshito1.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EquipoAccesoDatos {

    @Insert
    long insertar(Equipo equipo);

    @Query("SELECT * FROM equipos ORDER BY nombre")
    List<Equipo> listar();
}
