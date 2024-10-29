package com.primerp.exam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class OtraActividad extends AppCompatActivity {

    private TextView textViewNombre;
    private TextView textViewTelefono;
    private ImageView imageView;
    private ConstraintLayout layaut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otra_actividad);

        // Enlazar las vistas del layout
        textViewNombre = findViewById(R.id.textViewNombre);
        textViewTelefono = findViewById(R.id.textViewTelefono);
        imageView = findViewById(R.id.imageView);
        layaut = findViewById(R.id.layaut);

        // Obtener los datos del Intent
        Intent intent = getIntent();
        if (intent != null) {
            String nombre = intent.getStringExtra("nombre");
            String telefono = intent.getStringExtra("telefono");
            int imageResId = intent.getIntExtra("imageResId", -1);
            int color = intent.getIntExtra("color", -1);
            // Mostrar los datos en los TextViews
            textViewNombre.setText(nombre);
            textViewTelefono.setText(telefono);

            // Mostrar la imagen si se recibió correctamente
            if (imageResId != -1) {
                imageView.setImageResource(imageResId);
                layaut.setBackgroundColor(color);
            }
        }
    }
}