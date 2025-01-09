package com.example.actividadeselena;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Menu principal

        Button actividad1 = findViewById(R.id.button);

        actividad1.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Actividad1.class);
            startActivity(intent);
        });
    }
}