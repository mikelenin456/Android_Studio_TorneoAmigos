package com.udh.torneosamigoshito1.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Equipo.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instancia;

    public static final ExecutorService IO = Executors.newSingleThreadExecutor();

    public abstract EquipoAccesoDatos equipoDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instancia == null) {
            instancia = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"torneos.db").build();
        }
        return instancia;
    }
}