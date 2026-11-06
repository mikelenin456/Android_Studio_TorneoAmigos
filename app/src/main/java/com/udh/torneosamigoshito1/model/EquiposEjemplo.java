package com.udh.torneosamigoshito1.model;
import java.util.ArrayList;


public class EquiposEjemplo {
    public static ArrayList<Equipo> obtener(){
        ArrayList<Equipo> equipos = new ArrayList<>();

        equipos.add(new Equipo("Amigos UDH", "Libre", "Luis"));
        equipos.add(new Equipo("Los Andes", " Libre", "Pedro"));
        equipos.add(new Equipo("Azul", "Juvenil", "Ana"));
        equipos.add(new Equipo("Capibara", "Libre", "José"));
        equipos.add(new Equipo("Peluche", "Master", "Maria"));

        return equipos;

    }

}
