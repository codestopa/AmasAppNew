package com.example.amasappnew;

import android.content.ClipData;
import android.media.RouteListingPreference;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter adapter;
    private SearchView searchview;
    private List<Receta> recetas;

    private static final String BASE_URL = "http://192.168.1.96:3000/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setupUI();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecetaAPI recetaAPI = retrofit.create(RecetaAPI.class);

        fetchRecetas(recetaAPI);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        /*
        recetas = new ArrayList<>();
        recetas.add(new Receta("Arepas", leerArchivo("arepas.txt"), R.drawable.arepas, "20 minutos", Arrays.asList("Harina de Maíz: 400g","Agua: 375mL","Leche: 250mL","Sal: 1 cucharada","Matequilla: 1 cucharada"),"fácil","valorarepas.txt"));
        recetas.add(new Receta("Galletas con chocolate", leerArchivo("galletas.txt"), R.drawable.galletas, "25 minutos", Arrays.asList("Mantequilla: 180g","Huevos: 2","Levadura: 7g","Azúcar: 225g","Harina: 220g","Esencia de vainilla: 1 cucharada","Trozos de chocolate: 200g"),"fácil","valorgalletas.txt"));
        recetas.add(new Receta("Brownies", leerArchivo("brownies.txt"), R.drawable.brownies, "50 minutos", Arrays.asList("Chocolate negro: 200g","Mantequilla: 110g","Huevos: 4","Azúcar: 120g","Esencia de vainilla: 1 cucharada","Bicarbonato","Nueces","Pepitas de chocolate"),"fácil","valorbrownies.txt"));
        recetas.add(new Receta("Masa de pizza", leerArchivo("masapizza.txt"), R.drawable.masapizza, "10 minutos", Arrays.asList("Harina: 273g","Agua: 187mL","Levadura fresca: 0,5g","Sal: 8,2g"),"medio","valorpizza.txt"));
        recetas.add(new Receta("Pan de chapata", leerArchivo("chapata.txt"), R.drawable.chapata, "3h 20 min", Arrays.asList("Harina: 450g", "Agua: 450g", "Levadura fresca: 7g","Harina común: 300g","Agua: 120g","Sal: 14g"),"difícil","valorchapata.txt"));
        recetas.add(new Receta("Bizcocho de yogurt", leerArchivo("bizcocho.txt"), R.drawable.bizcocho, "45 min", Arrays.asList("aceite de oliva: 125g","yogur: 1","Azúcar: 250g","Harina de trigo: 375g","Huevos: 3","Levadura química: 16g","sal: 1 pizca"),"fácil","valorbizcocho.txt"));
        recetas.add(new Receta("Empanada argentina", leerArchivo("empanada.txt"), R.drawable.empanada, "30 min", Arrays.asList("Harina de fuerza: 500g","sal: 2 pellizcos","mantequilla: 100g","Huevo: 1","Aceite de oliva: 2 cucharadas","Agua tibia: 150mL"),"fácil","valorempanadas.txt"));
        recetas.add(new Receta("Masa de tacos", leerArchivo("tacos.txt"), R.drawable.tacos, "30 min", Arrays.asList("Harina 00: 350g","Agua tibia: 200mL","Sal"),"fácil","valorempanadas.txt"));
        recetas.add(new Receta("Masa de wonton", leerArchivo("wonton.txt"), R.drawable.wonton, "20 min", Arrays.asList("Harina de trigo: 150g","Agua: 35mL","Huevo: 1","Sal: 1/2 cucharada","Maizena"),"fácil","valorwonton.txt"));
        recetas.add(new Receta("Pastel de arándanos", leerArchivo("arandanos.txt"), R.drawable.arandanos, "20 min", Arrays.asList("Harina de trigo: 250g","Sal: 5g","Mantequilla: 125g","Huevos: 5","Agua fría: 2 cucharadas","Arándanos: 500g","Azúcar: 60g","Nata: 150g","Maicena: 1 cucharada","Vainilla: 1 vaina"),"medio","valorarandanos.txt"));
        */

        searchview = findViewById(R.id.searchview);
        searchview.clearFocus();
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Adapter(this,recetas);
        recyclerView.setAdapter(adapter);

        ImageButton btnFavoritos = findViewById(R.id.btnFavorite);

    }

    private void setupUI() {
        recetas = new ArrayList<>();


        searchview = findViewById(R.id.searchview);
        searchview.clearFocus();
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, recetas);
        recyclerView.setAdapter(adapter);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void fetchRecetas(RecetaAPI recetaAPI) {
        Call<List<Receta>> call = recetaAPI.getRecetas();
        call.enqueue(new Callback<List<Receta>>() {
            @Override
            public void onResponse(Call<List<Receta>> call, Response<List<Receta>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    recetas.clear();
                    recetas.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    Log.d("API_SUCCESS", "Recetas cargadas correctamente: " + recetas.size());
                } else {
                    Log.e("API_ERROR", "Error en la respuesta: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Receta>> call, Throwable t) {
                Log.e("API_ERROR", "Fallo en la llamada: " + t.getMessage());
            }
        });
    }


    private void filterList(String text) {
        List<Receta> filteredList = new ArrayList<>();
        for(Receta receta : recetas){
            if (receta.getNombre().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(receta);
            }
        }

        if (filteredList.isEmpty()){
            Toast.makeText(this, "No se han encontrado resultados :(", Toast.LENGTH_SHORT).show();
        }else{
            adapter.setFilteredList(filteredList);
        }
    }

    private String leerArchivo(String filename) {
        StringBuilder content = new StringBuilder();
        try (InputStream is = getAssets().open(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}