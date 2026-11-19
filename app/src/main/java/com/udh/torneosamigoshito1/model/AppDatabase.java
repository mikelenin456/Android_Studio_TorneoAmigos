package com.udh.torneosamigoshito1.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {Equipo.class, PartidosProgramados.class, Jugador.class},
        version = 4,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instancia;

    public static final ExecutorService IO = Executors.newSingleThreadExecutor();

    private static final Migration MIGRACION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase db) {
            db.execSQL("CREATE TABLE `partidos` ("
                    + "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + "`localId` INTEGER NOT NULL, "
                    + "`visitanteId` INTEGER NOT NULL, "
                    + "`cancha` TEXT NOT NULL, "
                    + "`fechaHora` TEXT NOT NULL)");
        }
    };
    private static final Migration MIGRACION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase db) {
            db.execSQL("ALTER TABLE `partidos` ADD COLUMN `golesLocal` INTEGER");
            db.execSQL("ALTER TABLE `partidos` ADD COLUMN `golesVisitante` INTEGER");
        }
    };

    private static final Migration MIGRACION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase db) {
            db.execSQL("CREATE TABLE `jugadores` ("
                    + "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                    + "`equipoId` INTEGER NOT NULL, "
                    + "`nombre` TEXT NOT NULL)");
        }
    };

    public abstract EquipoAccesoDatos equipoDao();

    public abstract PartidoAccesoDatos partidoDao();
    public abstract JugadorAccesoDatos jugadorDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instancia == null) {
            instancia = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "torneos.db").addMigrations(MIGRACION_1_2, MIGRACION_2_3, MIGRACION_3_4).build();
        }
        return instancia;
    }
}