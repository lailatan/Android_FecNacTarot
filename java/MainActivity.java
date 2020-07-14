package com.example.fechanactarot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static final String C_NUMEROARCANO = "numero_arcano";
    static final String C_TITULOARCANO = "titulo_arcano";
    Integer mazoINT;
    Boolean mazoSoloMayoresBL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView fechaNacBT =  findViewById(R.id.fechaNacBT);
        TextView mensajeDiaBT =  findViewById(R.id.mensajeDiaBT);
        TextView ingresarFechaTV =  findViewById(R.id.ingresarFechaTV);

        String font_path = "fonts/Orange Blossom.ttf";
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);
        fechaNacBT.setTypeface(TF);
        mensajeDiaBT.setTypeface(TF);
        ingresarFechaTV.setTypeface(TF);
    }

    public void abrirFechaNacimiento(View view) {

        Intent abrirPedirFecNac = new Intent(this, PedirFechaNacimientoActivity.class);
        startActivity(abrirPedirFecNac);
    }


    public void abrirMensajeDia(View view) {
        Boolean completoBL=false;
        Random random = new Random();
        int ArcanoDia = 1;

        CargarPreferencias();

        if (mazoSoloMayoresBL) ArcanoDia = random.nextInt(22) + 1;
        else ArcanoDia = random.nextInt(78) + 1;

        Intent abrirMsjDia = new Intent(this, MostrarArcanoActivity.class);
        abrirMsjDia.putExtra(C_NUMEROARCANO, ArcanoDia);
        abrirMsjDia.putExtra(C_TITULOARCANO, getString(R.string.daily_arcane));

        startActivity(abrirMsjDia);
    }

    private void CargarPreferencias() {
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        String mazo = preferencias.getString(getString(R.string.settings_deck_key),"0");
        mazoINT = Integer.parseInt(mazo);
        mazoSoloMayoresBL=preferencias.getBoolean(getString(R.string.settings_only_major_arcana_key),false);
    }

    public void abrirConfiguracion(View view) {
        Intent settingsIntent = new Intent(this, PreferenciasActivity.class);
        startActivity(settingsIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, PreferenciasActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}