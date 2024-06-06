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

import java.util.ArrayList;
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
        recetas.add(new Receta("Arepas", "Método arepas", R.drawable.arepas, "30 minutos", "Ingredientes 1"));
        recetas.add(new Receta("Galletas con chocolate", "Método galletas", R.drawable.galletas, "45 minutos", "Ingredientes 2"));
        recetas.add(new Receta("Brownies de chocolate", "Método brownies", R.drawable.brownies, "60 minutos", "Ingredientes 3"));
        recetas.add(new Receta("Masa de pizza napolitana", "Método masa de pizza", R.drawable.masapizza, "10 minutos", "Ingredientes 3"));
        recetas.add(new Receta("Pan", "Método pan", R.drawable.chapata, "60 minutos", "Ingredientes 3"));

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
}