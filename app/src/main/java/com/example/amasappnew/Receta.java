package com.example.amasappnew;
/*he cambiado imagen e ingredientes a String, en el adapter hay 2 lineas comentadas*/
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Receta {

    @SerializedName("id")
    private int id;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("metodo")
    private String metodo;

    @SerializedName("imagen")
    private String imagen;

    @SerializedName("duracion")
    private String duracion;

    @SerializedName("ingredientes")
    private String ingredientes;

    @SerializedName("dificultad")
    private String dificultad;

    @SerializedName("valorNutricional")
    private String valorNutricional;

    public Receta(String nombre, String metodo, String imagen, String duracion, String ingredientes, String dificultad, String valorNutricional){
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

    public String getImagen() {
        return imagen;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getIngredientes() {
        return ingredientes;
    }
    public String getDificultad(){
        return dificultad;
    }
    public String getValorNutricional(){return valorNutricional;}

}
