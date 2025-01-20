package com.example.actividadeselena;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Actividad5 extends AppCompatActivity {

    private EditText editTextDNI, editTextName;
    private Button buttonInsert, buttonList, buttonDelete, buttonUpdate;
    private ListView listViewRecords;
    private ArrayList<String> records;
    private ArrayAdapter<String> adapter;
    private SQLiteDatabase db;
    private int selectedId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actividad5);


// Initialize views
        editTextDNI = findViewById(R.id.editTextDNI);
        editTextName = findViewById(R.id.editTextName);
        buttonInsert = findViewById(R.id.botonInsert);
        buttonList = findViewById(R.id.botonLista);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        listViewRecords = findViewById(R.id.listViewRecords);

        // Initialize database
        db = openOrCreateDatabase("MyDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS people(id INTEGER PRIMARY KEY AUTOINCREMENT, dni TEXT, name TEXT);");

        // Initialize list
        records = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, records);
        listViewRecords.setAdapter(adapter);

        // Set click listeners
        buttonInsert.setOnClickListener(v -> insertRecord());
        buttonList.setOnClickListener(v -> listRecords());
        buttonDelete.setOnClickListener(v -> showDeleteDialog());
        buttonUpdate.setOnClickListener(v -> showUpdateDialog());

        listViewRecords.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = records.get(position);
            selectedId = Integer.parseInt(selectedItem.split(" - ")[0]);
            Cursor cursor = db.rawQuery("SELECT dni, name FROM people WHERE id = ?",
                    new String[]{String.valueOf(selectedId)});
            if (cursor.moveToFirst()) {
                editTextDNI.setText(cursor.getString(0));
                editTextName.setText(cursor.getString(1));
            }
            cursor.close();
        });
    }

    private void insertRecord() {
        String dni = editTextDNI.getText().toString();
        String name = editTextName.getText().toString();

        if (!dni.isEmpty() && !name.isEmpty()) {
            db.execSQL("INSERT INTO people (dni, name) VALUES (?, ?)",
                    new String[]{dni, name});
            Toast.makeText(this, "Registre inserit correctament", Toast.LENGTH_SHORT).show();
            clearFields();
            listRecords();
        } else {
            Toast.makeText(this, "Si us plau, ompliu tots els camps", Toast.LENGTH_SHORT).show();
        }
    }

    private void listRecords() {
        records.clear();
        Cursor cursor = db.rawQuery("SELECT * FROM people", null);
        while (cursor.moveToNext()) {
            records.add(cursor.getInt(0) + " - " + cursor.getString(1) + " - " + cursor.getString(2));
        }
        cursor.close();
        adapter.notifyDataSetChanged();
    }

    private void showDeleteDialog() {
        if (selectedId == -1) {
            Toast.makeText(this, "Si us plau, seleccioneu un registre", Toast.LENGTH_SHORT).show();
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("Confirmar eliminació")
                .setMessage("Esteu segur que voleu eliminar aquest registre?")
                .setPositiveButton("Sí", (dialog, which) -> deleteRecord())
                .setNegativeButton("No", null)
                .show();
    }

    private void deleteRecord() {
        db.execSQL("DELETE FROM people WHERE id = ?",
                new String[]{String.valueOf(selectedId)});
        Toast.makeText(this, "Registre eliminat correctament", Toast.LENGTH_SHORT).show();
        selectedId = -1;
        clearFields();
        listRecords();
    }

    private void showUpdateDialog() {
        if (selectedId == -1) {
            Toast.makeText(this, "Si us plau, seleccioneu un registre", Toast.LENGTH_SHORT).show();
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("Confirmar modificació")
                .setMessage("Esteu segur que voleu modificar aquest registre?")
                .setPositiveButton("Sí", (dialog, which) -> updateRecord())
                .setNegativeButton("No", null)
                .show();
    }

    private void updateRecord() {
        String dni = editTextDNI.getText().toString();
        String name = editTextName.getText().toString();

        if (!dni.isEmpty() && !name.isEmpty()) {
            db.execSQL("UPDATE people SET dni = ?, name = ? WHERE id = ?",
                    new String[]{dni, name, String.valueOf(selectedId)});
            Toast.makeText(this, "Registre modificat correctament", Toast.LENGTH_SHORT).show();
            selectedId = -1;
            clearFields();
            listRecords();
        } else {
            Toast.makeText(this, "Si us plau, ompliu tots els camps", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        editTextDNI.setText("");
        editTextName.setText("");
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}