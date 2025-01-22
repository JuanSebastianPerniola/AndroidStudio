package com.example.actividadeselena;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Actividad2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        // Iniciamos botones de input
        Button volverAlMain = findViewById(R.id.ButtonGetBackToMainMenu);
        Button guardar = findViewById(R.id.Guardar);
        Button mostrar = findViewById(R.id.Mostrar);

        EditText campo1 = findViewById(R.id.campo1);
        EditText campo2 = findViewById(R.id.capo2);

        // Limpiar los campos
        campo1.getText().clear();
        campo2.getText().clear();

        volverAlMain.setOnClickListener(view -> {
            Intent intent = new Intent(Actividad2.this, MainActivity.class);
            startActivity(intent);
        });

        guardar.setOnClickListener(view -> {
            String campo1Text = campo1.getText().toString();
            String campo2Text = campo2.getText().toString();

            // validamos alfabaticos valores
            String regex = "^[a-zA-Z]+$";
            Pattern pattern = Pattern.compile(regex);

            if (campo1Text.isEmpty() || campo2Text.isEmpty()) {
                Toast.makeText(this, "Llene ambos fields", Toast.LENGTH_LONG).show();
            } else if (!pattern.matcher(campo1Text).matches() || !pattern.matcher(campo2Text).matches()) {
                Toast.makeText(this, "Algun caracater no a sido valido", Toast.LENGTH_LONG).show();
            } else {
                try {
                    String file = "File.txt";
                    FileOutputStream fileOutputStream = openFileOutput(file, MODE_PRIVATE);
                    fileOutputStream.write(campo1Text.getBytes());
                    fileOutputStream.write("\n".getBytes());
                    fileOutputStream.write(campo2Text.getBytes());
                    fileOutputStream.close();
                    Toast.makeText(this, "Data guardada", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Toast.makeText(this, "Error al guardar ", Toast.LENGTH_LONG).show();
                }
            }
        });
        mostrar.setOnClickListener(view -> {
            try {
                FileInputStream fileInputStream = openFileInput("File.txt");
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }

                bufferedReader.close();
                Toast.makeText(this, "Contenido de archivo guardado" +
                                ":\n"
                                + stringBuilder.toString(), Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error reading file", Toast.LENGTH_LONG).show();
            }
        });
    }
}
