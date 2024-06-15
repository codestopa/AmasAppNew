package com.example.amasappnew;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecetaAPI {
    @GET("recetas")
    Call<List<Receta>> getRecetas();
}
