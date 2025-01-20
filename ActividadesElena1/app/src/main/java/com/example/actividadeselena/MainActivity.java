package com.example.actividadeselena;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Menu principal
        // Actividad 1
        Button actividad1 = findViewById(R.id.Actividad1);
        actividad1.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Actividad1.class);
            startActivity(intent);
        });
        // Actividad 2
        Button actividad2 = findViewById(R.id.Actividad2);
        actividad2.setOnClickListener(view -> { // Usa actividad2 aquí
            Intent intent = new Intent(MainActivity.this, Actividad2.class);
            startActivity(intent);
        });
        // Actividad 3
        Button actividad3 = findViewById(R.id.Actividad3);
        actividad3.setOnClickListener(view -> { // Usa actividad3 aquí
            Intent intent = new Intent(MainActivity.this, Actividad3.class);
            startActivity(intent);
        });
        // Actividad 4
        Button actividada4 = findViewById(R.id.Actividad4);
        actividad3.setOnClickListener(view -> { // Usa actividad3 aquí
            Intent intent = new Intent(MainActivity.this, Actividad4.class);
            startActivity(intent);
        });

        // Actividad 5
        Button actividada5 = findViewById(R.id.Actividad5);
        actividad3.setOnClickListener(view -> { // Usa actividad3 aquí
            Intent intent = new Intent(MainActivity.this, Actividad5.class);
            startActivity(intent);
        });
    }
}