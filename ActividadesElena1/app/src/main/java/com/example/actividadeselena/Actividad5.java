package com.example.actividadeselena;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class Actividad5 extends AppCompatActivity {
    EditText EditTextDNI, EditTextNombre;
    Button BotonInsertar, BotonListar, BotonBorrar, BotonActualizar;
    ListView ListaRegistros;
    ArrayAdapter<String> Adaptador;
    SQLiteDatabase BaseDatos;
    public static int IdSeleccionado = -1;
    public static java.util.ArrayList<String> Registros = new java.util.ArrayList<>();

    protected void onCreate(Bundle InstanciaGuardada) {
        super.onCreate(InstanciaGuardada);
        setContentView(R.layout.activity_actividad5);

        EditTextDNI = findViewById(R.id.editTextDNI);
        EditTextNombre = findViewById(R.id.editTextName);
        BotonInsertar = findViewById(R.id.botonInsert);
        BotonListar = findViewById(R.id.botonLista);
        BotonBorrar = findViewById(R.id.buttonDelete);
        BotonActualizar = findViewById(R.id.buttonUpdate);
        ListaRegistros = findViewById(R.id.listViewRecords);

        BaseDatos = openOrCreateDatabase("BaseDatos", Context.MODE_PRIVATE, null);
        BaseDatos.execSQL("CREATE TABLE IF NOT EXISTS personas(id INTEGER PRIMARY KEY AUTOINCREMENT, dni TEXT, nombre TEXT);");

        Adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Registros);
        ListaRegistros.setAdapter(Adaptador);

        BotonInsertar.setOnClickListener(v -> {
            if(!EditTextDNI.getText().toString().isEmpty() && !EditTextNombre.getText().toString().isEmpty()) {
                BaseDatos.execSQL("INSERT INTO personas (dni, nombre) VALUES ('" + EditTextDNI.getText() + "', '" + EditTextNombre.getText() + "')");
                Toast.makeText(this, "Insertado", Toast.LENGTH_SHORT).show();
                EditTextDNI.setText(""); EditTextNombre.setText("");
                Registros.clear();
                Cursor Consulta = BaseDatos.rawQuery("SELECT * FROM personas", null);
                while(Consulta.moveToNext()) Registros.add(Consulta.getInt(0) + " - " + Consulta.getString(1) + " - " + Consulta.getString(2));
                Consulta.close();
                Adaptador.notifyDataSetChanged();
            }
        });

        BotonListar.setOnClickListener(v -> {
            Registros.clear();
            Cursor Consulta = BaseDatos.rawQuery("SELECT * FROM personas", null);
            while(Consulta.moveToNext()) Registros.add(Consulta.getInt(0) + " - " + Consulta.getString(1) + " - " + Consulta.getString(2));
            Consulta.close();
            Adaptador.notifyDataSetChanged();
        });

        BotonBorrar.setOnClickListener(v -> {
            if(IdSeleccionado != -1) {
                new AlertDialog.Builder(this).setTitle("¿Seguro?").setMessage("¿Borrar?")
                        .setPositiveButton("Sí", (dialogo, cual) -> {
                            BaseDatos.execSQL("DELETE FROM personas WHERE id = " + IdSeleccionado);
                            Toast.makeText(this, "Borrado", Toast.LENGTH_SHORT).show();
                            IdSeleccionado = -1;
                            EditTextDNI.setText("");
                            EditTextNombre.setText("");
                            Registros.clear();
                            Cursor Consulta = BaseDatos.rawQuery("SELECT * FROM personas", null);
                            while(Consulta.moveToNext()) Registros.add(Consulta.getInt(0) + " - " + Consulta.getString(1) + " - " + Consulta.getString(2));
                            Consulta.close();
                            Adaptador.notifyDataSetChanged();
                        }).setNegativeButton("No", null).show();
            }
        });

        BotonActualizar.setOnClickListener(v -> {
            if(IdSeleccionado != -1) {
                new AlertDialog.Builder(this).setTitle("¿Seguro?").setMessage("¿Actualizar?")
                        .setPositiveButton("Sí", (dialogo, cual) -> {
                            if(!EditTextDNI.getText().toString().isEmpty() && !EditTextNombre.getText().toString().isEmpty()) {
                                BaseDatos.execSQL("UPDATE personas SET dni='" + EditTextDNI.getText() + "', nombre='" + EditTextNombre.getText() + "' WHERE id=" + IdSeleccionado);
                                Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show();
                                IdSeleccionado = -1;
                                EditTextDNI.setText("");
                                EditTextNombre.setText("");
                                Registros.clear();
                                Cursor Consulta = BaseDatos.rawQuery("SELECT * FROM personas", null);
                                while(Consulta.moveToNext()) Registros.add(Consulta.getInt(0) + " - " + Consulta.getString(1) + " - " + Consulta.getString(2));
                                Consulta.close();
                                Adaptador.notifyDataSetChanged();
                            }
                        }).setNegativeButton("No", null).show();
            }
        });

        ListaRegistros.setOnItemClickListener((padre, vista, posicion, id) -> {
            String[] partes = Registros.get(posicion).split(" - ");
            IdSeleccionado = Integer.parseInt(partes[0]);
            EditTextDNI.setText(partes[1]);
            EditTextNombre.setText(partes[2]);
        });
    }
}