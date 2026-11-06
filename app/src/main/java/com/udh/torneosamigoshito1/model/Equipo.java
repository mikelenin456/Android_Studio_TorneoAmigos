package com.udh.torneosamigoshito1.model;




public class Equipo {
    private String nombre;
    private String categoria;
    private String delegado;

    public Equipo(String nombre, String categoria, String delegado){
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
