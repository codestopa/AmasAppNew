package com.example.amasappnew;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RecetaActivity extends AppCompatActivity {

    private TextView textViewMetodo;
    private TextView textViewTitulo;
    private ImageView imageViewReceta;
    private LinearLayout ingredientsContainer;
    private TextView textViewValorNutricional;
    private NestedScrollView nestedScrollView;
    private ImageButton btnFavorite;
    private boolean isFavorite = false;
    private SharedPreferences sharedPreferences;
    private ArrayList<String> favoritosList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recetadetalleactivity);



        sharedPreferences = getSharedPreferences("favoritos", MODE_PRIVATE);
        favoritosList = new ArrayList<>(sharedPreferences.getAll().keySet());

        btnFavorite = findViewById(R.id.btnFavorite);


        updateFavoriteIcon();



        textViewMetodo = findViewById(R.id.textViewMetodo);
        textViewTitulo = findViewById(R.id.textViewTitulo);
        ingredientsContainer = findViewById(R.id.ingredientsContainer);
        imageViewReceta = findViewById(R.id.imageViewReceta);
        textViewValorNutricional = findViewById(R.id.valorNutricional);
        nestedScrollView = findViewById(R.id.nestedScrollView);

        Button shareButton = findViewById(R.id.shareButton);

        Intent intent = getIntent();
        if (intent != null) {
            String metodo = intent.getStringExtra("metodo");
            String titulo = intent.getStringExtra("nombre");
            int imageResId = intent.getIntExtra("imagen", R.drawable.ic_launcher_background);
            ArrayList<String> ingredientes = intent.getStringArrayListExtra("ingredientes");
            String valorNutricional = intent.getStringExtra("valorNutricional");

            textViewMetodo.setText(metodo);
            textViewTitulo.setText(titulo);
            imageViewReceta.setImageResource(imageResId);
            String valorNutricionalContenido = leerValorNutricional(valorNutricional);
            textViewValorNutricional.setText(valorNutricionalContenido);

            if (ingredientes != null) {
                for (String ingrediente : ingredientes) {
                    TextView textView = new TextView(this);
                    textView.setText(ingrediente);
                    textView.setTextSize(18);
                    ingredientsContainer.addView(textView);
                }
            }

        }

        shareButton.setOnClickListener(v -> {
            try {
                // Crear y compartir PDF
                File pdfFile = PDFUtils.createPdfFromNestedScrollView(this, nestedScrollView, "receta.pdf");
                sharePdf(pdfFile);
            } catch (IOException e) {
                Log.e("RecetaActivity", "Error al crear PDF", e);
            }
        });

    }




    private void updateFavoriteIcon() {
        if (isFavorite) {
            btnFavorite.setImageResource(R.drawable.ic_favorites_yes);
        } else {
            btnFavorite.setImageResource(R.drawable.ic_favorites);
        }
    }

    private void sharePdf(File pdfFile) {
        Uri pdfUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", pdfFile);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("application/pdf");
        shareIntent.putExtra(Intent.EXTRA_STREAM, pdfUri);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "Compartir receta en PDF"));
    }
    private String leerValorNutricional(String filename) {
        StringBuilder valorNutricional = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(filename)));
            String line;
            while ((line = reader.readLine()) != null) {
                valorNutricional.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return valorNutricional.toString();
    }
}
