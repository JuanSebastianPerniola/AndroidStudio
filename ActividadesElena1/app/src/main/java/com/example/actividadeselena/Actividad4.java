package com.example.actividadeselena;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Environment;
import android.widget.Toast;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;

public class Actividad4 extends AppCompatActivity {
    private static final String FILENAME = "textoGuardado.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actividad4);
        Button guardar = findViewById(R.id.guardar4);
        Button recuperar = findViewById(R.id.recuperar4);

        guardar.setOnClickListener(v -> saveToFile());
        recuperar.setOnClickListener(v -> readFromFile());
    }

    private void saveToFile() {
        EditText texto = findViewById(R.id.InputInformacion);
        String text = texto.getText().toString();
        try {
            File file = new File(Environment.getExternalStorageDirectory(), FILENAME);
            FileWriter writer = new FileWriter(file);
            writer.write(text);
            writer.close();
            Toast.makeText(this, "Texto guardado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardarlo: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void readFromFile() {
        EditText texto = findViewById(R.id.InputInformacion);
        try {
            File file = new File(Environment.getExternalStorageDirectory(), FILENAME);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder text = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            // lo cerramos
            bufferedReader.close();

            texto.setText(text.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "error al leerlo : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}