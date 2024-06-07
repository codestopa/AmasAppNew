package com.example.amasappnew;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RecetaActivity extends AppCompatActivity {

    private TextView textViewMetodo;
    private TextView textViewTitulo;
    private ImageView imageViewReceta;

    private LinearLayout ingredientsContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recetadetalleactivity);

        textViewMetodo = findViewById(R.id.textViewMetodo);
        textViewTitulo = findViewById(R.id.textViewTitulo);
        ingredientsContainer = findViewById(R.id.ingredientsContainer);
        imageViewReceta = findViewById(R.id.imageViewReceta);

        Intent intent = getIntent();
        if (intent != null) {
            String metodo = intent.getStringExtra("metodo");
            String titulo = intent.getStringExtra("nombre");
            int imageResId = intent.getIntExtra("imagen", R.drawable.ic_launcher_background);
            ArrayList<String> ingredientes = intent.getStringArrayListExtra("ingredientes");

            textViewMetodo.setText(metodo);
            textViewTitulo.setText(titulo);
            imageViewReceta.setImageResource(imageResId);

            if (ingredientes != null) {
                for (String ingrediente : ingredientes) {
                    TextView textView = new TextView(this);
                    textView.setText(ingrediente);
                    textView.setTextSize(18);
                    ingredientsContainer.addView(textView);
                }
            }

        }
    }
}
