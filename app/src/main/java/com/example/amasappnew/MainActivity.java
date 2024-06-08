package com.example.amasappnew;

import android.content.ClipData;
import android.media.RouteListingPreference;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter adapter;
    private SearchView searchview;
    private List<Receta> recetas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recetas = new ArrayList<>();
        recetas.add(new Receta("Arepas", leerArchivo("arepas.txt"), R.drawable.arepas, "20 minutos", Arrays.asList("Harina de Maíz: 400g","Agua: 375mL","Leche: 250mL","Sal: 1 cucharada","Matequilla: 1 cucharada"),"fácil"));
        recetas.add(new Receta("Galletas con chocolate", leerArchivo("galletas.txt"), R.drawable.galletas, "25 minutos", Arrays.asList("Mantequilla: 180g","Huevos: 2","Levadura: 7g","Azúcar: 225g","Harina: 220g","Esencia de vainilla: 1 cucharada","Trozos de chocolate: 200g"),"fácil"));
        recetas.add(new Receta("Brownies de chocolate", leerArchivo("brownies.txt"), R.drawable.brownies, "50 minutos", Arrays.asList("Chocolate negro: 200g","Mantequilla: 110g","Huevos: 4","Azúcar: 120g","Esencia de vainilla: 1 cucharada","Bicarbonato","Nueces","Pepitas de chocolate"),"fácil"));
        recetas.add(new Receta("Masa de pizza napolitana", leerArchivo("masapizza.txt"), R.drawable.masapizza, "10 minutos", Arrays.asList("Harina: 273g","Agua: 187mL","Levadura fresca: 0,5g","Sal: 8,2g"),"medio"));
        recetas.add(new Receta("Pan de chapata", leerArchivo("chapata.txt"), R.drawable.chapata, "3h 20 min", Arrays.asList("Harina: 450g", "Agua: 450g", "Levadura fresca: 7g","Harina común: 300g","Agua: 120g","Sal: 14g"),"difícil"));

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