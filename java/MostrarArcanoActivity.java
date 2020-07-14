package com.example.fechanactarot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MostrarArcanoActivity extends AppCompatActivity {
    static final String C_NUMEROARCANO = "numero_arcano";
    static final String C_TITULOARCANO = "titulo_arcano";

    Integer mazoINT;
    Integer arcanoDia;

    TextView arcanoDiaNombreTV;
    TextView arcanoDiaDescripcionTV;
    TextView arcanoDiaDescDerechoTV;
    TextView arcanoDiaDescRevesTV;
    ImageView arcanoDiaIG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_arcano);

        CargarPreferencias();

        arcanoDiaNombreTV = findViewById(R.id.arcanoDiaNombreTV);
        arcanoDiaDescDerechoTV = findViewById(R.id.arcanoDiaDescDerechoTV);
        arcanoDiaDescRevesTV = findViewById(R.id.arcanoDiaDescRevesTV);
        arcanoDiaIG = findViewById(R.id.arcanoDiaIG);
        arcanoDiaDescripcionTV = findViewById(R.id.arcanoDiaDescripcionTV);

        Bundle datos = this.getIntent().getExtras();
        arcanoDia = datos.getInt(C_NUMEROARCANO);
        String titulo = datos.getString(C_TITULOARCANO);

        String font_path = "fonts/Orange Blossom.ttf";
        Typeface TF = Typeface.createFromAsset(getAssets(),font_path);
        TextView textoTituloTV = findViewById(R.id.textoTituloTV);

        textoTituloTV.setText(titulo);
        textoTituloTV.setTypeface(TF);
        arcanoDiaNombreTV.setTypeface(TF);

        CrearMensajeDelDia();
    }

    private void CargarPreferencias() {
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
        String mazo = preferencias.getString(getString(R.string.settings_deck_key),"0");
        mazoINT = Integer.parseInt(mazo);
    }

    private void CrearMensajeDelDia() {

        Arcano arcanoActual = Utils.BuscarArcano(getApplicationContext(),arcanoDia);
        updateUi(arcanoActual);
    }

    private void updateUi(Arcano arcanoActual) {

        TextView textoDerechoTV = findViewById(R.id.textoDerechoTV);
        TextView textoRevesTV = findViewById(R.id.textoRevesTV);
        textoDerechoTV.setVisibility(View.VISIBLE);
        textoRevesTV.setVisibility(View.VISIBLE);

        arcanoDiaNombreTV.setText(arcanoActual.getMyNombre());
        arcanoDiaDescDerechoTV.setText(arcanoActual.getMyDescripcionCortaDerecho());
        arcanoDiaDescRevesTV.setText(arcanoActual.getMyDescripcionCortaReves());
        arcanoDiaDescripcionTV.setText(arcanoActual.getMyDescripcion());

        Integer imagenINT = Utils.ResolverImagenDesdeNombre(this, arcanoActual.getMyImagen(),mazoINT);
        arcanoDiaIG.setImageResource(imagenINT);
    }

}
