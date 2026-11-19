package com.udh.torneosamigoshito1.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
import androidx.room.Update;
import androidx.room.Delete;

@Dao
public interface EquipoAccesoDatos {

    @Insert
    long insertar(Equipo equipo);

    @Query("SELECT * FROM equipos ORDER BY nombre")
    List<Equipo> listar();

    @Update
    void actualizar(Equipo equipo);
    @Query("SELECT * FROM equipos WHERE id = :equipoId")
    Equipo buscarPorId(int equipoId);
    @Delete
    int eliminar(Equipo equipo);
}
