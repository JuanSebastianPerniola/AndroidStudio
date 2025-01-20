package com.example.actividadeselena;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

import kotlin.text.Regex;

public class Actividad1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actividad1);
        // return to the main
        Button volverAlMain = findViewById(R.id.ButtonGetBackToMainMenu);

        volverAlMain.setOnClickListener(view -> {
            Intent intent = new Intent(Actividad1.this, MainActivity.class);
            startActivity(intent);
        });
        // Elementos pantalla
        EditText campo1 = findViewById(R.id.campo1);
        EditText campo2 = findViewById(R.id.capo2);
        campo1.getText().clear();
        campo2.getText().clear();

        Button guardar = findViewById(R.id.Guardar);
        Button Mostrar = findViewById(R.id.Mostrar);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String regex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);

        if(pattern.matcher(campo1.getText().toString()).matches()||
           pattern.matcher(campo2.getText().toString()).matches()
          )
        {
            Toast.makeText(this, "Error, ", Toast.LENGTH_LONG).show();
        }else
        {
            editor.putString("CampoValor1", campo1.getText().toString());
            editor.putString("CampoValor2", campo2.getText().toString());
            editor.apply();
        }
    }
}