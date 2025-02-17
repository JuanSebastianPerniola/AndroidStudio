package com.example.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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

        Button sumar = findViewById(R.id.buttonSuma);
        Button restar = findViewById(R.id.buttonDelete);
        Button dividir = findViewById(R.id.buttonDividir);
        Button multiplicar = findViewById(R.id.buttonMultiplicar);
        TextView resultado = findViewById(R.id.resultado);

        sumar.setOnClickListener(view -> {
            // Llama al método para calcular la suma
            calcularOperacion(resultado, "\\+","+");
        });

        restar.setOnClickListener(view -> {
            // Llama al método para calcular la suma
            calcularOperacion(resultado, "\\-","+");
        });

        dividir.setOnClickListener(view -> {
            // Llama al método para calcular la suma
            calcularOperacion(resultado, "\\/","+");
        });

        multiplicar.setOnClickListener(view -> {
            // Llama al método para calcular la suma
            calcularOperacion(resultado, "\\*","+");
        });
    }

    private void calcularOperacion(TextView resultado, String regex, String operacion) {
        String expresion = resultado.getText().toString();
        String[] numeros = expresion.split(regex);
        if(numeros.length < 2){
            resultado.setText("Error al calcular :");

            try {
            double resultadoNum = Double.parseDouble(numeros[0].trim());
            for (int i = 1; i < numeros.length; i++){
                double numero = Double.parseDouble(numeros[1].trim());
                switch (operacion){
                    case "+":
                        resultadoNum += numero;
                        break;
                    case "-":
                        resultadoNum -= numero;
                        break;
                    case "*":
                        resultadoNum *= numero;
                        break;
                    case "/":
                        if(numero == 0){
                            resultado.setText("Error : division cero");
                            return;
                        }
                        resultadoNum /=  numero;
                        break;

                    default:
                        resultado.setText("Error operacion no valida");
                        return;
                }
            }
            }catch (NumberFormatException e){
                resultado.setText("Error: Número no válido.");
            }
        }
    }
}
