package com.example.actividadeselena;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Actividad3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad3);

        Button leer = findViewById(R.id.Leer);

        // Archivo almacenado en el almacenamiento interno
        File ficheroHolaMundo = new File(getFilesDir(), "HolaMundo.txt");
        leer.setOnClickListener(view -> {
            if (ficheroHolaMundo.exists()) {
                try (FileInputStream fileInputStream = new FileInputStream(ficheroHolaMundo);
                     InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                     BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(this, "Error al leer el archivo", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Archivo no encontrado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}