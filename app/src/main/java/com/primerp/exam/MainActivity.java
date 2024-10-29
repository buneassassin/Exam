package com.primerp.exam;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.primerp.exam.Adapter.adapter;
import com.primerp.exam.Class.Persona;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CALL_PHONE = 1;
    private static final int REQUEST_CAMERA = 2;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Verificar y solicitar permisos
        checkAndRequestPermissions();

        // Lista de personas para el RecyclerView
        List<Persona> personasList = new ArrayList<>();
        personasList.add(new Persona("Jose", "8714307468", "Persona normal",R.drawable.cat1, Color.RED));
        personasList.add(new Persona("Maria", "8714307418", "Persona loca", R.drawable.cat2, Color.GREEN));
        personasList.add(new Persona("Leo", "8714307720", "Persona miedosa", R.drawable.cat3, Color.BLUE));
        personasList.add(new Persona("Iker", "8714307462", "Persona cuidosa", R.drawable.cat4, Color.YELLOW));
        personasList.add(new Persona("Noe", "8714307111", "Persona pequeño", R.drawable.cat5, Color.CYAN));
        personasList.add(new Persona("Ana", "8714307777", "Persona enojon", R.drawable.cat6, Color.LTGRAY));

        // Crear el adaptador y asignarlo al RecyclerView
        adapter personaAdapter = new adapter(personasList, this, persona -> {
            int position = personasList.indexOf(persona);
            if (position == 0) {
                Toast.makeText(MainActivity.this, "Jose es un Persona muy grande", Toast.LENGTH_SHORT).show();
            } else if (position == 1) {
                Toast.makeText(MainActivity.this, "Maria es un Persona loca", Toast.LENGTH_SHORT).show();
            } else if (position == 2) {
                Toast.makeText(MainActivity.this, "Leo es muy Persona", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Descripción: " + persona.getDescription(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(personaAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    private void checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso para llamadas concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso para llamadas denegado", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso para la cámara concedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso para la cámara denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
