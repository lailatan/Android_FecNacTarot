package com.example.fechanactarot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PedirFechaNacimientoActivity extends AppCompatActivity {
    static final String C_MAZO = "mazo";
    static final String C_DIA = "valor_dia";
    static final String C_MES = "valor_mes";
    static final String C_ANIO = "valor_anio";

    EditText diaET;
    EditText mesET;
    EditText anioET;
    TextView ingresarFechaTV;
    TextView calcularBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_fecha_nacimiento);

        Bundle datos = this.getIntent().getExtras();

        diaET = findViewById(R.id.diaET);
        mesET = findViewById(R.id.mesET);
        anioET = findViewById(R.id.anioET);

        ingresarFechaTV =  findViewById(R.id.ingresarFechaTV);
        calcularBT =  findViewById(R.id.calcularBT);

        String font_path = "fonts/Orange Blossom.ttf";
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);
        ingresarFechaTV.setTypeface(TF);
        calcularBT.setTypeface(TF);

    }

        public void calcularTarot(View view) {
            Integer mazoINT=0;

            if (validarDatos()) {

                Intent abrirFecNacimiento = new Intent(this, FechaNacimientoActivity.class);

                abrirFecNacimiento.putExtra(C_DIA, diaET.getText().toString());
                abrirFecNacimiento.putExtra(C_MES, mesET.getText().toString());
                abrirFecNacimiento.putExtra(C_ANIO, anioET.getText().toString());

                startActivity(abrirFecNacimiento);

                finish();
            }
        }

        private boolean validarDatos() {
            boolean ValidarDatosBO = false;

            if (validarDia()) {
                if (validarMes()) {
                    if (validarAnio()) {
                        if (validarFecha()) {
                            ValidarDatosBO = true;
                        }
                    }
                }
            }
            return ValidarDatosBO;
        }

        private boolean validarDia() {
            String valorDiaSTR = diaET.getText().toString();
            boolean ValidarDatosBO = false;

            if (valorDiaSTR.isEmpty()) {
                // Le digo al usuario que necesita poner un dato
                Toast.makeText(getApplicationContext(), R.string.message_enter_value, Toast.LENGTH_LONG).show();
                diaET.requestFocus();
            } else if (Integer.parseInt(valorDiaSTR) > 31) {
                Toast.makeText(getApplicationContext(), R.string.message_enter_valid_day, Toast.LENGTH_LONG).show();
                diaET.requestFocus();
            } else {
                ValidarDatosBO = true;
                diaET.setText(String.format("%02d", Integer.parseInt(valorDiaSTR)));
            }

            return ValidarDatosBO;
        }

        private boolean validarMes() {
            String valorMesSTR = mesET.getText().toString();
            boolean ValidarDatosBO = false;

            if (valorMesSTR.isEmpty()) {
                // Le digo al usuario que necesita poner un dato
                Toast.makeText(getApplicationContext(), R.string.message_enter_value, Toast.LENGTH_LONG).show();
                mesET.requestFocus();
            } else if (Integer.parseInt(valorMesSTR) > 12) {
                Toast.makeText(getApplicationContext(), R.string.message_enter_valid_month, Toast.LENGTH_LONG).show();
                mesET.requestFocus();
            } else {
                ValidarDatosBO = true;
                mesET.setText(String.format("%02d", Integer.parseInt(valorMesSTR)));
            }

            return ValidarDatosBO;
        }

        private boolean validarAnio() {
            String valorAnioSTR = anioET.getText().toString();
            boolean ValidarDatosBO = false;

            if (valorAnioSTR.isEmpty()) {
                // Le digo al usuario que necesita poner un dato
                Toast.makeText(getApplicationContext(), R.string.message_enter_value, Toast.LENGTH_LONG).show();
                diaET.requestFocus();
            } else if (valorAnioSTR.length() < 4) {
                Toast.makeText(getApplicationContext(), R.string.message_year_4_digits, Toast.LENGTH_LONG).show();
                anioET.requestFocus();
            } else {
                ValidarDatosBO = true;
            }

            return ValidarDatosBO;
        }

        public boolean validarFecha() {
            String valorDiaSTR = diaET.getText().toString();
            String valorMesSTR = mesET.getText().toString();
            String valorAnioSTR = anioET.getText().toString();

            boolean valida = false;
            try {
                //Formato de fecha (día/mes/año)
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                formatoFecha.setLenient(false);
                //Comprobación de la fecha
                formatoFecha.parse(valorDiaSTR + "/" + valorMesSTR + "/" + valorAnioSTR);
                valida = true;
            } catch (ParseException e) {
                //Si la fecha no es correcta, pasará por aquí
                Toast.makeText(getApplicationContext(), R.string.message_enter_valid_date, Toast.LENGTH_LONG).show();
                diaET.requestFocus();
                valida = false;
            }
            return valida;
        }
}
