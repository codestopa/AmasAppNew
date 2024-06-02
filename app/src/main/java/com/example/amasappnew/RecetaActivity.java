package com.example.amasappnew;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecetaActivity extends AppCompatActivity {

    private TextView textViewMetodo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recetadetalleactivity);

        textViewMetodo = findViewById(R.id.textViewMetodo);

        Intent intent = getIntent();
        if (intent != null) {
            String metodo = intent.getStringExtra("metodo");
            textViewMetodo.setText(metodo);
        }
    }
}
