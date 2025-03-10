package com.example.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display = findViewById(R.id.textView);
    private String inputActual = "";
    private String operadorActual = "";
    private double primerNumero = 0;
    private boolean operadorClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Iniciamos los botones
        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonDecimal
        };
        // foreach anidado para recorrer cada boton
        // parecido a un foreach en c# .net
        for (int id : numberButtonIds) {
            // recorremos cada boton que le damos
            Button button = findViewById(id);
            button.setOnClickListener(v -> onNumberButtonClick(button.getText().toString()));
        }

        // Iniciamos de los botones de operacion
        Button sumar = findViewById(R.id.buttonSuma);
        Button restar = findViewById(R.id.buttonResta);
        Button multiplicar = findViewById(R.id.buttonMultiplicar);
        Button dividir = findViewById(R.id.buttonDividir);
        Button igual = findViewById(R.id.buttonResultado);
        Button borrar = findViewById(R.id.buttonCA);
        Button borrarUltimo = findViewById(R.id.buttonDelete);

        // Acciones al pulsar botones como en java eventclick listener
        sumar.setOnClickListener(v -> onOperatorClick("+"));
        restar.setOnClickListener(v -> onOperatorClick("-"));
        multiplicar.setOnClickListener(v -> onOperatorClick("*"));
        dividir.setOnClickListener(v -> onOperatorClick("/"));
        igual.setOnClickListener(v -> onEqualsClick());
        borrar.setOnClickListener(v -> Limpiar());
        borrarUltimo.setOnClickListener(v -> BorrarUltimo());
    }

    // metodo para manejar el clic en los botones numéricos
    private void onNumberButtonClick(String number) {
        if (operadorClick) {
            inputActual = "";
            operadorClick = false;
        }
        inputActual += number;
        display.setText(inputActual);
    }

    // metodo para manejar el clic en los botones de operación
    private void onOperatorClick(String operator) {
        if (!inputActual.isEmpty()) {
            // Si ya hay un operador, control sobre operadores ya puestos
            if (!operadorActual.isEmpty()) {
                return;
            }
            primerNumero = Double.parseDouble(inputActual);
            operadorActual = operator;
            operadorClick = true;
            inputActual += " " + operator + " ";
            display.setText(inputActual);
        }
    }

    // metodo para manejar el clic en el botón de igual
    private void onEqualsClick() {
        if (!inputActual.isEmpty() && !operadorActual.isEmpty()) {
            // Agarramos el segundo numero añadido
            String[] parts = inputActual.split(" ");
            if (parts.length < 3) {
                display.setText("Error: Formato invalido");
                return;
            }
            double segundNumero = Double.parseDouble(parts[2]);
            double result = 0;
            // switch para causisticas
            switch (operadorActual) {
                case "+":
                    result = primerNumero + segundNumero;
                    break;
                case "-":
                    result = primerNumero - segundNumero;
                    break;
                case "*":
                    result = primerNumero * segundNumero;
                    break;
                case "/":
                    // no se puede dividir por cero
                    if (segundNumero != 0) {
                        result = primerNumero / segundNumero;
                    } else {
                        display.setText("Error: División por cero");
                        return;
                    }
                    break;
            }

            display.setText(String.valueOf(result));
            inputActual = String.valueOf(result);
            operadorActual = "";
            operadorClick = true;
        }
    }

    // metodo para manejar el clic en el botón de borrar todo
    private void Limpiar() {
        inputActual = "";
        primerNumero = 0;
        operadorActual = "";
        operadorClick = false;
        display.setText("0");
    }

    // metodo para manejar el clic en el boton de borrar el ultimo boton
    private void BorrarUltimo() {
        if (inputActual.length() > 0) {
            inputActual = inputActual.substring(0, inputActual.length() - 1);
            display.setText(inputActual);
        }
    }
}
