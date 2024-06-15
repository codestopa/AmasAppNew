package com.example.amasappnew;

import java.util.List;

public class Receta {

    private String nombre;
    private String metodo;
    private int imagen;
    private String duracion;
    private List<String> ingredientes;
    private String dificultad;
    private String valorNutricional;

    public Receta(String nombre, String metodo, int imagen, String duracion, List<String> ingredientes, String dificultad, String valorNutricional){
        this.nombre = nombre;
        this.metodo = metodo;
        this.imagen = imagen;
        this.duracion = duracion;
        this.ingredientes = ingredientes;
        this.dificultad = dificultad;
        this.valorNutricional = valorNutricional;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMetodo() {
        return metodo;
    }

    public int getImagen() {
        return imagen;
    }

    public String getDuracion() {
        return duracion;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }
    public String getDificultad(){
        return dificultad;
    }
    public String getValorNutricional(){return valorNutricional;}

}
