package com.example.amasappnew;

public class Receta {

    private String nombre;
    private String metodo;
    private int imagen;
    private String duracion;
    private String ingredientes;

    public Receta(String nombre, String metodo, int imagen, String duracion, String ingredientes){
        this.nombre = nombre;
        this.metodo = metodo;
        this.imagen = imagen;
        this.duracion = duracion;
        this.ingredientes = ingredientes;
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

    public String getIngredientes() {
        return ingredientes;
    }
}
