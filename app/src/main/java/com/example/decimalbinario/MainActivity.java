package com.example.decimalbinario;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView label1, label2, labelResultado;
    EditText txtNumero, txtNumero2;
    Button cmdok;
    Spinner spinner;
    String[] operaciones = {"Suma", "Resta", "Multiplicación", "División"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        label1 = findViewById(R.id.label1);
        label2 = findViewById(R.id.label2);
        labelResultado = findViewById(R.id.labelResultado);
        txtNumero = findViewById(R.id.txtNumero);
        txtNumero2 = findViewById(R.id.txtNumero2);
        cmdok = findViewById(R.id.cmdok);
        spinner = findViewById(R.id.spinner);

        label1.setText("Número 1: ");
        label2.setText("Número 2: ");
        labelResultado.setText("Resultado:");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, operaciones);
        spinner.setAdapter(adapter);

        cmdok.setOnClickListener(this::binario_onClick);
    }

    public void binario_onClick(View v) {
        try {
            String numero1 = txtNumero.getText().toString();
            String numero2 = txtNumero2.getText().toString();

            int num1 = Integer.parseInt(numero1, 2);
            int num2 = Integer.parseInt(numero2, 2);

            String operacion = spinner.getSelectedItem().toString();
            int resultado = 0;

            switch (operacion) {
                case "Suma":
                    resultado = num1 + num2;
                    break;
                case "Resta":
                    resultado = num1 - num2;
                    break;
                case "Multiplicación":
                    resultado = num1 * num2;
                    break;
                case "División":
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        Toast.makeText(this, "División por cero no permitida", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    break;
            }

            labelResultado.setText("Resultado: " + Integer.toBinaryString(resultado));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor ingrese números binarios válidos", Toast.LENGTH_SHORT).show();
        }
    }

}