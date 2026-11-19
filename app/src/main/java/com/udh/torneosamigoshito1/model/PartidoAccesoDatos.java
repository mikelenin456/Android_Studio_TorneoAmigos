package com.udh.torneosamigoshito1.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PartidoAccesoDatos {

    @Insert
    long insertar(PartidosProgramados partido);

    @Query("SELECT * FROM partidos ORDER BY id DESC")
    List<PartidosProgramados> listar();
}