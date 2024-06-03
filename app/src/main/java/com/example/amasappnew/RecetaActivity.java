package com.example.amasappnew;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecetaActivity extends AppCompatActivity {

    private TextView textViewMetodo;
    private TextView textViewTitulo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recetadetalleactivity);

        textViewMetodo = findViewById(R.id.textViewMetodo);
        textViewTitulo = findViewById(R.id.textViewTitulo);

        Intent intent = getIntent();
        if (intent != null) {
            String metodo = intent.getStringExtra("metodo");
            String titulo = intent.getStringExtra("nombre");
            textViewMetodo.setText(metodo);
            textViewTitulo.setText(titulo);
        }
    }
}
