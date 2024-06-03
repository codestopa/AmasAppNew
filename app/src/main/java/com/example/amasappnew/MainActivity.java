package com.example.amasappnew;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
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
        recetas.add(new Receta("Arepas", "Método arepas", R.drawable.image1, "30 minutos", "Ingredientes 1"));
        recetas.add(new Receta("Galletas con chocolate", "Método galletas", R.drawable.image1, "45 minutos", "Ingredientes 2"));
        recetas.add(new Receta("Brownies de chocolate", "Método brownies", R.drawable.image1, "60 minutos", "Ingredientes 3"));
        recetas.add(new Receta("Pan", "Método pan", R.drawable.image1, "60 minutos", "Ingredientes 3"));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Adapter(this,recetas);
        recyclerView.setAdapter(adapter);
    }
}